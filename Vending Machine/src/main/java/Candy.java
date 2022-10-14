import java.util.Map;

    public class Candy extends Item {
        public String sound = "Munch Munch, Yum!";


        public Candy(String location, String name, double price) {
            super(location, name, price);
        }

        public String getSound(){
            return this.sound;
        }
    }

