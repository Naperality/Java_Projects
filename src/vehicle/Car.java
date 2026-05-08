package vehicle;

// Car: Vehicle, Can Drive
public class Car extends Vehicle implements Details{
    private String license, brand, model, color, fuelType;
    public Car(int ticket, String license, String brand, 
               String model, String color, String fuelType){
        super(ticket);
        this.license = license;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
    }

    public String showLicensePlate(){
        return license;
    };
    public String showBrand(){
        return brand;
    };
    public String showModel(){
        return model;
    };
    public String showColor(){
        return color;
    };
    public String showFuelType(){
        return fuelType;
    };

    // Setter Methods to acces modification
    public void setLicensePlate(String l){this.license=l;}
    public void setBrand(String b){this.brand=b;}
    public void setModel(String m){this.model=m;}
    public void setColor(String c){this.color=c;}
    public void setFuelType(String ft){this.fuelType=ft;}
}
