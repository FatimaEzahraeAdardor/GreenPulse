package GreenPulse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManageUser {
    private Map<String , User> users;
    private Map<String ,Consomation> consomations;
    Scanner sc = new Scanner(System.in);


    public ManageUser() {
          users = new HashMap<>();
          consomations = new HashMap<>();

    }
    public void addUser() {
        System.out.println(" Create New Account ");
        System.out.println(" Enter your Unique Identifier");
        String IdUnique = sc.nextLine();
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Your Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        if (users.containsKey(IdUnique)){
            System.out.println("User with this ID already exists.");
        } else {
            User newUser = new User(IdUnique, name, age);
            users.put(IdUnique, newUser);
            System.out.println("User added successfully!");
        }
    }
     public void UpdateUser(){
         System.out.println("Modify Account");
         System.out.println("Enter the unique Identifier of the user to modify:");
        String IdUnique = sc.nextLine();
         User userModify = users.get(IdUnique);
         if (userModify != null){
             System.out.println("Enter new name:");
             String newName = sc.nextLine();
             System.out.println("Enter new age:");
             int newAge = sc.nextInt();
             sc.nextLine();
             userModify.setName(newName);
             userModify.setAge(newAge);
             System.out.println("User modified successfully!");
         } else {
             System.out.println("User with Id " + IdUnique + " not found.");
         }
     }
     public void deleteUser(){
         System.out.println("Enter the unique Identifier of the user to delete:");
         String IdUnique = sc.nextLine();
         User DeletedUser = users.remove(IdUnique);
         if(DeletedUser != null){
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User with ID " + IdUnique + " not found.");
        }
     }
    public void displayUsers() {
        System.out.println("List of All Accounts");
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users.values()) {
                System.out.print("id: " + user.getIdUnique() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
            }
        }
    }
    public void addConsumptionToUser(){
        System.out.println("Add new Consumption");
        System.out.println("Enter the unique Identifier of the user to Add Consumption:");
        String  IdUnique = sc.nextLine();
        User userCons= users.get(IdUnique);
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

        }

    }

}

