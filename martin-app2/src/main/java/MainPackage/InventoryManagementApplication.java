/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Gabriel Martin
 */
package MainPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class InventoryManagementApplication extends javafx.application.Application {
    @Override

    public void start(Stage stage) throws Exception {

        Parent root =
                FXMLLoader.load((Objects.requireNonNull(getClass().getResource("InventoryManagerGUI.fxml"))));
        Scene scene = new Scene(root); // attach scene graph to scene
        stage.setTitle("Inventory Manager"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
