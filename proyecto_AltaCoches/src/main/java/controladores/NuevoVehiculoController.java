/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import bbdd.Conexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import modelo.Coche;
import modelo.ConexionFTP;

/**
 * FXML Controller class
 *
 * @author JuanitoBalderrama
 */
public class NuevoVehiculoController implements Initializable {

    @FXML
    private Button botonRegistrar;
    @FXML
    private Button botonImagen;
    @FXML
    private TextField campoModelo;
    @FXML
    private TextField campoColor;
    @FXML
    private TextField campoMatriculacion;
    @FXML
    private TextField campoPrecio;
    @FXML
    private TextField campoSubirImagen;
    @FXML
    private CheckBox cb_activo;
    
    FileChooser selector;
    
    String nuevoNombre = String.valueOf(System.currentTimeMillis());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_activo.isDisabled();
    }    

    @FXML
    private void registrarVehiculo(ActionEvent event) {
         if (utilidades.CompruebaCampo.compruebaVacio(campoModelo)) {
          
          utilidades.Alertas.aviso("Campo vacío", "El campo modelo no debe estar vacío");
        
      } else if (utilidades.CompruebaCampo.compruebaVacio(campoColor)){
          
          utilidades.Alertas.aviso("Campo vacío", "El campo color no debe estar vacío");
      }else if (utilidades.CompruebaCampo.compruebaVacio(campoMatriculacion)) {
          
          utilidades.Alertas.aviso("Combo vacío", "El combo matriculacion no debe estar vacío");
        
      }else if (!utilidades.CompruebaCampo.enteroCorrecto(campoMatriculacion)){
          
          utilidades.Alertas.aviso("Formato incorrecto", "Debes ingresar un valor numérico en el campo matriculación");
          campoMatriculacion.setText("");
      }else if (utilidades.CompruebaCampo.compruebaVacio(campoPrecio)){
          
          utilidades.Alertas.aviso("Campo vacío", "El campo precio no debe estar vacío");
      }else if (!utilidades.CompruebaCampo.enteroCorrecto(campoPrecio)){
          
          utilidades.Alertas.aviso("Formato incorrecto", "Debes ingresar un valor numérico en el campo precio");
          campoPrecio.setText("");
      }else if (utilidades.CompruebaCampo.compruebaVacio(campoSubirImagen)){
          
          utilidades.Alertas.aviso("Campo vacío", "El campo subir imagen no debe estar vacío");
      }
      else {
           Conexion.conectar();
            String extension = campoSubirImagen.getText().substring(campoSubirImagen.getText().lastIndexOf("."));
            Coche c = new Coche(
                    campoModelo.getText(),
                    campoColor.getText(),
                    Integer.parseInt(campoMatriculacion.getText()),
                    Double.parseDouble(campoPrecio.getText()),
                    nuevoNombre+extension);
          
              if (Conexion.registrarCoche(c)) {
                subirArchivo();
                utilidades.Alertas.confirmacion("Registro exitoso", "El coche ha sido registrado correctamente");
                resetear();
            } else {
                utilidades.Alertas.error("Registro fallido", "Error al registrar el coche en la base de datos, inténtelo de nuevo");
                resetear();
            }
            }
        Conexion.cerrarConexion();
    }

    @FXML
    private void subirImagen(ActionEvent event) {
        selector = new FileChooser();
        selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png","*.jpg","*.jpeg"));
        File file = selector.showOpenDialog(null);
        if (file != null) {
            campoSubirImagen.setText(file.getAbsolutePath());
        }
    }
    
    public void subirArchivo(){
        String extension = campoSubirImagen.getText().substring(campoSubirImagen.getText().lastIndexOf("."));
        File file = new File(campoSubirImagen.getText());

        ConexionFTP.conectar();
        ConexionFTP.subirArchivo(file.getAbsolutePath());

        try {
            ConexionFTP.clienteFtp.rename(file.getAbsolutePath(), nuevoNombre + extension);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ConexionFTP.desconectar();
    }
    
    public void resetear(){
         campoModelo.setText("");
         campoColor.setText("");
         campoMatriculacion.setText("");
         campoPrecio.setText("");
         campoColor.setText("");
         campoSubirImagen.setText("");
     }
}
