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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerGUIController implements Initializable {
    ObservableList<Item> obsInventory = FXCollections.observableArrayList();

    InventoryManager inventoryManager = new InventoryManager();

    @FXML
    public TableView<Item> tableView;
    @FXML
    private TableColumn<Item, Double> valueColumn;
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
            newItem.value = Double.parseDouble(valueField.getText());
        } else {
            errorFlag = 1;
        }
        //ensure the serial number is in the correct format
        if(newItem.checkSerialNumberFormat(serialNumberField.getText())) {
            newItem.serialNumber = serialNumberField.getText();
        } else {
            errorFlag = 1;
        }

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

    public void editItemOnButtonPress() {
        //displays the selected items values
        //get the index of the displayed item in the inventory
        //ensure name is between [2, 256] characters
        //ensure value >= 0
        //ensure the serial number is unique
            //call the editItem method with the currentItem passed as a parameter
        //load the table to display the updated inventory
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
    }

    //loads the table with new todolist manager when called
    @FXML
    public void loadTable(InventoryManager im) {
        tableView.getItems().clear();
        obsInventory.addAll(im.inventory.itemList);
        tableView.setItems(obsInventory);
    }
}
