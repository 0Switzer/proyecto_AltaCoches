package com.jcf.proyecto_altacoches;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vistas/Inicio.fxml"));
        Parent root = cargador.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("REGISTRO");
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

   
    public static void main(String[] args) {
        launch();
    }

}