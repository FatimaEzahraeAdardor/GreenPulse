package GreenPulse;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsomationService {
    private  ManageUser manageUser = new ManageUser();
      Scanner sc = new Scanner(System.in);
    public ConsomationService(ManageUser manageUser) {
        this.manageUser = manageUser;
    }
    public void addConsumptionToUser(){
        System.out.println("Add new Consumption");
        System.out.println("Enter the unique Identifier of the user to Add Consumption:");
        String  idUnique = sc.nextLine();
        User userCons= manageUser.getUserById(idUnique);
        if(userCons != null){
            System.out.println("Enter Start date");
            System.out.print("Enter start date (format: YYYY-MM-DD) : ");
            LocalDate startDate = LocalDate.parse(sc.nextLine());
            System.out.print("Enter end date (format: YYYY-MM-DD) : ");
            LocalDate endDate = LocalDate.parse(sc.nextLine());
            System.out.println("Enter the quantity:");
            double quantity = sc.nextDouble();
            sc.nextLine();
            int consomationId = userCons.getConsomation().size() + 1;
            Consomation newConsomation = new Consomation(consomationId, startDate, endDate, quantity);

            userCons.getConsomation().add(newConsomation);
            System.out.println("Consumption added successfully!");

        }else {
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
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                double dailyConsumption = totalQuantity / daysBetween;
                System.out.println("Daily consumption for user  with id " + idUnique + " between " + startDate + " and " + endDate + "is : " + dailyConsumption + " units.");
            }
        } else {
            System.out.println("User not found with ID: " + idUnique);
        }
    }


}

