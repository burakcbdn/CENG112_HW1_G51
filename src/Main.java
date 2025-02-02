import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
import static java.util.stream.Collectors.toMap;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Creating item boxes by type.
        Box clothing = new Box("Clothing");
        Box foodAndDrink = new Box("Food And Drink");
        Box firstAid = new Box("First Aid");
        Box tool = new Box("Tool");

        ArrayList<Box> boxes = new ArrayList<>();
        boxes.add(clothing);
        boxes.add(foodAndDrink);
        boxes.add(firstAid);
        boxes.add(tool);

        //reading items from file
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
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //creating hashmap for items and their values

        HashMap<Item, Double> itemValues = new HashMap<>();

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


        System.out.println("Welcome to Survival Game!");
        System.out.println("*****************************************************************");
        System.out.println(clothing.createMainLine());
        System.out.println(foodAndDrink.createMainLine());
        System.out.println(firstAid.createMainLine());
        System.out.println(tool.createMainLine());
        System.out.println("*****************************************************************");

        Backpack backpack;
        
        //Main loop

        boolean status = true;
        while (status) {
        	try {
        		System.out.println("Select difficulty:");
                System.out.println("[0] Pilgrim   [1] Voyager  [2] Stalker   [3] Interloper   [9] Exit");
                
                
                //Taking input from user.
                int option = scanner.nextInt();

                Integer[] optionsList = {0,1,2,3,9};
                List<Integer> options = Arrays.asList(optionsList);

                if (!options.contains(option)) {
                    System.out.println("Please select again!");
                    continue;
                }
                
                if (option == 9) {
                    break;
                }


                backpack = new Backpack(option);
                
                //Filling the backpack with most valuable items.

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

                
                System.out.println("*****************************************************************");
                System.out.println(clothing.createMainLine());
                System.out.println(foodAndDrink.createMainLine());
                System.out.println(firstAid.createMainLine());
                System.out.println(tool.createMainLine());
                System.out.println("Backpack                    " + backpack.getItemCount() + " items    |  " + backpack.getCurrentWeight() + " kg");
                System.out.println("Lifespan                    " + backpack.calculateLifeSpan() + " days");
                System.out.println("*****************************************************************");
                
                //Checking all the boxes if they are empty.
                status = !clothing.isBoxEmpty() || !foodAndDrink.isBoxEmpty() || !firstAid.isBoxEmpty() || !tool.isBoxEmpty();
        		
        	//Handling InputMismatchException.
        	}catch (InputMismatchException e) {
        		System.out.println("You entered invalip ınput. Please try again!");
        		scanner.next();
        	}

            
        }
        System.out.println("No items left in the boxes");
        scanner.close();
    }

    //calculating value of items
    private static void calculateItemValues(Box box, Map<Item, Double> itemValues) {
        for (Item item : box.getItems()) {
            itemValues.put(item, item.calculateItemValue());
        }
    }
    
    //Adding items to backpack
    private static void useItem(Item item, ArrayList<Box> boxes) {
        for (Box box : boxes) {
            if (box.contains(item)) {
                box.removeItem(item);
            }
        }
    }
}
