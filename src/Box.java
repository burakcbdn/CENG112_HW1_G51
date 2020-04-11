
import java.util.ArrayList;


public class Box {

    private String itemType;
    private int itemCount = 0;

    private double totalWeight = 0;

    private ArrayList<Item> items = new ArrayList<Item>();

    //Constructor
    public Box(String itemType) {
        this.itemType = itemType;
    }


    public void addItem(Item item) {
        itemCount++;
        totalWeight += item.getWeight();
        items.add(item);
    }

    public boolean removeItem(Item itemForRemove){
        if (items.contains(itemForRemove)) {
            items.remove(itemForRemove);
            return true;
        } else {
            return false;
        }
    }

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

    public boolean isEmpty() {
        return itemCount == 0;
    }

    public String getMainLine() {

        switch (itemType) {
            case "Clothing":
                return itemType + "                    " + itemCount + " items    |  " + totalWeight + " kg";
            case "Food And Drink":
                return itemType + "              " + itemCount + " items     |  " + totalWeight + " kg";
            case "First Aid":
                return itemType + "                   " + itemCount + " items     |  " + totalWeight + " kg";
            case "Tool":
                return itemType + "                        " + itemCount + " items     |  " + totalWeight + " kg";

        }
        return "";

    }

}
