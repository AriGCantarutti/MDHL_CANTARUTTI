package Modelo;

public class Estado {
    private String nombre;
    private String descripcion;
    private String ambito;

    //Constructor
    public Estado(String nombre, String descripcion, String ambito) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ambito = ambito;
    }

    //Método get y set
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getAmbito() {
        return ambito;
    }
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    // Métodos de Estado
    public boolean esFinalizada() {
        // Verifica si este estado corresponde a "Finalizada"
        return "Finalizada".equals(this.nombre);
    }

    public boolean esCerrada() {
        // Verifica si este estado corresponde a "Cerrada"
        return "Cerrada".equals(this.nombre);
    }
}
