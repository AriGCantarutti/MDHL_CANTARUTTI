package Modelo;

public class Sesion {
    private String usuario;

    //Constructor
    public Sesion(String usuario) {
        this.usuario = usuario;
    }

    //Métodos get y set
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //Métodos de la sesión
    public void obtenerUsuario() {}
}
