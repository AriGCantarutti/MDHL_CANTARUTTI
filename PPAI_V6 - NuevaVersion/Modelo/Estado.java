package Modelo;

public class Estado {
    private String nombre;
    private String ambito;

    //Constructor
    public Estado(String nombre, String ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    //Método get y set
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAmbito() {
        return ambito;
    }
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    // Métodos del Estado
    public void esFinalizada() {}

    public void esAmbitoSismografo(){}

    public void esFueraServicio(){}

    public void esAmbitoOrden(){}

    public void esCerrada() {}
}
