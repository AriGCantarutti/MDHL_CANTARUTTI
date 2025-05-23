package org.example.models;

public class CambioEstado {
    private String horaFin;
    private String fechaFin;
    private Estado estado;

    public CambioEstado(String horaFin, String fechaFin) {
        this.horaFin = horaFin;
        this.fechaFin = fechaFin;
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
