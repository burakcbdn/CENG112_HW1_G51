import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Box {

    private String itemType;
    private int itemCount = 0;
    private double totalWeight = 0;
    private ArrayList<Item> items = new ArrayList<>();

    //Constructor
    public Box(String itemType) {
        this.itemType = itemType;
    }

    //getters
    public double getTotalWeight() {
        return totalWeight;
    }

    public int getItemCount() {
        return itemCount;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemType() {
        return itemType;
    }

    //setters


    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    //functions
    public void addItem(Item item) {
        itemCount++;
        totalWeight += item.getWeight();
        items.add(item);
    }

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
    public boolean isBoxEmpty() {
        return itemCount == 0;
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    //creating information line for display
    public String createMainLine() {
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
