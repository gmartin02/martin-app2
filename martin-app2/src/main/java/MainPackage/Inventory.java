/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Gabriel Martin
 */
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

    public Item editItem(String newName, String newID, String newValue) {
        //takes in the new values for the current item
        Item editedItem = new Item();
        //sets each of the items values to the appropriate attributes
        editedItem.name = newName;
        editedItem.serialNumber = newID;
        editedItem.value = newValue;
        //returns the new Item
        return editedItem;
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
