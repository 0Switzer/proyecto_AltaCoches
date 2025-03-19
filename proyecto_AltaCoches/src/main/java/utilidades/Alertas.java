/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javafx.scene.control.Alert;

/**
 *
 * @author JuanitoBalderrama
 */
public class Alertas {
    public static void informacion (String mensaje){
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("INFORMACIÓN");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
}
    
    
    public static void aviso (String cabecera, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("AVISO");
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    public static void error (String cabecera, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        //Optional<ButtonType> opcion = alerta.showAndWait();
               
//                if (opcion.get() == ButtonType.OK) {
//                    
//                }
    }
    
    public static void confirmacion (String cabecera, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Diálogo de confirmación");
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
