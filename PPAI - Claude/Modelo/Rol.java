package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Rol {
    private String nombre;

    // Atributos adicionales
    private String descripcion;
    private List<String> permisos;  // Lista de permisos asociados al rol

    // Constructor expandido
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.permisos = new ArrayList<>();
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    
// Métodos adicionales get y set
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public void agregarPermiso(String permiso) {
        if (!permisos.contains(permiso)) {
            permisos.add(permiso);
        }
    }

// Implementación del método esResponsable
    public boolean esResponsable() {
        // Verifica si el rol es "Responsable de Inspecciones"
        return "Responsable de Inspecciones".equals(this.nombre) || 
            permisos.contains("cerrar_orden_inspeccion");
    }

    // Métodos adicionales que pueden ser útiles
    public boolean tienePermiso(String permiso) {
        return permisos.contains(permiso);
    }
}
