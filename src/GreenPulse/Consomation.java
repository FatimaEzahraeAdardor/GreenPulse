package GreenPulse;
import java.time.LocalDate;

public class Consomation {
    private int Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double quantity;
    public Consomation(){ }
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

    public double getAmount() {
        return quantity;
    }

    public void setAmount(double amount) {
        this.quantity = amount;
    }


}
