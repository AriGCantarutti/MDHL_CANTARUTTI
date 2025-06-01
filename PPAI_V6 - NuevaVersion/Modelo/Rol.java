package Modelo;

public class Rol {
    private String nombre;
    private String descripcion;

    // Constructor expandido
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos de Rol
    public void esResponsable() {}
}
