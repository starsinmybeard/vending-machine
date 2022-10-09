
import java.util.Map;

public class Item {
    private String location;
    private String name;
    private double price;
    private int quantity;

    public Item(String location, String name, double price) {
        this.location = location;
        this.name = name;
        this.price = price;
        this.quantity = 5;
    }


    //called in the purchase method, subtracts quantity and prints sound
    public void dispenseItem(Item itemSelection){
        if(quantity > 0){
            quantity --;
        } else {
            System.out.println("Sorry - This Item is Out of Stock" );
        }
        System.out.println("You Purchased: " + itemSelection.getName());
        System.out.println(itemSelection.getSound());
    }

    public String getSound(){
        return getSound();
    }


    //Getters and Setters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //To String (String Print out when Customer selects 1 from the main menu
    @Override
    public String toString(){
        String pricePrint = String.format("%.2f", price);
        return location + ") " +  name + " - " + "$" + pricePrint + " (" + quantity + " in stock)\n";
    }
}

