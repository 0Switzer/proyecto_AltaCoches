module com.jcf.proyecto_altacoches {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires commons.net;
    requires MySQL;

    opens com.jcf.proyecto_altacoches to javafx.fxml;
    exports com.jcf.proyecto_altacoches;
    
    opens controladores to javafx.fxml;
    exports controladores;
    
    opens modelo to javafx.fxml;
    exports modelo;
    
    opens bbdd to javafx.fxml;
    exports bbdd;
    
    opens utilidades to javafx.fxml;
    exports utilidades;
    
    
}
