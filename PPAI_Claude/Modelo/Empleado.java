package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private String nombre;
    private String email;

    // Atributos adicionales
    private List<Rol> roles;

    //Constructor
    public Empleado(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.roles = new ArrayList<>();
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String obtenerEmail() {
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    // Métodos a implementar
    public boolean esResponsable() {
        // Verifica si el empleado tiene el rol de Responsable
        for (Rol rol : roles) {
            if (rol.esResponsable()) {
                return true;
            }
        }
        return false;
    }


}
