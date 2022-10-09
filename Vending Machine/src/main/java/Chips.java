
import java.util.Map;
public class Chips extends Item {
    String sound = "Crunch Crunch, Yum!";

    public Chips(String location, String name, double price) {
        super(location, name, price);
    }

    public String getSound(){
        return this.sound;
    }
}

