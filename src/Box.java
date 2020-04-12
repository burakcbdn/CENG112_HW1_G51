import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Box implements BoxInterface<Item>{

    private String itemType;
    private int itemCount = 0;
    private double totalWeight = 0;
    private ArrayList<Item> items = new ArrayList<>();

    //Constructor
    Box(String itemType) {
        this.itemType = itemType;
    }

    //getters
    @Override
    public double getTotalWeight() {
        return totalWeight;
    }
    @Override
    public int getItemCount() {
        return itemCount;
    }
    @Override
    public ArrayList<Item> getItems() {
        return items;
    }
    @Override
    public String getItemType() {
        return itemType;
    }

    //setters
    @Override
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    @Override
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    @Override
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    @Override
    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    //functions
    @Override
    public void addItem(Item item) {
        itemCount++;
        totalWeight += item.getWeight();
        items.add(item);
    }
    @Override
    public boolean removeItem(Item itemForRemove) {
        if (items.contains(itemForRemove)) {
            totalWeight -= itemForRemove.getWeight();
            items.remove(itemForRemove);
            itemCount--;
            return true;
        } else {
            return false;
        }
    }

    //checkers
    @Override
    public boolean isBoxEmpty() {
        return itemCount == 0;
    }
    @Override
    public boolean contains(Item item) {
        return items.contains(item);
    }

    //creating information line for display
    String createMainLine() {
        BigDecimal w = new BigDecimal(totalWeight);
        w = w.setScale(2, RoundingMode.HALF_EVEN);

        double we = w.doubleValue();

        switch (itemType) {
            case "Clothing":
                return itemType + "                    " + itemCount + " items    |  " + we + " kg";
            case "Food And Drink":
                return itemType + "              " + itemCount + " items     |  " + we + " kg";
            case "First Aid":
                return itemType + "                   " + itemCount + " items     |  " + we + " kg";
            case "Tool":
                return itemType + "                        " + itemCount + " items     |  " + we + " kg";
        } return "";
    }
}
