/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import modelo.Coche;

/**
 *
 * @author JuanitoBalderrama
 */
public class Conexion {
    
    //pruebas para ver si funciona
    static Connection conn;
    public static final String URL = "jdbc:mysql://145.14.151.1/u812167471_coches";
    public static final String USERNAME = "u812167471_coches";
    public static final String PASSWORD = "2025-Coches";
    
    public static Connection conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
     public static void cerrarConexion(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static boolean registrarCoche(Coche c) {
          String consulta = "INSERT INTO coches (modelo, color, matriculacion, precio, imagen, activo) VALUES (?,?,?,?,?,?)";

          try {
             PreparedStatement st = conn.prepareStatement(consulta);
             st.setString(1, c.getModelo());
             st.setString(2, c.getColor());
             st.setInt(3,c.getMatriculacion());
             st.setDouble(4,c.getPrecio());
             st.setString(5,c.getImagen());
             st.setString(6, c.getActivo());

             st.execute();

             return true;
          } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return false;
       }
     
     public static void cargarDatosCoches(ObservableList<Coche> listado){
         try {
            String consultaCarga = "SELECT idCoche, modelo, color, matriculacion, precio, imagen, activo FROM coches";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaCarga);

            while (rs.next()) {
                listado.add(new Coche(
                        rs.getInt("idCoche"),
                        rs.getString("modelo"),
                        rs.getString("color"),
                        rs.getInt("matriculacion"),
                        rs.getDouble("precio"),
                        rs.getString("imagen"),
                        rs.getString("activo")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
      public static void cargarImagenCoche(ObservableList<Coche> listado){
        try {
            String consultaCarga = "SELECT modelo, color, matriculacion, precio, imagen FROM coches";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaCarga);
            
            while(rs.next()){
                listado.add(new Coche(
                        rs.getString("modelo"),
                        rs.getString("color"),
                        rs.getInt("matriculacion"),
                        rs.getDouble("precio"),
                        rs.getString("imagen")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      
      public static void cargarDatosCocheListado(ObservableList<Coche> listado){
        try {
            String consultaCarga = "SELECT idCoche, modelo, color, matriculacion, precio, imagen, activo FROM coches";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaCarga);
            
            while(rs.next()){
                listado.add(new Coche(
                        rs.getInt("idCoche"),
                        rs.getString("modelo"),
                        rs.getString("color"),
                        rs.getInt("matriculacion"),
                        rs.getDouble("precio"),
                        rs.getString("imagen"),
                        rs.getString("activo")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      
      public static boolean actualizarActivoCoche(Coche c) {
        String consulta = "UPDATE coches SET activo = 'SI' WHERE idCoche = ?";

        try {
            PreparedStatement st = conn.prepareStatement(consulta);
            st.setInt(1, c.getIdCoche()); 

            st.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      public static boolean actualizarDesactivoCoche(Coche c) {
        String consulta = "UPDATE coches SET activo = 'NO' WHERE idCoche = ?";

        try {
            PreparedStatement st = conn.prepareStatement(consulta);
            st.setInt(1, c.getIdCoche()); 

            st.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      public static boolean eliminarCoche(Coche c) {
        String consulta = "DELETE FROM coches WHERE idCoche = ?";

        try {
            PreparedStatement st = conn.prepareStatement(consulta);
            st.setInt(1, c.getIdCoche());  

            st.execute(); 

            return true ;  
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  // Si algo falla, retorna false
    }
      
      
     
     
}
