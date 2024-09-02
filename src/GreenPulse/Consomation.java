package GreenPulse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;


public class Consomation {
    private int Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double quantity;
    Scanner sc = new Scanner(System.in);
    public Consomation(){
    }

    public Consomation(int Id, LocalDate startDate, LocalDate endDate , double quantity) {
        this.Id = Id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return "startDate=" + startDate + ", endDate=" + endDate + ", quantity=" + quantity ;
    }

}
