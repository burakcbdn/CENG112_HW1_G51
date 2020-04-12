//Box interface for implementing box

import java.util.ArrayList;

public interface BoxInterface<T> {

    //GETTERS
    public double getTotalWeight();

    public int getItemCount();

    public ArrayList<T> getItems();

    public String getItemType();

    //SETTERS
    public void setItems(ArrayList<T> items);

    public void setItemCount(int itemCount);

    public void setItemType(String itemType);

    public void setTotalWeight(double totalWeight);

    //FUNCTIONS
    public void addItem(T item);

    public boolean removeItem(T itemForRemove);

    //CHECKERS
    public boolean isBoxEmpty();

    public boolean contains(T item);
}
