package org.example.util;

public record OrdenInspeccionVisualDTO(
        int numeroDeOrden,
        String EstadoActual,
        String fechaGeneracion
) {

    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }

    public String getEstadoActual() {
        return EstadoActual;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }
}
