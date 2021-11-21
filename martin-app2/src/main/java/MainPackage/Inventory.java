package MainPackage;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> itemList = new ArrayList<>();
    //add item and remove item functionality already exists as a part of ArrayList

    public void removeAllItems() {
        //creates an empty inventory list
        //sets the current inventory list to the empty one
        itemList = new ArrayList<>();
    }

    public Item editItem(String newName, String newID, double newValue) {
        //takes in the new values for the current item
        //sets each of the items values to the appropriate attributes
        //returns the new Item
        return null;
    }

    public boolean checkUniqueSerialNumbers(Item newItem) {
        for(int i = 0;i < itemList.size();i++) {
            if(itemList.get(i).serialNumber.equals(newItem.serialNumber)) {
                return false;
            }
        }
        return true;
    }
}
