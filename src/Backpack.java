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

    //getters
    public double getCapacity() {
        return this.capacity;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    //setters
    public void setCapacity() {
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

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    //functions
    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() < capacity) {
            currentWeight += item.getWeight();
            items.add(item);
            itemCount++;
            return true;
        } else {
            return false;
        }
    }

    public int calculateLifeSpan() {
        int totalLifeSpan = 0;
        for (Item item : items) {
            totalLifeSpan += item.getItemGain();
        }
        return totalLifeSpan;
    }

    //checkers
    public boolean isFull() {
        return currentWeight >= capacity;
    }
}
