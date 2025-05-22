package Modelo;

public class Usuario {
    private String usuario;
    private int password;

    //Constructor
    public Usuario(String usuario, int password){
        this.usuario = usuario;
        this.password = password;
    }

    //Métodos get y set
    public String getUsuario(){
        return usuario;
    }

    public int getPassword(){
        return password;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public void setPassword(int password){
        this.password = password;
    }

    //Métodos del usuario
    public void obtenerEmpleado() {} 
}
