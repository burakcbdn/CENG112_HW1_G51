import java.util.ArrayList;

public class Backpack {

    private int difficultyLevel;

    private double capacity;
    private double currentWeight;
    private int itemCount;

    private ArrayList<Item> items = new ArrayList<Item>();


    public Backpack(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        setCapacity();
    }

    public boolean addItem(Item item) {
            if(currentWeight + item.getWeight() < capacity) {
                currentWeight += item.getWeight();
                items.add(item);
                itemCount++;
                return true;
            }else {
                return false;
            }



    }

    public boolean isFull(){
        return currentWeight >= capacity;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    private void setCapacity() {
        switch (difficultyLevel) {
            case 0:
                this.capacity = 9;
                break;
            case 1:
                this.capacity = 7;
                break;
            case 2:
                this.capacity = 5;
                break;
            case 3:
                this.capacity = 3;
                break;
        }
    }


}
