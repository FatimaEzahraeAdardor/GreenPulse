package GreenPulse.Entites;
import GreenPulse.Entites.Consomation;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Consomation> consomation;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.consomation = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public List<Consomation> getConsomation() {
        return consomation;
    }

    public void setConsomation(List<Consomation> consomation) {
        this.consomation = consomation;
    }

    @Override
    public String toString() {
        return "User{" +
                "IdUnique='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", consomation=" + consomation +
                '}';
    }


}