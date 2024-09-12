package GreenPulse.Entites;

import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.FoodType;

import java.time.LocalDate;

public class Food extends Consomation{
    private FoodType foodType;
    private double weight;

    public Food(LocalDate startDate, LocalDate endDate, double quantity, FoodType foodType, double weight, ConsumptionType consumptionType, int user_id) {
        super( startDate, endDate, quantity, consumptionType, user_id);
        this.foodType = foodType;
        this.weight = weight;
    }

    public Food() {

    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double calculerImpact() {
        double impactFactor = 0.0;
        if(this.foodType == FoodType.MEAT){
            impactFactor =5.0;
        }else if (this.foodType == FoodType.VEGETABLE){
            impactFactor = 0.5;
        }
        return super.getQuantity() * impactFactor * getWeight();
    }
}
