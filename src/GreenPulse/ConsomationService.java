package GreenPulse;
import utils.DateChecker;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsomationService {
    private ManageUser manageUser = new ManageUser();
    Scanner sc = new Scanner(System.in);

    public ConsomationService(ManageUser manageUser) {
        this.manageUser = manageUser;
    }

    public void addConsumptionToUser() {
        LocalDate startDate;
        LocalDate endDate;
        System.out.println("Add new Consumption");
        System.out.println("Enter the unique Identifier of the user to Add Consumption:");
        String idUnique = sc.nextLine();
        User userCons = manageUser.getUserById(idUnique);
        if (userCons != null) {
            while (true) {
                System.out.println("Enter Start date");
                System.out.print("Enter start date (format: YYYY-MM-DD) : ");
                 startDate = LocalDate.parse(sc.nextLine());
                System.out.print("Enter end date (format: YYYY-MM-DD) : ");
                 endDate = LocalDate.parse(sc.nextLine());
                 if (!endDate.isBefore(startDate)){
                     break;
                 }else {
                     System.out.println(" \n End date cannot be before the start date. Please enter valid dates.\n");
                 }
            };
            System.out.println("Enter the quantity:");
            double quantity = sc.nextDouble();
            sc.nextLine();
            boolean check = DateChecker.isDateAvailable(startDate , endDate ,getDates(userCons.getConsomation()));
            if (check) {
                int consomationId = userCons.getConsomation().size() + 1;
                Consomation newConsomation = new Consomation(consomationId, startDate, endDate, quantity);
                userCons.getConsomation().add(newConsomation);
                System.out.println("Consumption added successfully!");
            }else {
                System.out.println("Error occurred while adding consumption");

            }

        } else {
            System.out.println("User not found");
        }

    }

    public void showDailyCarbonConsumption() {
        System.out.println("Daily Carbon Consumption");
        System.out.print("Enter user unique Identifier: ");
        String idUnique = sc.nextLine();
        User user = manageUser.getUserById(idUnique);
        if (user != null) {
            for (Consomation consomation : user.getConsomation()) {
                LocalDate startDate = consomation.getStartDate();
                LocalDate endDate = consomation.getEndDate();
                double totalQuantity = consomation.getQuantity();
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                double dailyConsumption = totalQuantity / daysBetween;
                System.out.println("Daily consumption for user  with id " + idUnique + " between " + startDate + " and " + endDate + "is : " + dailyConsumption + " kg CO₂.");
            }
        } else {
            System.out.println("User not found with ID: " + idUnique);
        }
    }

    public void showWeeklyCarbonConsumption() {
        System.out.println("Weekly Carbon Consumption");
        System.out.print("Enter user unique Identifier: ");
        String idUnique = sc.nextLine();
        User user = manageUser.getUserById(idUnique);

        if (user != null) {
            for (Consomation consomation : user.getConsomation()) {
                LocalDate startDate = consomation.getStartDate();
                LocalDate endDate = consomation.getEndDate();
                double totalQuantity = consomation.getQuantity();
                long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                double dailyConsumption = totalQuantity / totalDays;
                LocalDate currentStartDate = startDate;

                while (currentStartDate.isBefore(endDate)) {
                    LocalDate currentEndDate = currentStartDate.plusDays(6);
                    if (currentEndDate.isAfter(endDate)) {
                        currentEndDate = endDate;
                    }
                    long daysInCurrentWeek = ChronoUnit.DAYS.between(currentStartDate, currentEndDate) + 1;
                    double weeklyConsumption = dailyConsumption * daysInCurrentWeek;

                    System.out.println("Weekly consumption for user with ID " + idUnique + " from " + currentStartDate + " to " + currentEndDate + " is: " + weeklyConsumption + " kg CO₂.");
                    currentStartDate = currentEndDate.plusDays(1);
                }
            }
        } else {
            System.out.println("User not found with ID: " + idUnique);
        }
    }

    public void showMonthlyCarbonConsumption() {
        System.out.println("Monthly Carbon Consumption");
        System.out.print("Enter user unique Identifier: ");
        String idUnique = sc.nextLine();
        User user = manageUser.getUserById(idUnique);

        if (user != null) {
            for (Consomation consomation : user.getConsomation()) {
                LocalDate startDate = consomation.getStartDate();
                LocalDate endDate = consomation.getEndDate();
                double totalQuantity = consomation.getQuantity();
                long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                double dailyConsumption = totalQuantity / totalDays;
                LocalDate currentStartDate = startDate;

                while (currentStartDate.isBefore(endDate)) {
                    LocalDate currentEndDate = currentStartDate.with(TemporalAdjusters.lastDayOfMonth());
                    if (currentEndDate.isAfter(endDate)) {
                        currentEndDate = endDate;
                    }
                    long daysInCurrentMonth = ChronoUnit.DAYS.between(currentStartDate, currentEndDate) + 1;
                    double monthlyConsumption = dailyConsumption * daysInCurrentMonth;

                    System.out.println("Monthly consumption for user with ID " + idUnique + " from " + currentStartDate + " to " + currentEndDate + " is: " + monthlyConsumption + " kg CO₂.");
                    currentStartDate = currentEndDate.plusDays(1);
                }
            }
        } else {
            System.out.println("User not found with ID: " + idUnique);
        }

    }
    public List<LocalDate> getDates (List<Consomation> consomations){
        List<LocalDate> dates = new ArrayList<>();
        for (Consomation consomation: consomations){
            for (LocalDate date = consomation.getStartDate() ; !date.isAfter(consomation.getEndDate()) ; date= date.plusDays(1)){
                dates.add(date);
            }
        }
        return dates;

    }
}

