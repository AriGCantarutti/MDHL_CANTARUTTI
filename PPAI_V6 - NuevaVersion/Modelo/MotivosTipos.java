package Modelo;

public class MotivosTipos {
    private String descripcion;

    //Constructor
    public MotivosTipos(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos get y set
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    //Métodos de Motivos Tipos
    public void obtenerMotivosFS() {}
}
