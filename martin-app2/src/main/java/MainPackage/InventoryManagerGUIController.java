package MainPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerGUIController implements Initializable {
    ObservableList<Item> obsInventory = FXCollections.observableArrayList();

    InventoryManager inventoryManager = new InventoryManager();

    @FXML
    public TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> valueColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private Label fileError;
    @FXML
    private Label itemError;
    @FXML
    private TextField valueField;
    @FXML
    private TextField serialNumberField;
    @FXML
    private TextField nameField;

    public void addItemOnButtonPress() {
        int errorFlag = 0;
        Item newItem = new Item();
        //ensure name is between [2, 256] characters
        if(nameField.getText().length() >= 2 && nameField.getText().length() <= 256) {
            newItem.name = nameField.getText();
        } else {
            errorFlag = 1;
        }
        //ensure value >= 0
        if(Double.parseDouble(valueField.getText()) >= 0) {
            newItem.value = valueField.getText();
        } else {
            errorFlag = 1;
        }
        //ensure the serial number is in the correct format
        if(newItem.checkSerialNumberFormat(serialNumberField.getText())) {
            newItem.serialNumber = serialNumberField.getText();
        } else {
            errorFlag = 1;
        }
        //ensure the serial number is unique
        if(!inventoryManager.inventory.checkUniqueSerialNumbers(newItem)) {
            errorFlag = 2;
        }

        if(errorFlag == 0) {
            //call the addItem method
            inventoryManager.inventory.itemList.add(newItem);
        } else if(errorFlag == 1){
            //display an error message if any requirement is not met
            itemError.setText("Please enter valid data.");
        } else {
            itemError.setText("Please enter a unique serial number.");
        }
        //load the table to display the updated inventory
        loadTable(inventoryManager);

        //clear the text fields
        nameField.setText("");
        valueField.setText("");
        serialNumberField.setText("");
    }

    public void removeItemOnButtonPress() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //remove the selected item in the table from the display
        if(inventoryManager.inventory.itemList.contains(selectedItem)) {
            //call the remove method to get rid of it from the code
            obsInventory.remove(selectedItem);
            inventoryManager.inventory.itemList.remove(selectedItem);
            //load the table to display the updated inventory
            loadTable(inventoryManager);
        }
    }

    public void removeAllItemsOnButtonPress() {
        //clear the entire table
        tableView.getItems().removeAll(obsInventory);
        //call the removeAllItems method
        inventoryManager.inventory.removeAllItems();
        obsInventory.removeAll();
        loadTable(inventoryManager);
    }

    public void checkForEditedSerialNumber(TableColumn.CellEditEvent<String, String> cell) {
        //get the item that you want to edit
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //remove the selected item from the inventory temporarily
        inventoryManager.inventory.itemList.remove(selectedItem);
        obsInventory.remove(selectedItem);
        //get the new value of the edited item
        String newSerialNumber = cell.getNewValue();
        //ensure the new number has the right format
        if(selectedItem.checkSerialNumberFormat(newSerialNumber)) {
            //save the old sn
            String oldSerialNumber = selectedItem.serialNumber;
            selectedItem.serialNumber = newSerialNumber;
            //ensure new sn is unique
            if(inventoryManager.inventory.checkUniqueSerialNumbers(selectedItem)) {
                //put it into the itemList
                Item editedItem = inventoryManager.inventory.editItem(selectedItem.name, selectedItem.serialNumber, selectedItem.value);
                inventoryManager.inventory.itemList.add(editedItem);
                obsInventory.add(editedItem);
            } else {
                //return to original state
                selectedItem.serialNumber = oldSerialNumber;
                inventoryManager.inventory.itemList.add(selectedItem);
                obsInventory.add(selectedItem);
            }
        } else {
            //return to original state
            inventoryManager.inventory.itemList.add(selectedItem);
            obsInventory.add(selectedItem);
        }

        //reload the table
        loadTable(inventoryManager);
    }

    public void checkForEditedName(TableColumn.CellEditEvent<String, String> cell) {
        //get the item that you want to edit
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //remove the selected item from the inventory temporarily
        inventoryManager.inventory.itemList.remove(selectedItem);
        obsInventory.remove(selectedItem);
        //get the new value of the edited item
        String newName = cell.getNewValue();
        //ensure the new name hsa the proper length
        if(newName.length() >= 2 && newName.length() <= 256) {
            //set the item to have the new name
            selectedItem.name = newName;
        }
        //add it back into the inventory
        inventoryManager.inventory.itemList.add(selectedItem);
        obsInventory.add(selectedItem);

        //reload the table
        loadTable(inventoryManager);
    }

    public void checkForEditedValue(TableColumn.CellEditEvent<String, String> cell) {
        //get the item that you want to edit
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //remove the selected item from the inventory temporarily
        inventoryManager.inventory.itemList.remove(selectedItem);
        obsInventory.remove(selectedItem);
        //get the new value of the edited item
        String newValue = cell.getNewValue();
        //ensure the new name hsa the proper length
        if(Double.parseDouble(newValue) >= 0) {
            //set the item to have the new name
            selectedItem.value = newValue;
        }
        //add it back into the inventory
        inventoryManager.inventory.itemList.add(selectedItem);
        obsInventory.add(selectedItem);

        //reload the table
        loadTable(inventoryManager);
    }

    public void loadInventoryOnButtonPress() {
        //creates a file chooser when the button is pressed
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        //once a file is selected its type is determined and one of the 3 load functions is called
        //the current inventory is replaced by the new one both visually and in the code
        if(selectedFile != null && selectedFile.getName().contains(".json")) {
            inventoryManager.loadFromJSONFile(selectedFile);
            loadTable(inventoryManager);
        } else if(selectedFile != null && selectedFile.getName().contains(".html")) {
            inventoryManager.loadFromHTMLFile(selectedFile);
            loadTable(inventoryManager);
        } else if(selectedFile != null && selectedFile.getName().contains(".txt")) {
            inventoryManager.loadFromTSVFile(selectedFile);
            loadTable(inventoryManager);
        } else {
            //if the chosen file is not one of the 3 acceptable types an error message is given
            fileError.setText("File chosen is invalid.");
        }
    }

    public void saveInventoryOnButtonPress() {
        //creates a file chooser when the button is pressed
        FileChooser fileChooser = new FileChooser();
        File file =  fileChooser.showSaveDialog(null);
        //the user can then select a location and choose a name for the file followed by a proper extension
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Allowed Types", "*.txt", "*.txt");
        fileChooser.getExtensionFilters().addAll(fileExtensions);
        //if the chosen file is not one of the 3 acceptable types an error message is given
        if(file.getName().contains(".txt")) {
            inventoryManager.writeToTSVFile(file);
        } else if(file.getName().contains(".html")) {
            inventoryManager.writeToHTMLFile(file);
        } else if(file.getName().contains(".json")) {
            inventoryManager.writeToJSONFile(file);
        }
            //current list remains displayed
    }

    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(obsInventory);

        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //loads the table with new todolist manager when called
    @FXML
    public void loadTable(InventoryManager im) {
        tableView.getItems().clear();
        obsInventory.addAll(im.inventory.itemList);
        tableView.setItems(obsInventory);
    }
}
