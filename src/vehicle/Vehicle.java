package vehicle;

public abstract class Vehicle {
    private int ticket; // Encapsulation: Private Variable
    
    public Vehicle(int ticket){
        this.ticket = ticket;
    }

    // getter method to access the private brand
    public Integer getTicket(){
        return ticket;
    }
    
}
