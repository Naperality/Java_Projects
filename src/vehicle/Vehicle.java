package vehicle;

public abstract class Vehicle {
    private String brand; // Encapsulation: Private Variable
    
    public Vehicle(String brand){
        this.brand = brand;
    }

    // getter method to access the private brand
    public String getBrand(){
        return brand;
    }
    
}
