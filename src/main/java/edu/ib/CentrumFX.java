package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CentrumFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("edu.ib/fxml/loginFX.fxml"));
        Scene scene= new Scene(root);
        primaryStage.setTitle("centrum interface");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
