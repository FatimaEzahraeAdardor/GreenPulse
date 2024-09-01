package GreenPulse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManageUser {
//    private List<User> users;
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
        sc.nextLine();
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
         sc.nextLine();
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

}

