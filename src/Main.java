
import  GreenPulse.ManageUser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManageUser manageUser = new ManageUser();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. Display Users");
            System.out.println("3 . Modify User ");
            System.out.println("4 . Delete User ");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageUser.addUser();
                    break;
                case 2:
                    manageUser.displayUsers();
                    break;
                case 3:
                    manageUser.UpdateUser();
                    break;
                case 4:
                    manageUser.deleteUser();
                    break;
                case 5:
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
