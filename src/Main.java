import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Box clothing = new Box("Clothing");
        Box foodAndDrink = new Box("Food And Drink");
        Box firstAid = new Box("First Aid");
        Box tool = new Box("Tool");

        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/items.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[] parsedItem = line.split(",");

                String itemTitle = parsedItem[0];
                int itemID = Integer.parseInt(parsedItem[1]);
                double itemWeight = Double.parseDouble(parsedItem[2]);
                double itemGain = Double.parseDouble(parsedItem[3]);

                Item item = new Item(itemWeight, itemGain, itemTitle);

                switch (itemID) {

                    case 0:
                        clothing.addItem(item);
                        break;

                    case 1:
                        foodAndDrink.addItem(item);
                        break;

                    case 2:
                        firstAid.addItem(item);
                        break;

                    case 3:
                        tool.addItem(item);
                        break;
                }

                line = reader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<Item, Double> itemValues = new HashMap<Item, Double>();

        calculateItemValues(clothing, itemValues);
        calculateItemValues(foodAndDrink, itemValues);
        calculateItemValues(firstAid, itemValues);
        calculateItemValues(tool, itemValues);

        //Sorting items by value

        Map<Item, Double> sortedItemValues = sortByValue(itemValues);

        System.out.println(itemValues);

        System.out.println("Welcome to Survival Game!");
        System.out.println("*****************************************************************");
        System.out.println(clothing.getMainLine());
        System.out.println(foodAndDrink.getMainLine());
        System.out.println(firstAid.getMainLine());
        System.out.println(tool.getMainLine());
        System.out.println("*****************************************************************");

        System.out.println("Select difficulty:");

        Backpack backpack;

        while (true) {

            System.out.println("[0] Pilgrim   [1] Voyager  [2] Stalker   [3] Interloper   [9] Exit");


            int option = scanner.nextInt();

            backpack = new Backpack(option);

            while (!backpack.isFull()){

                Item mostValuableItem = sortedItemValues.keySet().iterator().next();

                backpack.addItem(mostValuableItem);
                sortedItemValues.remove(mostValuableItem);




            }



            if (option == 9) {
                break;
            }

            System.out.println("*****************************************************************");
            System.out.println(clothing.getMainLine());
            System.out.println(foodAndDrink.getMainLine());
            System.out.println(firstAid.getMainLine());
            System.out.println(tool.getMainLine());
            System.out.println("*****************************************************************");
        }


    }

    //For calculating lifespan of items
    private static void calculateItemValues(Box box, Map<Item, Double> itemValues) {
        for (Item item : box.getItems()) {
            itemValues.put(item, item.getItemValue());
        }
    }

    public static HashMap<Item, Double> sortByValue(HashMap<Item, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Item, Double> > list =
                new LinkedList<Map.Entry<Item, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Item, Double> >() {
            public int compare(Map.Entry<Item, Double> o1,
                               Map.Entry<Item, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Item, Double> temp = new LinkedHashMap<Item,Double>();
        for (Map.Entry<Item, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }



}


