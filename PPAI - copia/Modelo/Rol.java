package Modelo;

public class Rol {
    private String nombre;

    //Constructor
    public Rol(String nombre) {
        this.nombre = nombre;
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //Métodos del Rol
    public void esResponsable() {}   
}
