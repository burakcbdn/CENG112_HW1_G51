import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Box clothing = new Box("Clothing");
        Box foodAndDrink = new Box("Food And Drink");
        Box firstAid = new Box("First Aid");
        Box tool = new Box("Tool");

        ArrayList<Box> boxes = new ArrayList<>();
        boxes.add(clothing);
        boxes.add(foodAndDrink);
        boxes.add(firstAid);
        boxes.add(tool);


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

        Map<Item, Double> sortedItemValues = itemValues.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println(itemValues);

        System.out.println("Welcome to Survival Game!");
        System.out.println("*****************************************************************");
        System.out.println(clothing.getMainLine());
        System.out.println(foodAndDrink.getMainLine());
        System.out.println(firstAid.getMainLine());
        System.out.println(tool.getMainLine());
        System.out.println("*****************************************************************");

        System.out.println("Select difficulty:");

        boolean status = true;

        Backpack backpack;


        while (status) {


            System.out.println(status);
            System.out.println("[0] Pilgrim   [1] Voyager  [2] Stalker   [3] Interloper   [9] Exit");


            int option = scanner.nextInt();

            backpack = new Backpack(option);

            while (true) {

                try {
                    Item mostValuableItem = sortedItemValues.keySet().iterator().next();

                    if (backpack.addItem(mostValuableItem)) {
                        sortedItemValues.remove(mostValuableItem);
                        useItem(mostValuableItem, boxes);
                    } else {
                        break;
                    }

                } catch (NoSuchElementException e) {
                    break;
                }


            }

            if (option == 9) {
                break;
            }


            System.out.println("*****************************************************************");
            System.out.println(clothing.getMainLine());
            System.out.println(foodAndDrink.getMainLine());
            System.out.println(firstAid.getMainLine());
            System.out.println(tool.getMainLine());
            System.out.println(backpack.getCurrentWeight());
            System.out.println("*****************************************************************");

            status = !clothing.isBoxEmpty() || !foodAndDrink.isBoxEmpty() || !firstAid.isBoxEmpty() || !tool.isBoxEmpty();
        }


    }

    //For calculating lifespan of items
    private static void calculateItemValues(Box box, Map<Item, Double> itemValues) {
        for (Item item : box.getItems()) {
            itemValues.put(item, item.getItemValue());
        }
    }

    public static HashMap<Item, Double> sortByValue11(HashMap<Item, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Item, Double>> list =
                new LinkedList<Map.Entry<Item, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Item, Double>>() {
            public int compare(Map.Entry<Item, Double> o1,
                               Map.Entry<Item, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Item, Double> temp = new LinkedHashMap<Item, Double>();
        for (Map.Entry<Item, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void useItem(Item item, ArrayList<Box> boxes) {
        for (Box box : boxes) {
            if (box.contains(item)) {
                box.removeItem(item);
            }
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }


}


