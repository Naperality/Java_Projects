package vehicle;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Car> data = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        // 1. LOAD FIRST
        loadFromFile(data);

        Integer inputTicket = Integer.valueOf(0);
        Integer inTicketMod = Integer.valueOf(0);
        Integer choice = Integer.valueOf(0);

        while (choice!=5){
            // User Interface for Management
            System.out.println("-----Welcome to Vehicle Management System-----");
            System.out.println("[1] Add New Vehicle");
            System.out.println("[2] View Vehicle Details");
            System.out.println("[3] Modify Vehicle Details");
            System.out.println("[4] Delete Vehicle");
            System.out.println("[5] Exit");
            System.out.println("----------------------------------------------");
            choice = sc.nextInt();
            sc.nextLine();
            // User Choice 
            switch (choice) {
                case 1:
                    // we calculate the next ID dynamically from the current keys.
                    int currentMax = 0;
                    for (Integer id : data.keySet()) {
                        if (id > currentMax) currentMax = id;
                    }
                    int ticket = currentMax + 1;
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

                    // Add the object to hashmap
                    data.put(ticket, newCar);
                    System.out.println("Vehicle Added Successfully!");
                    saveToFile(data);
                    System.out.println("----------------------------------------------");
                    break;
                case 2:
                    System.out.println("----------------------------------------------");
                    System.out.println("Please Enter Vehicle Ticket: ");
                    inputTicket = sc.nextInt(); sc.nextLine();
                    if (!data.containsKey(inputTicket)){
                        System.out.println("No Details - Wrong Key!");
                    }else{
                        Car car = data.get(inputTicket);
                        System.out.println("License Plate: "+car.showLicensePlate());
                        System.out.println("Brand: "+car.showBrand());
                        System.out.println("Model Type: "+car.showModel());
                        System.out.println("Vehicle Color: "+car.showColor());
                        System.out.println("Engine Fuel Type: "+car.showFuelType());
                    }
                    System.out.println("----------------------------------------------");
                    break;
                case 3:
                    // Use to check the ticket and modify them
                    System.out.println("----------------------------------------------");
                    System.out.println("Please Enter Vehicle Ticket to Modify: ");
                    inTicketMod = sc.nextInt(); sc.nextLine();
                    if (!data.containsKey(inTicketMod)){
                        System.out.println("No Details - Wrong Key!");
                    }else{
                        // get the car object
                        Car vehicle = data.get(inTicketMod);

                        // Ask what to change
                        System.out.println("What would you like to update? (license, brand, model, color, fueltype)");
                        String attribute = sc.nextLine().trim().toLowerCase();
                        System.out.print("Enter new value: ");
                        String newValue = sc.nextLine();

                        // Use switch to get use the setter
                        switch (attribute) {
                            case "license":
                                vehicle.setLicensePlate(newValue);
                                break;
                            case "brand":
                                vehicle.setBrand(newValue);
                                break;
                            case "model":
                                vehicle.setModel(newValue);
                                break;
                            case "color":
                                vehicle.setColor(newValue);
                                break;
                            case "fueltype":
                                vehicle.setFuelType(newValue);
                                break;
                            default:
                                System.out.println("Invalid attribute name.");
                                break;
                        }
                    }
                    saveToFile(data);
                    System.out.println("----------------------------------------------");
                    break;
                case 4:
                    System.out.println("----------------------------------------------");
                    System.out.print("Enter Vehicle Ticket to Delete: ");
                    int deleteTicket = sc.nextInt(); sc.nextLine();

                    if (!data.containsKey(deleteTicket)) {
                        System.out.println("No Details - Wrong Key!");
                    } else {
                        System.out.print("Are you sure you want to delete this vehicle? (yes/no): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        
                        if (confirm.equals("yes")) {
                            // This removes the entire ticket and its details from the data map
                            data.remove(deleteTicket); 
                            System.out.println("Vehicle record deleted successfully.");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    saveToFile(data);
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

    public static void saveToFile(HashMap<Integer, Car> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.json"))) {
            writer.write("[\n"); // Start of JSON array
            int count = 0;
            for (Car car : data.values()) {
                String json = String.format(
                    "  {\n" +
                    "    \"ticket\": %d,\n" +
                    "    \"license\": \"%s\",\n" +
                    "    \"brand\": \"%s\",\n" +
                    "    \"model\": \"%s\",\n" +
                    "    \"color\": \"%s\",\n" +
                    "    \"fuelType\": \"%s\"\n" +
                    "  }",
                    car.getTicket(), car.showLicensePlate(), car.showBrand(), 
                    car.showModel(), car.showColor(), car.showFuelType()
                );
                writer.write(json);
                
                // Add a comma between objects, but not after the last one
                if (++count < data.size()) writer.write(",");
                writer.write("\n");
            }
            writer.write("]"); // End of JSON array
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }

    public static void loadFromFile(HashMap<Integer, Car> data) {
        File file = new File("vehicles.json");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int ticket = 0;
            String lp = "", b = "", m = "", c = "", ft = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"ticket\":")) ticket = Integer.parseInt(line.split(":")[1].replaceAll("[,\\s]", ""));
                else if (line.startsWith("\"license\":")) lp = extractValue(line);
                else if (line.startsWith("\"brand\":")) b = extractValue(line);
                else if (line.startsWith("\"model\":")) m = extractValue(line);
                else if (line.startsWith("\"color\":")) c = extractValue(line);
                else if (line.startsWith("\"fuelType\":")) ft = extractValue(line);
                else if (line.equals("}") || line.equals("},")) {
                    // When we hit the closing brace, create the car object
                    data.put(ticket, new Car(ticket, lp, b, m, c, ft));
                    // Reset variables to prevent data bleeding into the next object
                    lp = ""; b = ""; m = ""; c = ""; ft = "";
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading JSON: " + e.getMessage());
        }
    }

    // Helper method to pull the text out from between the quotes
    private static String extractValue(String line) {
        return line.split(":")[1].replaceAll("[\",]", "").trim();
    }
}
