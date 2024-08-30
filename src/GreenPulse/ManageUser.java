package GreenPulse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageUser {
    private List<User> users;

    public ManageUser() {
        users = new ArrayList<>();
    }
    public void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Identifiant Unique");
        int IdUnique = sc.nextInt();
        sc.nextLine();
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Age:");
        int age = sc.nextInt();
        sc.nextLine();
        User newUser = new User(IdUnique, name, age);
        users.add(newUser);
        System.out.println("User added successfully!");
    }
     public void UpdateUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the unique ID of the user to modify:");
        int IdUnique = sc.nextInt();
        sc.nextLine();
        User userModify = null;
         for (User user : users) {
             if(user.getIdUnique() == IdUnique){
                 userModify = user;
                 break;
             }
         }
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
        Scanner sc = new Scanner(System.in);
         System.out.println("Enter the unique ID of the user to delete:");
         int IdUnique = sc.nextInt();
         sc.nextLine();
         User DeletedUser = null;
        for (User user : users){
            if(user.getIdUnique() == IdUnique){
                DeletedUser= user;
                break;
            }

        }
        if(DeletedUser != null){
            users.remove(DeletedUser);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User with ID " + IdUnique + " not found.");
        }
     }
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println("users :");
                System.out.println("Identifiant Unique: " + user.getIdUnique());
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());
            }
        }
    }
}

