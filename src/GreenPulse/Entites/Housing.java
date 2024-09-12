package GreenPulse.Entites;

import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.EnergyType;

import java.time.LocalDate;

public class Housing extends Consomation{
        private  Double energyConsumption;
        private EnergyType energyTypes;


    public Housing(LocalDate startDate, LocalDate endDate, double quantity, Double energyConsumption, EnergyType energyTypes, ConsumptionType consumptionType, int user_id) {
        super(startDate, endDate, quantity, consumptionType, user_id);
        this.energyConsumption = energyConsumption;
        this.energyTypes = energyTypes;
    }

    public Housing() {

    }

    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public EnergyType getEnergyTypes() {
        return energyTypes;
    }

    public void setEnergyTypes(EnergyType energyTypes) {
        this.energyTypes = energyTypes;
    }

    @Override
    public double calculerImpact() {
        double impactFactor = 0.0;
        if(this.energyTypes == EnergyType.ELECTRICITY){
            impactFactor =1.5;
        } else if (this.energyTypes == EnergyType.GAZ){
            impactFactor = 2.0;
        }
        return super.getQuantity() * impactFactor * getEnergyConsumption();
      }
}
