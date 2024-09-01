package GreenPulse;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String IdUnique;
    private String name;
    private int age;
    private List<Consomation> consomation;

    public User( String IdUnique, String name, int age ) {
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

    public String getIdUnique() {
        return IdUnique;
    }

    public void setIdUnique(String idUnique) {
        IdUnique = idUnique;
    }

    public List<Consomation> getConsomation() {
        return consomation;
    }

    public void setConsomation(List<Consomation> consomation) {
        this.consomation = consomation;
    }



}
