package MainPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Label error;

    public void addItemOnButtonPress() {
        //ensure name is between [2, 256] characters
        //ensure value >= 0
        //ensure the serial number is unique
            //call the addItem method
        //display an error message if any requirement is not met
        //load the table to display the updated inventory

    }

    public void removeItemOnButtonPress() {
        //remove the selected item in the table from the display
        //call the removeItem method to get rid of it from the code
        //load the table to display the updated inventory
    }

    public void removeAllItemsOnButtonPress() {
        //clear the entire table
        //call the removeAllItems method
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
            error.setText("File chosen is invalid.");
        }
    }

    public void saveInventoryOnButtonPress() {
        //creates a file chooser when the button is pressed
        //the user can then select a location and choose a name for the file followed by a proper extension
        //if the chosen file is not one of the 3 acceptable types an error message is given
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
