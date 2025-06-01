package Modelo;

public class Usuario {
    private String usuario;
    private int password;
    private Empleado empleado;

    // Constructor
    public Usuario(String usuario, int password, Empleado empleado) {
        this.usuario = usuario;
        this.password = password;
        this.empleado = empleado;
    }

    // Métodos Get y Set
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    //Métodos del Usuario
    public void obtenerEmpleado() {} 
}
