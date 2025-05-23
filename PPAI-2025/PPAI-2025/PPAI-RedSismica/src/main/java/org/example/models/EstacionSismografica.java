package org.example.models;

public class EstacionSismografica {
    private String nombre;
    private String ubicacion;
    private String coordenada;
    private Sismografo sismografo;

    public EstacionSismografica(String nombre, String ubicacion, String coordenada) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.coordenada = coordenada;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCoordenada() {
        return this.coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public void enviarAReparar() {
        if (this.sismografo != null) {
            this.sismografo.enviarAReparar();
        }

    }

    public Sismografo getSismografo() {
        return this.sismografo;
    }
}
