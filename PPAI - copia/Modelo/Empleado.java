package Modelo;

public class Empleado {
    private String nombre;
    private String email;

    //Constructor
    public Empleado(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //Métodos del Empleado
    public void esResponsable() {};
    public void obtenerEmail() {};       
}
