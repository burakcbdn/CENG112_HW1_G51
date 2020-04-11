import java.lang.reflect.Array;
import java.util.List;

public class Backpack {

    private int difficultyLevel;

    private double capacity;


    public Backpack(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setCapacity() {
        switch (difficultyLevel) {
            case 0:
                this.capacity = 9;
            case 1:
                this.capacity = 7;
            case 2:
                this.capacity = 5;
            case 3:
                this.capacity = 3;
        }
    }

    public double getCapacity() {
        return this.capacity;
    }
}
