package GreenPulse.Entites;
import GreenPulse.Entites.Enums.ConsumptionType;

import java.time.LocalDate;
import java.util.Scanner;


public abstract class Consomation {
    private int Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double quantity;
    private int user_id;
    private ConsumptionType consumptionType;

    public Consomation(){
    }

    public Consomation(LocalDate startDate, LocalDate endDate , double quantity, ConsumptionType consumptionType, int user_id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.consumptionType = consumptionType;
        this.user_id = user_id;
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

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public abstract double calculerImpact();

    @Override
    public String toString() {
        return "Consomation{" +
                "Id=" + Id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", quantity=" + quantity +
                '}';
    }
}
