package vehicle;
import java.util.Scanner;

import vehicle.model.Car;
import vehicle.service.VehicleService;
import vehicle.controller.Menu;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        VehicleService service = new VehicleService();
        Menu menu = new Menu();
        Integer choice = Integer.valueOf(0);

        while (choice!=5){
            menu.showMenu();
            choice = sc.nextInt();
            sc.nextLine();

            // User Choice 
            switch (choice) {
                case 1:
                    int ticket = service.generateNextTicket();
                    System.out.println("----------------------------------------------");
                    //Put the details needed for each object or vehicle
                    System.out.println("Vehicle Ticket Number: "+ticket);
                    System.out.print("Enter License Plate: ");
                    String lp = sc.nextLine();
                    System.out.print("Enter Brand/Maker: ");
                    String b = sc.nextLine();
                    System.out.print("Enter Model Type: ");
                    String m = sc.nextLine();
                    System.out.print("Enter Vehicle Color: ");
                    String c = sc.nextLine();
                    System.out.print("Enter Engine Fuel Type: ");
                    String ft = sc.nextLine();

                    // Create car object
                    Car newCar = new Car(ticket, lp, b, m, c, ft);

                    // Add the object to hashmap using service
                    service.addVehicle(newCar);
                    System.out.println("Vehicle Added Successfully!");
                    System.out.println("----------------------------------------------");
                    break;
                case 2:
                    System.out.println("----------------------------------------------");
                    System.out.println("Please Enter Vehicle Ticket: ");
                    int searchTicket = sc.nextInt(); sc.nextLine();

                    if (!service.vehicleExist(searchTicket)){
                        System.out.println("No Details - Wrong Key!");
                    }else{
                        Car car = service.getVehicle(searchTicket);
                        System.out.println("License Plate: "+car.getLicensePlate());
                        System.out.println("Brand: "+car.getBrand());
                        System.out.println("Model Type: "+car.getModel());
                        System.out.println("Vehicle Color: "+car.getColor());
                        System.out.println("Engine Fuel Type: "+car.getFuelType());
                    }
                    System.out.println("----------------------------------------------");
                    break;
                case 3:
                    // Use to check the ticket and modify them
                    System.out.println("----------------------------------------------");
                    System.out.println("Please Enter Vehicle Ticket to Modify: ");
                    int inTicketMod = sc.nextInt(); sc.nextLine();
                    if (!service.vehicleExist(inTicketMod)){
                        System.out.println("No Details - Wrong Key!");
                    }else{
                        // Ask what to change
                        System.out.println("What would you like to update? (license, brand, model, color, fueltype)");
                        String attribute = sc.nextLine().trim().toLowerCase();
                        System.out.print("Enter new value: ");
                        String newValue = sc.nextLine();
                        boolean updated = service.updateVehicle(inTicketMod, attribute, newValue);
                        if (updated){
                            System.out.println("Vehicle Updated Successfully!");
                        }else{
                            System.out.println("Invalid attribute");
                        }
                    }
                    System.out.println("----------------------------------------------");
                    break;
                case 4:
                    System.out.println("----------------------------------------------");
                    System.out.print("Enter Vehicle Ticket to Delete: ");
                    int deleteTicket = sc.nextInt(); sc.nextLine();

                    if (!service.vehicleExist(deleteTicket)) {
                        System.out.println("No Details - Wrong Key!");
                    } else {
                        System.out.print("Are you sure you want to delete this vehicle? (yes/no): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        
                        if (confirm.equals("yes")) {
                            // This removes the entire ticket and its details from the data map
                            service.deleteVehicle(deleteTicket);
                            System.out.println("Vehicle record deleted successfully.");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    service.saveChanges();
                    System.out.println("----------------------------------------------");
                    break;
                case 5:
                    System.out.println("Thank you for using Vehicle Management!");
                    System.out.println("----------------------------------------------");
                    break;
                default:
                    System.out.println("Choice is Invalid! Please Try Again!");
                    break;
            }
        }
        sc.close();
    }
}
