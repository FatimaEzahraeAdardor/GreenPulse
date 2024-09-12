package GreenPulse.Views;

import GreenPulse.Services.ConsomationService;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsumptionView {
    private final ConsomationService consumptionService = new ConsomationService();
    private final Scanner scanner = new Scanner(System.in);


    public void addConsumptionToUser() throws SQLException {
        System.out.print("Enter user CIN: ");
        int id = scanner.nextInt();
        consumptionService.addConsumptionToUser(id);
    }
    public void showConsumptionTotal(){
        System.out.println("Enter your id");
        int id =scanner.nextInt();
         Double total = consumptionService.calculConsumptionTotalForUser(id);
                System.out.println("\nTotal carbon consumption of user " + id + " is : " + total +"  KgCO2eq");


}
}
