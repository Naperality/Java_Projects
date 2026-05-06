package vehicle;

public class Truck extends Vehicle implements Drivable{
    public Truck(String brand){
        super(brand);
    }

    @Override
    public void startEngine(){
        System.out.println("Engine Starting for "+getBrand());
    }

    @Override
    public void accelerate(int speed){
        System.out.println("Accelerate to "+speed);
    }
}
