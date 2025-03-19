
package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author JuanitoBalderrama
 */
public class ConexionFTP {
    public static FTPClient clienteFtp;
    
    
    public static boolean conectar() {

        String servidorFtp = "ftp.reynaldomd.com"; // IP O NOMBRE DEL SERVIDOR
        String user = "u812167471.2025Carpeta";
        String pass = "2025-Carpeta";

        clienteFtp = new FTPClient(); // Instanciamos el objeto de tipo FTPClient

        try {

            // CONEXIÓN CON EL SERVIDOR FTP CON EL MÉTODO CONNECT PASÁNDOLE POR PARÁMETRO EL SERVIDOR
            clienteFtp.connect(servidorFtp);

            // ESTABLECEMOS LOS MODOS DE CONEXIÓN-TRANSFERENCIA
            clienteFtp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            clienteFtp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            clienteFtp.enterLocalPassiveMode();
            return clienteFtp.login(user,pass);
        } catch(IOException ex){
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public static void subirArchivo(String nombreArchivoOrigen){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nombreArchivoOrigen);
            clienteFtp.storeFile(nombreArchivoOrigen, fis);
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
//    public static void listaArchivosServidor(ListView<String> lista){
//                    
//        ObservableList<String> archivos = FXCollections.observableArrayList();
//
//        try {
//            String[] imagenes = clienteFtp.listNames();
//            
//            if (imagenes != null && imagenes.toString() != "." && imagenes.toString() !="..") {
//                archivos.addAll(imagenes);
//            }
//            lista.setItems(archivos);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
//        
//    }
    public static void eliminarArchivo(String nombreImagen) {
        conectar(); // Asegúrate de que la conexión al servidor FTP esté establecida
        try {
            if (!clienteFtp.deleteFile(nombreImagen)) {
                System.out.println("No se pudo eliminar el archivo: " + nombreImagen);
                // O podrías usar un sistema de alertas si tienes uno
            } else {
                System.out.println("Archivo eliminado con éxito: " + nombreImagen);
            }
        } catch (IOException ex) {
            System.out.println("Error al eliminar el archivo: " + ex.getMessage());
            // O podrías usar un sistema de alertas si tienes uno
        } finally {
            desconectar(); // Cierra la conexión independientemente del resultado
        }
    }
    public static void cargarImagen(String imagen, ImageView img){
        String urlImagen = "https://reynaldomd.com/carpetaimg/" + imagen;
        Image image = new Image(urlImagen,true);
        img.setImage(image);
    }
public static void desconectar(){
        try {
            clienteFtp.logout();
            clienteFtp.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
}
