/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Gabriel Martin
 */
package MainPackage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void removeAllItems() {
        Inventory test = new Inventory();
        test.itemList = new ArrayList<>();
        Item one = new Item();
        test.itemList.add(one);
        test.removeAllItems();

        assertEquals(new ArrayList<>(), test.itemList);
    }

    @Test
    void editItem() {
        Inventory inventory = new Inventory();
        Item act = inventory.editItem("cup", "A-111-B3C-4dg", "22.00");

        Item exp = new Item();
        exp.serialNumber = "A-111-B3C-4dg";
        exp.name = "cup";
        exp.value = "22.00";

        assertEquals(act.name, exp.name);
        assertEquals(act.serialNumber, exp.serialNumber);
        assertEquals(act.value, exp.value);
    }

    @Test
    void checkUniqueSerialNumbers() {
        Inventory inventory = new Inventory();

        Item one = new Item();
        one.serialNumber = "A-111-B3C-4dg";
        one.name = "cup";
        one.value = "22.00";

        Item two = new Item();
        two.serialNumber = "A-222-B3C-4dg";
        two.name = "cup";
        two.value = "22.00";

        Item three = new Item();
        three.serialNumber = "A-333-B3C-4dg";
        three.name = "cup";
        three.value = "22.00";

        Item four = new Item();
        four.serialNumber = "A-111-B3C-4dg";
        four.name = "cup";
        four.value = "22.00";

        inventory.itemList.add(one);
        inventory.itemList.add(two);
        inventory.itemList.add(three);

        assertFalse(inventory.checkUniqueSerialNumbers(four));
    }
}