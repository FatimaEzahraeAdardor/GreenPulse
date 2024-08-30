package GreenPulse;
import java.time.LocalDate;

public class Consomation {
    private int Id;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor
    public Consomation(int Id, LocalDate startDate, LocalDate endDate) {
        this.Id = Id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void afficherDetails() {
        System.out.println("ID: " + Id);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
    }
}
