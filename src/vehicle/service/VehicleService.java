package vehicle.service;

import vehicle.model.Car;
import vehicle.repository.VehicleRepository;

import java.util.HashMap;

public class VehicleService {
    private final HashMap<Integer, Car> data = new HashMap<>();
    private final VehicleRepository repository = new VehicleRepository();

    public VehicleService() {
        repository.loadFromFile(data);
    }

    public void addVehicle(Car car) {
        data.put(car.getTicket(), car);
        repository.saveToFile(data);
    }

    public Car getVehicle(int ticket) {
        return data.get(ticket);
    }
    
    public boolean vehicleExist(int ticket) {
        return data.containsKey(ticket);
    }

    public void deleteVehicle(int ticket){
        data.remove(ticket);
        repository.saveToFile(data);
    }

    public boolean updateVehicle(
        int ticket,
        String attribute,
        String newValue
    ){
        Car car = data.get(ticket);
        if (car == null){
            return false;
        }else{
            // Use switch to get use the setter
            switch (attribute) {
                case "license":
                    car.setLicensePlate(newValue);
                    break;
                case "brand":
                    car.setBrand(newValue);
                    break;
                case "model":
                    car.setModel(newValue);
                    break;
                case "color":
                    car.setColor(newValue);
                    break;
                case "fueltype":
                    car.setFuelType(newValue);
                    break;
                default:
                    System.out.println("Invalid attribute name.");
                    break;
            }
        }
        repository.saveToFile(data);

        return true;
    }

    public int generateNextTicket() {
        int currentMax = 0;
        for (Integer id : data.keySet()) {
            if (id > currentMax) {
                currentMax = id;
            }
        }
        return currentMax + 1;
    }

    public void saveChanges() {
        repository.saveToFile(data);
    }
}
