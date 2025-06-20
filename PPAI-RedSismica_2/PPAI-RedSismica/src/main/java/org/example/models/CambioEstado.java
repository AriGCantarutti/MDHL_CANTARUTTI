package org.example.models;

public class CambioEstado {
    private String horaFin;
    private String fechaFin;
    private String horaInicio;
    private String fechaInicio;
    private Estado estado;

    public CambioEstado(String horaFin, String fechaFin, String horaInicio, String fechaInicio) {
        this.horaFin = horaFin;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.fechaInicio = fechaInicio;
    }

    public String getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setFechaHoraFin(String fecha, String hora) {
        this.fechaFin = fecha;
        this.horaFin = hora;
    }

    public void crearMotivos() {
    }

    public boolean esActual() {
        return this.horaFin == null || this.fechaFin == null;
    }
}
