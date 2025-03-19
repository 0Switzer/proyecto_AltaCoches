/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import bbdd.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Coche;
import modelo.ConexionFTP;
import utilidades.Animacion;

/**
 * FXML Controller class
 *
 * @author JuanitoBalderrama
 */
public class VerCochesController implements Initializable {

    @FXML
    private ImageView imagen;
    @FXML
    private TextField campoModelo;
    @FXML
    private TextField campoColor;
    @FXML
    private TextField campoMatriculacion;
    @FXML
    private TextField campoPrecio;
    @FXML
    private TableView<Coche> tablaCoches;
    @FXML
    private TableColumn<Coche, String> columnaModelo;
    @FXML
    private TableColumn<Coche, Double> columnaPrecio;
    
    ObservableList<Coche> listaCoches;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCoches();
        ConexionFTP.conectar();
        seleccionarDatosCoche();
    }    

    @FXML
    private void seleccionarCoche(MouseEvent event) {
        String selectedFile = tablaCoches.getSelectionModel().getSelectedItem().getImagen();
        if (selectedFile != null) {
            ConexionFTP.cargarImagen(selectedFile, imagen);
        }
    }
    
    
    public void cargarCoches(){
                listaCoches = FXCollections.observableArrayList();
                
                Conexion.conectar();
                Conexion.cargarDatosCoches(listaCoches);
                
                tablaCoches.setItems(listaCoches);
                
                columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
                columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            }
    
    public void seleccionarDatosCoche() {
        tablaCoches.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                campoModelo.setText(newValue.getModelo());
                campoColor.setText(newValue.getColor());
                campoMatriculacion.setText(String.valueOf(newValue.getMatriculacion()));
                campoPrecio.setText(String.valueOf(newValue.getPrecio()));

                ConexionFTP.cargarImagen(tablaCoches.getSelectionModel().getSelectedItem().getImagen(), imagen);

            }
        });
    }
    
}
