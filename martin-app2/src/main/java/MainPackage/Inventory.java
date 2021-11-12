package MainPackage;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public void addItem(String name, String id, double value) {
        //creates a new item with the parameters as its attributes
        //appends this item to the end of the list
    }

    public void removeItem(Item selectedItem) {
        //using the parameter removes the item using the remove() method in the arrayList
    }

    public void removeAllItems() {
        //creates an empty inventory list
        //sets the current inventory list to the empty one
    }

    public Item editItem(String newName, String newID, double newValue) {
        //takes in the new values for the current item
        //sets each of the items values to the appropriate attributes
        //returns the new Item
        return null;
    }
}
