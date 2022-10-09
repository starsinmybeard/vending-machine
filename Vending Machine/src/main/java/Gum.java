
import java.util.Map;

public class Gum extends Item{
    public String sound = "Chew Chew, Yum!";


    public Gum(String location, String name, double price) {
        super(location, name, price);
    }

    public String getSound(){
        return this.sound;
    }

}



