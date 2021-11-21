/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Gabriel Martin
 */
package MainPackage;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {

    @Test
    void writeToJSONFile() {
        InventoryManager im = new InventoryManager();
        Item newItem = new Item();
        newItem.name = "banana";
        newItem.value = "5.00";
        newItem.serialNumber = "A-123-456-789";

        im.inventory.itemList.add(newItem);
        File file = new File("docs/test/JSONwrite.json");
        im.writeToJSONFile(file);

        assertTrue(file.exists());
    }

    @Test
    void writeToHTMLFile() {
        InventoryManager im = new InventoryManager();
        Item newItem = new Item();
        newItem.name = "banana";
        newItem.value = "5.00";
        newItem.serialNumber = "A-123-456-789";

        im.inventory.itemList.add(newItem);
        File file = new File("docs/test/HTMLwrite.html");
        im.writeToHTMLFile(file);

        assertTrue(file.exists());
    }

    @Test
    void writeToTSVFile() {
        InventoryManager im = new InventoryManager();
        Item newItem = new Item();
        newItem.name = "banana";
        newItem.value = "5.00";
        newItem.serialNumber = "A-123-456-789";

        im.inventory.itemList.add(newItem);
        File file = new File("docs/test/TSVwrite.txt");
        im.writeToTSVFile(file);

        assertTrue(file.exists());
    }

    @Test
    void loadFromJSONFile() {
        InventoryManager im = new InventoryManager();
        File file = new File("docs/test/JSONtest.json");
        im.loadFromJSONFile(file);

        assertEquals("Widget", im.inventory.itemList.get(0).name);
    }

    @Test
    void loadFromHTMLFile() {
        InventoryManager im = new InventoryManager();
        File file = new File("docs/test/InventoryTest.html");
        im.loadFromHTMLFile(file);

        assertEquals("apple", im.inventory.itemList.get(0).name);
    }

    @Test
    void loadFromTSVFile() {
        InventoryManager im = new InventoryManager();
        File file = new File("docs/test/TSVtest.txt");
        im.loadFromTSVFile(file);

        assertEquals("Widget", im.inventory.itemList.get(0).name);
    }
}