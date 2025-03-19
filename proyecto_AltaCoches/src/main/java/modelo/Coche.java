/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author JuanitoBalderrama
 */
public class Coche {
    private int idCoche;
    private String modelo;
    private String color;
    private int matriculacion;
    private double precio;
    private String imagen;
    private String activo;

     public Coche(String modelo, double precio) {
        this.modelo = modelo;
        this.precio = precio;
    }

    public Coche(String modelo, String color, int matriculacion, double precio, String imagen) {
        this.modelo = modelo;
        this.color = color;
        this.matriculacion = matriculacion;
        this.precio = precio;
        this.imagen = imagen;
        this.activo = "SI";
    }

    public Coche(String modelo, String color, int matriculacion, double precio, String imagen, String activo) {
        this.modelo = modelo;
        this.color = color;
        this.matriculacion = matriculacion;
        this.precio = precio;
        this.imagen = imagen;
        this.activo = activo;
    }

    public Coche(int idCoche, String modelo, String color, int matriculacion, double precio, String imagen, String activo) {
        this.idCoche = idCoche;
        this.modelo = modelo;
        this.color = color;
        this.matriculacion = matriculacion;
        this.precio = precio;
        this.imagen = imagen;
        this.activo = activo;
    }
    
    
    
    

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMatriculacion() {
        return matriculacion;
    }

    public void setMatriculacion(int matriculacion) {
        this.matriculacion = matriculacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
}
