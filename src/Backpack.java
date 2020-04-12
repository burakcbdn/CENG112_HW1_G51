import java.util.ArrayList;

public class Backpack implements BackpackInterface<Item> {

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
    @Override
    public double getCapacity() {
        return this.capacity;
    }
    @Override
    public int getItemCount() {
        return itemCount;
    }
    @Override
    public double getCurrentWeight() {
        return currentWeight;
    }
    @Override
    public ArrayList<Item> getItems() {
        return items;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    //setters
    @Override
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

    @Override
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    @Override
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    //functions
    @Override
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
    @Override
    public boolean isFull() {
        return currentWeight >= capacity;
    }
}
