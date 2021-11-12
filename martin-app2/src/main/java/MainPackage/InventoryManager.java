package MainPackage;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    Inventory inventory = new Inventory();

    public void writeToJSONFile() {
        //writes the current inventory to a JSON file
        //using the JSON format the inventory is written as an array of objects which are the items
        //each object then has its own unique attributes which follow its declaration

    }

    public void writeToHTMLFile() {
        //first a blank HTML file is created with the proper tags
        //a table is created
        //each column is assigned to each attribute of an item
        //then each item is written with the appropriate values in the columns
        //the closing tags are added last

    }

    public void writeToTSVFile() {
        //each item is written with all of its attributes separated by the \t character
        //a new line separates each item

    }

    public void loadFromJSONFile() {
        //empty the inventory first
        //in a try block
            //create gson object
            //assign inventory to hold the gson parsed data
        //catch any exceptions
    }

    public void loadFromHTMLFile() {
        //empty the inventory first
        //skip through all the tags in the file to get to each item
        //create a new temporary Item object and assign it those values
        //add the items to the empty inventory

    }

    public void loadFromTSVFile() {
        //empty the inventory first
        //while the next line is not empty
        //create a new temporary Item object and assign it those values
        //add the items to the empty inventory
    }
}
