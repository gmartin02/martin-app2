package MainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    Inventory inventory = new Inventory();

    public void writeToJSONFile(File file) {
        //writes the current inventory to a JSON file
        //using the JSON format the inventory is written as an array of objects which are the items
        //each object then has its own unique attributes which follow its declaration
        try(Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(inventory, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToHTMLFile(File file) {
        try {
            //create a buffered reader insider a try/catch block
            //first a blank HTML file is created with the proper tags
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("""
                        <!DOCTYPE html>
                        <html>
                        <body>
                        <table>
                        <tr>
                        <th>
                        Value
                        </th>
                        <th>
                        Serial Number
                        </th>
                        <th>
                        Name
                        </th>
                        </tr>""");
                //for loop through the size of the item list
                //each column is assigned to each attribute of an item
                //then each item is written with the appropriate values in the columns
                for(int i = 0;i < inventory.itemList.size(); i++) {
                    bw.write("<tr>\n");
                    bw.write("<td>\n" + inventory.itemList.get(i).value + "\n</td>\n");
                    bw.write("<td>\n" + inventory.itemList.get(i).serialNumber + "\n</td>\n");
                    bw.write("<td>\n" + inventory.itemList.get(i).name + "\n</td>\n");
                    bw.write("</tr>\n");
                }
                //the closing tags are added last
                bw.write("""
                        </table>
                        </body>
                        </html>
                                """);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToTSVFile(File file) {
        try {
            //create a buffered reader insider a try/catch block
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                //for loop through the size of the item list
                for(int i = 0;i < inventory.itemList.size(); i++) {
                    //each item is written with all of its attributes separated by the \t character
                    //a new line separates each item
                    bw.write(inventory.itemList.get(i).serialNumber + "\t");
                    bw.write(inventory.itemList.get(i).name + "\t");
                    bw.write(String.valueOf(inventory.itemList.get(i).value + "\n"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromJSONFile(File selectedFile) {
        //empty the inventory first
        inventory.removeAllItems();
        //in a try block
        try {
            //create gson object
            Gson gson = new Gson();
            //assign inventory to hold the gson parsed data
            inventory = gson.fromJson(new FileReader(selectedFile.getPath()), Inventory.class);
        //catch any exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromHTMLFile(File selectedFile) {
        //empty the inventory first
        inventory.removeAllItems();
        //skip through all the tags in the file to get to each item
        //create a new temporary Item object and assign it those values
        //add the items to the empty inventory
        List<String> htmlInput = new ArrayList<>();
        List<String> validInput = new ArrayList<>();
        try {
            try (Scanner sc = new Scanner(selectedFile)) {
                while (sc.hasNext()) {
                    htmlInput.add(sc.nextLine());
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < htmlInput.size(); i++) {
            if(!htmlInput.get(i).contains("<")) {
                validInput.add(htmlInput.get(i));
            }
        }
        validInput.remove(0);
        validInput.remove(0);
        validInput.remove(0);

        System.out.println(validInput);

        for(int j = 0; j < validInput.size(); j+=3) {
            Item item = new Item();
            item.value = Double.parseDouble(validInput.get(j));
            item.serialNumber = validInput.get(j+1);
            item.name = validInput.get(j+2);
            inventory.itemList.add(item);
        }
    }

    public void loadFromTSVFile(File selectedFile) {
        //empty the inventory first
        inventory.removeAllItems();
        //while the next line is not empty
        try {
            try (Scanner sc = new Scanner(selectedFile)) {
                while (sc.hasNext()) {
                    String currentItem = sc.nextLine();
                    String[] itemValues = currentItem.split("\t");
                    Item item = new Item();
                    item.value = Double.parseDouble(itemValues[2]);
                    item.serialNumber = itemValues[0];
                    item.name = itemValues[1];
                    inventory.itemList.add(item);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //create a new temporary Item object and assign it those values
        //add the items to the empty inventory
    }
}
