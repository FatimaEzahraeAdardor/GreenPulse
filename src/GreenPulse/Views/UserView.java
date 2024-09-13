package GreenPulse.Views;

import GreenPulse.Entites.User;
import GreenPulse.Services.ConsomationService;
import GreenPulse.Services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserView {
    private UserService userService = new UserService();
    private ConsomationService consomationService = new ConsomationService();
    private Scanner scanner = new Scanner(System.in);

    public void addUser() {
        System.out.println("Create Account");
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter your name.");
            }
        }
        int age = 0;
        while (age <= 0) {
            System.out.print("Enter your age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine();
                if (age <= 0) {
                    System.out.println("Invalid age. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        User user = new User(name, age);
        User savedUser = userService.create(user);

        if (savedUser != null) {
            System.out.println("User created successfully with ID: " + savedUser.getId());
        } else {
            System.out.println("Error creating user.");
        }
    }

    public void updateUser() {
        System.out.println("Update your account");
        System.out.println("Enter your id");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            String newName = "";
            while (newName.isEmpty()) {
                System.out.print("Enter your name: ");
                newName = scanner.nextLine().trim();
                if (newName.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter your name.");
                } else {
                    user.setName(newName);
                }
            }
            int newAge = 0;
            while (newAge <= 0) {
                System.out.print("Enter your age: ");
                if (scanner.hasNextInt()) {
                    newAge = scanner.nextInt();
                    scanner.nextLine();
                    user.setAge(newAge);
                    if (newAge <= 0) {
                        System.out.println("Invalid age. Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }

            }
            Optional<User> updatedUser = userService.update(user);
            if (updatedUser.isPresent()) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Error updating user.");
            }
        } else {
            System.out.println("User with ID " + id + " not found.");
        }


    }
    public void deleteUser(){
        System.out.println("delete your account");
        System.out.println("Enter your id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<User> existingUser = userService.findById(id);
        if(existingUser.isPresent()){
            User user = existingUser.get();
             userService.delete(user);
            System.out.println("User deleted successfully.");
        }else {
            System.out.println("User with ID " + id + " not found.");
        }
    }
    public void displayAllUsers(){
        List<User> users = userService.findAll();
        System.out.println("list of all account");
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
            }
        }
    }
    public void ShowAllUsersWithConsumptionGreater(){
        System.out.println("  Users with a total consumption greater than 3000 KgCO2eq  ");
        List<User> users = userService.FindserWithConsumption();
        if(!users.isEmpty()){
            System.out.println("\n+--------------------+--------------------+--------------------+-------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n","ID", "Name", "Age" ,"total consumption");
            System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            for (User user : users){
                System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n", user.getId(), user.getName(), user.getAge(),userService.calculConsumptionTotalForUser(user.getId()));
                System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            }
        }else {
            System.out.println("\n Users not found \n");
        }

    }
    public void showUsersInactifs(){
        System.out.println("list of users inactifs");
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD)");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        List<User> users = userService.getUsersInactifs(startDate,endDate);
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
            }
        }
    }
    public void getavgConsumption(){
        System.out.println("Enter your Id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD)");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        double avg = userService.calculAverageConsumption(id,startDate,endDate);
        System.out.println("\n Average consumption of user : " + id + " in period between  : " +startDate +" & "+endDate+ " is : " + avg +"  KgCO2eq");

    }
    public void sortUserByTotalConsumption(){
        System.out.println("list of users sorted by total consumption");
        List<User> users = userService.sortUserByTotalConsumption();
        for (User user : users){
            System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "tatal consumption:" + userService.calculConsumptionTotalForUser(user.getId()) + "\n");
        }

    }

}

