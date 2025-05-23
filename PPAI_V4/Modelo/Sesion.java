package Modelo;

public class Sesion {
    private String usuario;
    private Usuario user;

    //Constructor
    public Sesion(String usuario) {
      this.usuario = usuario;
    }
    
    public Sesion(Usuario user) {
        this.user = user;
    }

    //Métodos get y set
    public Usuario getUsuario() {
        return user;
    }
    public void setUsuarioName(String name) {
        this.user.setUsuario(name);
    }

    //Métodos de la sesión
    public void obtenerUsuario() {}
}
