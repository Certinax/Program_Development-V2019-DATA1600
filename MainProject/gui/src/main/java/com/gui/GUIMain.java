package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h1>MainClass</h1>
 *
 * A main class for launching the application
 *
 * @author Fredrik Pedersen
 * @since 25-03-2019
 */


public class GUIMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/AvailablePositions.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Woopdidoo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}