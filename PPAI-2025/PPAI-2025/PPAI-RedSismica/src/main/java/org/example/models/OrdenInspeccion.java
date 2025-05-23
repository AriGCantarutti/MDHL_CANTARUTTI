package org.example.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrdenInspeccion {
    private int numeroDeOrden;
    private String fechaGeneracion;
    private String tipoMotivo;
    private String observacionCierre;
    private List<CambioEstado> cambiosEstado;
    private EstacionSismografica estacion;
    private Empleado empleadoResponsable;

    public OrdenInspeccion(int numeroDeOrden, String fechaGeneracion, String tipoMotivo) {
        this.numeroDeOrden = numeroDeOrden;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoMotivo = tipoMotivo;
        this.cambiosEstado = new ArrayList();
        Estado estadoInicial = new Estado("En Progreso", "Orden en progreso", "OrdenInspeccion");
        CambioEstado cambioInicial = new CambioEstado((String)null, (String)null);
        cambioInicial.setEstado(estadoInicial);
        this.cambiosEstado.add(cambioInicial);
    }

    public int getNumeroDeOrden() {
        return this.numeroDeOrden;
    }

    public void setNumeroDeOrden(int numeroDeOrden) {
        this.numeroDeOrden = numeroDeOrden;
    }

    public String getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoMotivo() {
        return this.tipoMotivo;
    }

    public void setTipoMotivo(String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    public String getObservacionCierre() {
        return this.observacionCierre;
    }

    public void setObservacionCierre(String observacionCierre) {
        this.observacionCierre = observacionCierre;
    }

    public EstacionSismografica getEstacion() {
        return this.estacion;
    }

    public void setEstacion(EstacionSismografica estacion) {
        this.estacion = estacion;
    }

    public Empleado getEmpleadoResponsable() {
        return this.empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public boolean esDeEmpleado(Empleado empleado) {
        return this.empleadoResponsable != null && empleado != null ? this.empleadoResponsable.getNombre().equals(empleado.getNombre()) : false;
    }

    public boolean esRealizada() {
        Iterator var2 = this.cambiosEstado.iterator();

        CambioEstado cambio;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            cambio = (CambioEstado)var2.next();
        } while(!cambio.esActual() || !cambio.getEstado().esFinalizada());

        return true;
    }

    public String getNombreEstacion() {
        return this.estacion != null ? this.estacion.getNombre() : "Estación no asignada";
    }

    public String getEstadoActual() {
        Iterator var2 = this.cambiosEstado.iterator();

        while(var2.hasNext()) {
            CambioEstado cambio = (CambioEstado)var2.next();
            if (cambio.esActual()) {
                return cambio.getEstado().getNombre();
            }
        }

        return "Sin estado";
    }

    public boolean estaCerrada() {
        Iterator var2 = this.cambiosEstado.iterator();

        CambioEstado cambio;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            cambio = (CambioEstado)var2.next();
        } while(!cambio.esActual() || !cambio.getEstado().esCerrada());

        return true;
    }

    public void marcarComoFinalizada() {
        Iterator var2 = this.cambiosEstado.iterator();

        while(var2.hasNext()) {
            CambioEstado cambio = (CambioEstado)var2.next();
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(this.obtenerFechaActual(), this.obtenerHoraActual());
            }
        }

        Estado estadoFinalizada = new Estado("Finalizada", "Orden finalizada", "OrdenInspeccion");
        CambioEstado nuevoCambio = new CambioEstado((String)null, (String)null);
        nuevoCambio.setEstado(estadoFinalizada);
        this.cambiosEstado.add(nuevoCambio);
        System.out.println("Orden #" + this.numeroDeOrden + " marcada como finalizada");
    }

    public void cerrar(String observacion, String motivo, String comentario, String hora, String fecha) {
        this.observacionCierre = observacion;
        Iterator var7 = this.cambiosEstado.iterator();

        while(var7.hasNext()) {
            CambioEstado cambio = (CambioEstado)var7.next();
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(fecha, hora);
            }
        }

        Estado estadoCerrada = new Estado("Cerrada", "Orden cerrada - " + motivo, "OrdenInspeccion");
        CambioEstado nuevoCambio = new CambioEstado((String)null, (String)null);
        nuevoCambio.setEstado(estadoCerrada);
        this.cambiosEstado.add(nuevoCambio);
        System.out.println("Orden #" + this.numeroDeOrden + " cerrada exitosamente");
        System.out.println("Motivo: " + motivo);
        System.out.println("Observación: " + observacion);
        if (comentario != null && !comentario.isEmpty()) {
            System.out.println("Comentario: " + comentario);
        }

    }

    public void enviarSismografoAReparar() {
        if (this.estacion != null) {
            this.estacion.enviarAReparar();
            System.out.println("Sismógrafo de " + this.estacion.getNombre() + " enviado a reparación");
        } else {
            System.out.println("Advertencia: No hay estación asignada a esta orden");
        }

    }

    public boolean validarObservacionComentarios(String observacion, String motivo) {
        return observacion != null && !observacion.trim().isEmpty() && motivo != null && !motivo.trim().isEmpty();
    }

    public String getFechaFin() {
        Iterator var2 = this.cambiosEstado.iterator();

        CambioEstado cambio;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            cambio = (CambioEstado)var2.next();
        } while(!cambio.getEstado().esFinalizada() && !cambio.getEstado().esCerrada());

        return cambio.getFechaFin();
    }

    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public String obtenerResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== ORDEN DE INSPECCIÓN #").append(this.numeroDeOrden).append(" ===\n");
        resumen.append("Fecha de generación: ").append(this.fechaGeneracion).append("\n");
        resumen.append("Tipo de motivo: ").append(this.tipoMotivo).append("\n");
        resumen.append("Estado actual: ").append(this.getEstadoActual()).append("\n");
        resumen.append("Estación: ").append(this.getNombreEstacion()).append("\n");
        if (this.empleadoResponsable != null) {
            resumen.append("Responsable: ").append(this.empleadoResponsable.getNombre()).append("\n");
        }

        if (this.observacionCierre != null) {
            resumen.append("Observación de cierre: ").append(this.observacionCierre).append("\n");
        }

        return resumen.toString();
    }

    public String toString() {
        int var10000 = this.numeroDeOrden;
        return "Orden #" + var10000 + " - " + this.getNombreEstacion() + " (" + this.getEstadoActual() + ")";
    }
}

