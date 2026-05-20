package vehicle.repository;

import vehicle.model.Car;

import java.io.*;
import java.util.HashMap;

public class VehicleRepository{

    public void saveToFile(HashMap<Integer, Car> data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.json"))){
            writer.write("[\n");

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
            System.out.println("Error Saving File: "+e.getMessage());
        }
    }

    public void loadFromFile(HashMap<Integer, Car> data){
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