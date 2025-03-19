/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author JuanitoBalderrama
 */
public class CompruebaCampo {
     public static boolean compruebaVacio (TextField campo){
        return campo.getText().isEmpty();
    }
    
    
    public static boolean compruebaNull(ComboBox selector){
        return selector.getSelectionModel().isEmpty();
    }
    
    public static boolean enteroCorrecto(TextField campo){
        double minum;
        
        try{
            minum=Double.parseDouble(campo.getText());
            return true;
        } catch(NumberFormatException nfe){
            return false;
        }
    }
}
