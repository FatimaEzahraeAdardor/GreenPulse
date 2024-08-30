package GreenPulse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private int IdUnique;
    private String name;
    private int age;
    private List<Consomation> consomation;

    // Constructor
    public User( int IdUnique,String name, int age ) {
        this.IdUnique = IdUnique;
        this.name = name;
        this.age = age;
        this.consomation = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdUnique() {
        return IdUnique;
    }

    public void setIdUnique(int idUnique) {
        IdUnique = idUnique;
    }

    public List<Consomation> getConsomation() {
        return consomation;
    }

    public void setConsomation(List<Consomation> consomation) {
        this.consomation = consomation;
    }

    // Method to create a user

    public void afficherDetails() {
        System.out.println("Identifiant Unique: " + this.IdUnique);
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
    }
}
