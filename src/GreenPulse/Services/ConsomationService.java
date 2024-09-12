package GreenPulse.Services;
import GreenPulse.Entites.*;
import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.EnergyType;
import GreenPulse.Entites.Enums.FoodType;
import GreenPulse.Entites.Enums.VihicleType;
import GreenPulse.repository.ConsumptionRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsomationService {
    private final ConsumptionRepository consumptionRepository = new ConsumptionRepository();
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void addConsumptionToUser(int id) throws SQLException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getName());
            LocalDate startDate = null;
            LocalDate endDate = null;
            while (startDate == null) {
                try {
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    startDate = LocalDate.parse(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            while (endDate == null) {
                try {
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    endDate = LocalDate.parse(scanner.nextLine());
                    if (endDate.isBefore(startDate)) {
                        System.out.println("End date cannot be before the start date. Please enter a valid date.");
                        endDate = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            // Collect quantity
            double quantity = -1;
            while (quantity <= 0) {
                try {
                    System.out.print("Enter consumption quantity: ");
                    quantity = Double.parseDouble(scanner.nextLine());
                    if (quantity <= 0) {
                        System.out.println("Quantity must be a positive number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for quantity.");
                }
            }

            // Choose consumption type (Transport, Housing, Food)
            Consomation consumption = null;
            ConsumptionType consumptionType = null;
            System.out.println("Select consumption type (TRANSPORT, HOUSING, FOOD): ");
            consumptionType = ConsumptionType.valueOf(scanner.nextLine().toUpperCase());
            String type = String.valueOf(consumptionType);
            switch (type) {
                case "TRANSPORT":
                    double distanceTraveled = -1;
                    while (distanceTraveled <= 0) {
                        try {
                            System.out.print("Enter distance traveled: ");
                            distanceTraveled = Double.parseDouble(scanner.nextLine());
                            if (distanceTraveled <= 0) {
                                System.out.println("Distance must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for distance.");
                        }
                    }

                    VihicleType vehicleType = null;
                    while (vehicleType == null) {
                        System.out.print("Enter vehicle type (CAR, TRAIN): ");
                        try {
                            vehicleType = VihicleType.valueOf(scanner.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid vehicle type! Please enter one of: CAR, TRAIN.");
                        }
                    }

                    consumption = new Transport(startDate, endDate, quantity, distanceTraveled, vehicleType, consumptionType , user.get().getId());
                    consumptionRepository.createTransport((Transport) consumption);
                    break;

                case "HOUSING":
                    double energyConsumption = -1;
                    while (energyConsumption <= 0) {
                        try {
                            System.out.print("Enter energy consumption: ");
                            energyConsumption = Double.parseDouble(scanner.nextLine());
                            if (energyConsumption <= 0) {
                                System.out.println("Energy consumption must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for energy consumption.");
                        }
                    }

                    EnergyType energyType = null;
                    while (energyType == null) {
                        System.out.print("Enter energy type (ELECTRICITY, GAZ): ");
                        try {
                            energyType = EnergyType.valueOf(scanner.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid energy type! Please enter one of: ELECTRICITY, GAZ.");
                        }
                    }

                    consumption = new Housing(startDate, endDate, quantity, energyConsumption, energyType, consumptionType, user.get().getId());
                    consumptionRepository.createHousing((Housing) consumption);
                    break;

                case "FOOD":
                    FoodType foodType = null;
                    while (foodType == null) {
                        System.out.print("Enter type of food (MEAT, VEGETABLE): ");
                        try {
                            foodType = FoodType.valueOf(scanner.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid food type! Please enter one of: MEAT, VEGETABLE.");
                        }
                    }

                    double weight = -1;
                    while (weight <= 0) {
                        try {
                            System.out.print("Enter food weight: ");
                            weight = Double.parseDouble(scanner.nextLine());
                            if (weight <= 0) {
                                System.out.println("Weight must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for weight.");
                        }
                    }

                    consumption = new Food(startDate, endDate, quantity, foodType, weight, consumptionType, user.get().getId());
                    consumptionRepository.createFood((Food) consumption);
                    break;

                default:
                    System.out.println("Invalid consumption type selected.");
                    return;
            }

            System.out.println("Consumption added successfully for user: " + user.get().getName());
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }
    public Double calculConsumptionTotalForUser(int id){
        Optional<User> user = userService.getUserWithConsumptions(id);
        return user.get().getConsomation().stream().mapToDouble(Consomation::calculerImpact).sum();
    }

}
