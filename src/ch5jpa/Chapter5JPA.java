/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5jpa;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class Chapter5JPA extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane tableListView = FXMLLoader.load(getClass().getResource("jpaPanel.fxml"));
        Scene scene = new Scene(tableListView);
        primaryStage.setTitle("JPA App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
