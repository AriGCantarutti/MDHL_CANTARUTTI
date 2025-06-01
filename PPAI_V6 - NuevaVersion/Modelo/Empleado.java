package Modelo;

public class Empleado {
    private String nombre;
    private String apellido;
    private String email;
    private int telefono;
    private Rol rol;

    // Constructor
    public Empleado(String nombre, String apellido, String email, int telefono, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Métodos Get y Set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Métodos de Empleado
    public void esResponsable(){}

    // Métodos para manejar roles
    public void obtenerEmail(Rol rol) {}
}
