package vehicle;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        // Creating a list of car objects
        ArrayList<Drivable> fleet = new ArrayList<>();

        // Add Different objects to fleet (Polymorphism)
        fleet.add(new Car("Toyota"));
        fleet.add(new Truck("Mitsubishi"));

        //Use for each loop to run the object

        for (Drivable v:fleet){
            v.startEngine();
            v.accelerate(50);
            System.out.println("-----");
        }
        // Car myCar = new Car("Toyota");
        // myCar.startEngine();
        // myCar.accelerate(70);

        // Truck myTruck = new Truck("Mitsubishi");
        // myTruck.startEngine();
        // myTruck.accelerate(20);
    }
}
