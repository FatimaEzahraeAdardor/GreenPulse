package GreenPulse.Services;
import GreenPulse.Entites.Consomation;
import GreenPulse.Entites.User;

import java.util.*;

public class ManageUser {
    private Map<Long , User> users =users = new HashMap<>();
    private Map<Long , Consomation> consomations = consomations = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public User getUserById(String id) {
        return users.get(id);
    }
    public void addUser() {
        int age;
        System.out.println(" Create New Account ");
        System.out.println(" Enter your Unique Identifier");
        Long id = sc.nextLong();
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
        if (users.containsKey(id)){
            System.out.println("User with this ID already exists.");
        } else {
            User newUser = new User(name, age);
            users.put(id, newUser);
            System.out.println("User added successfully!");
        }
    }
     public void UpdateUser(){
         System.out.println("Modify Account");
         System.out.println("Enter the unique Identifier of the user to modify:");
        Long id = sc.nextLong();
         User userModify = users.get(id);
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
             System.out.println("User with Id " + id + " not found.");
         }
     }
     public void deleteUser(){
         System.out.println("Enter the unique Identifier of the user to delete:");
         Long id = sc.nextLong();
         User DeletedUser = users.remove(id);
         if(DeletedUser != null){
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
     }
    public void displayUsers() {
        System.out.println("List of All Accounts");
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users.values()) {
                System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
            }
        }
    }
    public void DisplayTotalConsumption() {
        System.out.println("Total Consumption");
        System.out.print("Enter user unique Identifier: ");
        Long id = sc.nextLong();
        User user = users.get(id);
        if (user != null) {
            double totalConsumption = 0.0d;
            for (Consomation consumption : user.getConsomation()) {
                totalConsumption += consumption.getQuantity();
            }
            System.out.println("Total consumption for user " + id + " is: " + totalConsumption + " kg CO₂.");
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }


}

