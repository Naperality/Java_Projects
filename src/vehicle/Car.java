package vehicle;

// Car: Vehicle, Can Drive
public class Car extends Vehicle implements Drivable{
    public Car(String brand){
        super(brand);
    }

    @Override
    public void startEngine(){
        System.out.println("The "+getBrand()+" engine is roaring!");
    }

    @Override
    public void accelerate(int speed){
        System.out.println("Accelerating to "+speed+" km/h.");
    }
}
