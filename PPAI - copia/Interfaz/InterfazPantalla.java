package Interfaz;

public class InterfazPantalla {
    private String nombre;

    //Constructor
    public InterfazPantalla(String nombre) {
        this.nombre = nombre;
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    //Métodos del Monitor
    public void publicar() {}
}
