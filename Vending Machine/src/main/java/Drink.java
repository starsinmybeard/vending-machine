
public class Drink extends Item {
    private String sound = "Glug Glug, Yum!";

    public Drink(String location, String name, double price) {
        super(location, name, price);
    }


    public String getSound() {
        return this.sound;
    }
}
