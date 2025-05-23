package org.example.models;

public class Estado {
    private String nombre;
    private String descripcion;
    private String ambito;

    public Estado(String nombre, String descripcion, String ambito) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ambito = ambito;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAmbito() {
        return this.ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public boolean esFinalizada() {
        return "Finalizada".equals(this.nombre);
    }

    public boolean esCerrada() {
        return "Cerrada".equals(this.nombre);
    }
}

