package GreenPulse;
import java.time.LocalDate;
import java.util.*;

public class ManageUser {
    private Map<String , User> users =users = new HashMap<>();
    private Map<String ,Consomation> consomations = consomations = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public User getUserById(String id) {
        return users.get(id);
    }
    public void addUser() {
        int age;
        System.out.println(" Create New Account ");
        System.out.println(" Enter your Unique Identifier");
        String IdUnique = sc.nextLine();
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        while (true) {
            System.out.print("Enter Your Age: ");
             age = sc.nextInt();
            sc.nextLine();
            if (age>0 ){
                break;
            }
            System.out.println("age invalide. please enter again");
        };
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
    public void DisplayTotalConsumption() {
        System.out.println("Total Consumption");
        System.out.print("Enter user unique Identifier: ");
        String IdUnique = sc.nextLine();
        User user = users.get(IdUnique);
        if (user != null) {
            double totalConsumption = 0.0d;
            for (Consomation consumption : user.getConsomation()) {
                totalConsumption += consumption.getQuantity();
            }
            System.out.println("Total consumption for user " + IdUnique + " is: " + totalConsumption + " kg CO₂.");
        } else {
            System.out.println("User not found with ID: " + IdUnique);
        }
    }


}

