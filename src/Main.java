import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

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

                Item item = new Item(itemWeight,  itemGain, itemTitle);

                switch (itemID) {

                    case 0:
                        clothing.addItem(item);
                        System.out.println("Clothing added");
                        break;

                    case 1:
                        foodAndDrink.addItem(item);
                        System.out.println("food added");
                        break;

                    case 2:
                        firstAid.addItem(item);
                        System.out.println("first aid added");
                        break;

                    case 3:
                        tool.addItem(item);
                        System.out.println("tool added");
                        break;
                }

                line = reader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to Survival Game!");
        System.out.println("*****************************************************************");
        System.out.println(clothing.getMainLine());
        System.out.println(foodAndDrink.getMainLine());
        System.out.println(firstAid.getMainLine());
        System.out.println(tool.getMainLine());
        System.out.println("*****************************************************************");


    }
}


