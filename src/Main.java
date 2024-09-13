import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.EnergyType;
import GreenPulse.Entites.Enums.FoodType;
import GreenPulse.Entites.Enums.VihicleType;
import GreenPulse.Entites.Food;
import GreenPulse.Entites.Housing;
import GreenPulse.Services.ManageUser;
import GreenPulse.Views.ConsumptionView;
import GreenPulse.Views.UserView;
import GreenPulse.repository.ConsumptionRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        UserView userView = new UserView();
        ManageUser manageUser = new ManageUser();
        ConsumptionView consumptionView = new ConsumptionView();
        ConsumptionRepository consumptionRepository = new ConsumptionRepository();
        Scanner scanner = new Scanner(System.in);
//        Housing housing = new Housing(LocalDate.of(2024, 12, 12),LocalDate.of(2024, 12, 22),100,100., EnergyType.GAZ, ConsumptionType.HOUSING, 1);
//        consumptionRepository.createHousing(housing);


        while (true) {
            System.out.println("************************ Principal Menu *******************");
            System.out.println("1. Create Account");
            System.out.println("2. Show All Accounts");
            System.out.println("3. Modify Account  ");
            System.out.println("4. Delete Account ");
            System.out.println("5. Add Consumption to User");
            System.out.println("6. Show total Consumption ");
            System.out.println("7. Show Daily Consumption ");
            System.out.println("8. Show weekly Consumption ");
            System.out.println("9. Show Monthly  Consumption ");
            System.out.println("10. Users with a total consumption greater than 3000 KgCO2eq");
            System.out.println("11. Users Inactive");
            System.out.println("12. Show average of consumption for user");
            System.out.println("13. List of users sorted by their ");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userView.addUser();
                    break;
                case 2:
                    userView.displayAllUsers();
                    break;
                case 3:
                    userView.updateUser();
                    break;
                case 4:
                    userView.deleteUser();
                    break;
                case 5:
                    consumptionView.addConsumptionToUser();
                    break;
                case 6:
                    consumptionView.showConsumptionTotal();
                    break;
                case 7 :
//                    consomationService.showDailyCarbonConsumption();
                    break;
                case 8 :
//                    consomationService.showWeeklyCarbonConsumption();
                    break;
                case 9 :
//                    consomationService.showMonthlyCarbonConsumption();
                    break;
                case 10 :
                    userView.ShowAllUsersWithConsumptionGreater();
                    break;
                case 11:
                    userView.showUsersInactifs();
                    break;
                case 12:
                    userView.getavgConsumption();
                    break;
                case 13 :
                    userView.sortUserByTotalConsumption();
                    break;
                case 14:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
