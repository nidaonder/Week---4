import java.util.Random;

public class Snake  extends Monster{
    public Snake() {
        super(4, "Yılan", 0,12,0);
        Random random = new Random();
        int randomDamage = random.nextInt(3,7);
        setDamage(randomDamage);
    }
}