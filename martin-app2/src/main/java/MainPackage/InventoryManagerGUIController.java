package MainPackage;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerGUIController implements Initializable {
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
        //once a file is selected its type is determined and one of the 3 load functions is called
        //if the chosen file is not one of the 3 acceptable types an error message is given
        //else the current inventory is replaced by the new one both visually and in the code

    }

    public void saveInventoryOnButtonPress() {
        //creates a file chooser when the button is pressed
        //the user can then select a location and choose a name for the file followed by a proper extension
        //if the chosen file is not one of the 3 acceptable types an error message is given
            //current list remains displayed
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
