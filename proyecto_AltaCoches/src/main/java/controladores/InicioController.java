/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author JuanitoBalderrama
 */
public class InicioController implements Initializable {

    @FXML
    private BorderPane contenedor;
    @FXML
    private Pane panelInferior;
    @FXML
    private ColorPicker selectorColor;
    @FXML
    private Button botonNuevoVehiculo;
    @FXML
    private Button botonVerListado;
    @FXML
    private Button botonVerCoches;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void seleccionarColor(ActionEvent event) {
    Color colorSeleccionado = selectorColor.getValue();
    
    String colorHex = String.format("#%02x%02x%02x",
            (int) (colorSeleccionado.getRed() * 255),
            (int) (colorSeleccionado.getGreen() * 255),
            (int) (colorSeleccionado.getBlue() * 255));

    contenedor.setStyle("-fx-background-color: " + colorHex + ";");
    }

    @FXML
    private void registrarNuevoVehiculo(ActionEvent event) {
        cargaEscena("/vistas/NuevoVehiculo.fxml");
    }

    @FXML
    private void verListado(ActionEvent event) {
        cargaEscena("/vistas/VerListado.fxml");
    }

    @FXML
    private void verCoches(ActionEvent event) {
        cargaEscena("/vistas/VerCoches.fxml");
    }
     public void cargaEscena (String escena){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(getClass().getResource(escena));
            Parent nuevaEscena = (Parent) cargador.load();
            
            contenedor.setCenter(nuevaEscena);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
