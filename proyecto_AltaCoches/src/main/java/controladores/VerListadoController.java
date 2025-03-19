/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import bbdd.Conexion;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Coche;
import modelo.ConexionFTP;
import utilidades.Alertas;
import utilidades.Animacion;

/**
 * FXML Controller class
 *
 * @author JuanitoBalderrama
 */
public class VerListadoController implements Initializable {

    @FXML
    private Button botonActivar;
    @FXML
    private Button botonDesactivar;
    @FXML
    private Button botonEliminar;
    @FXML
    private TableView<Coche> tablaCoches;
    @FXML
    private TableColumn<Coche, String> columnaModelo;
    @FXML
    private TableColumn<Coche, String> columnaColor;
    @FXML
    private TableColumn<Coche, Integer> columnaMatriculacion;
    @FXML
    private TableColumn<Coche, Double> columnaPrecio;
    @FXML
    private TableColumn<Coche, String> columnaActivo;
    
    ObservableList<Coche> listaCoches;
    @FXML
    private TableColumn<Coche, Integer> columnaIdCoche;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonActivar.setDisable(true);
        botonDesactivar.setDisable(true);
        botonEliminar.setDisable(true);
        Animacion.desaparecer2000(botonActivar);
        Animacion.desaparecer2000(botonDesactivar);
        Animacion.desaparecer2000(botonEliminar);
        cargarCoches();
    }    

    @FXML
    private void activarCoche(ActionEvent event) {
        Conexion.conectar();
        Coche c = tablaCoches.getSelectionModel().getSelectedItem();
    
      c = new Coche(c.getIdCoche(),c.getModelo(),c.getColor(),c.getMatriculacion(),c.getPrecio(),c.getActivo(),c.getImagen());
            if (Conexion.actualizarActivoCoche(c)) {
                utilidades.Alertas.confirmacion("Actualización exitosa", "El vehículo ha sido activado correctamente");
                botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
                cargarCoches();
            }else {
                utilidades.Alertas.error("Actualización fallida", "Error al activar el coche seleccionado en la base de datos, inténtelo de nuevo");
                botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
            }

    Conexion.cerrarConexion();
    }

    @FXML
    private void desactivarCoche(ActionEvent event) {
        Conexion.conectar();
        Coche c = tablaCoches.getSelectionModel().getSelectedItem();
    
      c = new Coche(c.getIdCoche(),c.getModelo(),c.getColor(),c.getMatriculacion(),c.getPrecio(),c.getActivo(),c.getImagen());
            if (Conexion.actualizarDesactivoCoche(c)) {
                utilidades.Alertas.confirmacion("Actualización exitosa", "El vehículo ha sido desactivado correctamente");
                botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
                cargarCoches();
            }else {
                utilidades.Alertas.error("Actualización fallida", "Error al desactivar el coche seleccionado en la base de datos, inténtelo de nuevo");
                botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
            }

    Conexion.cerrarConexion();
    }

    @FXML
    private void eliminarCoche(ActionEvent event) {
        Conexion.conectar();
          Coche cocheSeleccionado = tablaCoches.getSelectionModel().getSelectedItem();
          
    if (cocheSeleccionado == null) {
        Alertas.aviso("Selección", "Seleccione un coche para eliminar.");
        return;
    }
    
    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Realmente quiere eliminar el registro seleccionado?",
                            ButtonType.YES, ButtonType.NO);
    alerta.setTitle("Confirmación de eliminación");
    Optional<ButtonType> resultado = alerta.showAndWait();

    if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
        boolean eliminado = Conexion.eliminarCoche(cocheSeleccionado);
        if (eliminado) {
            ConexionFTP.eliminarArchivo(cocheSeleccionado.getImagen());
            Alertas.informacion("Registro y archivo de imagen eliminados correctamente.");
            
            cargarCoches();  // Recargar los datos de la tabla
                botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
        } else {
            Alertas.error("Error", "No se pudo eliminar el registro.");
            botonActivar.setDisable(true);
                botonDesactivar.setDisable(true);
                botonEliminar.setDisable(true);
                Animacion.desaparecer2000(botonActivar);
                Animacion.desaparecer2000(botonDesactivar);
                Animacion.desaparecer2000(botonEliminar);
        }
    }
    }
  private void actualizarEstadoBotones(Coche cocheSeleccionado) {
        if (cocheSeleccionado.getActivo().equals("SI")) {
            botonActivar.setDisable(true);
            botonDesactivar.setDisable(false);
            botonEliminar.setDisable(false);
            Animacion.aparecer2000(botonDesactivar);
            Animacion.aparecer2000(botonEliminar);
            Animacion.desaparecer2000(botonActivar);
        } else {
            botonActivar.setDisable(false);
            botonDesactivar.setDisable(true);
            botonEliminar.setDisable(false);//o true 
            Animacion.aparecer2000(botonActivar);
            Animacion.desaparecer2000(botonDesactivar);
            Animacion.aparecer2000(botonEliminar);
        }
    }
    @FXML
    private void seleccionarCoche(MouseEvent event) {
        actualizarEstadoBotones(tablaCoches.getSelectionModel().getSelectedItem());
        
    }
    public void cargarCoches() {
        listaCoches = FXCollections.observableArrayList();
        Conexion.conectar();
        Conexion.cargarDatosCocheListado(listaCoches);
        Conexion.cerrarConexion();

        tablaCoches.setItems(listaCoches);
        columnaIdCoche.setCellValueFactory(new PropertyValueFactory<>("idCoche"));
        columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnaColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        columnaMatriculacion.setCellValueFactory(new PropertyValueFactory<>("matriculacion"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
    }
}
