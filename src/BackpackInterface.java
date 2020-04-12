//Backpack interface for implementing backpack

import java.util.ArrayList;

public interface BackpackInterface<T> {

    //GETTERS
    public double getCapacity();

    public int getItemCount();

    public double getCurrentWeight();

    public ArrayList<T> getItems();


    //SETTERS
    public void setCurrentWeight(double currentWeight);

    public void setDifficultyLevel(int difficultyLevel);

    public void setItemCount(int itemCount);

    public void setItems(ArrayList<T> items);

    public void setCapacity();


    //FUNCTIONS
    public boolean addItem(T item);

    public boolean isFull();

}
