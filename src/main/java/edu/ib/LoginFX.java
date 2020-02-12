package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author: A. Kowal
 * @ver: v1.0
 * @date: 2020-02-12
 */

/**
 * LoginFX as a class responsible for Login window stage set (the parent window);
 */

public class LoginFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginFX.fxml"));
        Scene scene= new Scene(root);
        primaryStage.setTitle("user login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
