public class Item {
    private double weight;
    private String title;
    private double itemGain;


    public Item(double weight, double itemGain,String title) {
        this.weight = weight;
        this.itemGain = itemGain;
        this.title = title;
    }

    //getters
    public double getItemGain() {
        return itemGain;
    }

    public String getTitle() {
        return title;
    }

    public double getWeight() {
        return weight;
    }

    //setters
    public void setItemGain(double itemGain) {
        this.itemGain = itemGain;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    //calculating value of item
    public double calculateItemValue() {
        return  itemGain / weight;
    }

}
