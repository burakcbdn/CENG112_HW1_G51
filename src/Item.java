public class Item {
    private double weight;
    private String title;
    private double itemGain;


    public Item(double weight, double itemGain,String title) {
        this.weight = weight;
        this.itemGain = itemGain;
        this.title = title;
    }

    public double getItemValue() {
        return  itemGain / weight;
    }

    public String getTitle() {
        return title;
    }

    public double getWeight() {
        return weight;
    }

}
