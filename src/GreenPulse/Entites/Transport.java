package GreenPulse.Entites;
import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.VihicleType;

import java.time.LocalDate;

public class Transport extends Consomation {
    private Double distanceTraveled;
    private VihicleType vehicleType;


    public Transport(LocalDate startDate, LocalDate endDate, double quantity, Double distanceTraveled, VihicleType vehicleType, ConsumptionType consumptionType, int user_id) {
        super(startDate, endDate, quantity, consumptionType, user_id);
        this.distanceTraveled = distanceTraveled;
        this.vehicleType = vehicleType;
    }

    public Transport() {

    }

    public Double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public VihicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VihicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public double calculerImpact() {
        double impactFactor = 0.0;
        if(this.vehicleType == VihicleType.CAR){
            impactFactor =0.5;
        } else if (this.vehicleType == VihicleType.TRAIN){
            impactFactor = 0.1;
        }
        return super.getQuantity() * impactFactor * getDistanceTraveled();
    }
}
