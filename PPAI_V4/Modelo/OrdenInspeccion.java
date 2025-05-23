package Modelo;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdenInspeccion {
    private int numeroDeOrden;
    private String fechaGeneracion;
    private String tipoMotivo;
    private String observacionCierre;
    
    // Atributos de relación
    private List<CambioEstado> cambiosEstado;
    private EstacionSismografica estacion;
    private Empleado empleadoResponsable;

    // Constructor
    public OrdenInspeccion(int numeroDeOrden, String fechaGeneracion, String tipoMotivo) {
        this.numeroDeOrden = numeroDeOrden;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoMotivo = tipoMotivo;
        this.cambiosEstado = new ArrayList<>();
        
        // Estado inicial "En Progreso"
        Estado estadoInicial = new Estado("En Progreso", "Orden en progreso", "OrdenInspeccion");
        CambioEstado cambioInicial = new CambioEstado(null, null);
        cambioInicial.setEstado(estadoInicial);
        cambiosEstado.add(cambioInicial);
    }

    // Getters y Setters
    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }

    public void setNumeroDeOrden(int numeroDeOrden) {
        this.numeroDeOrden = numeroDeOrden;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    public EstacionSismografica getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionSismografica estacion) {
        this.estacion = estacion;
    }

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public String getObservacionCierre() {
        return observacionCierre;
    }

    // Métodos de negocio
    public boolean esDeEmpleado(Empleado empleado) {
        if (empleadoResponsable == null || empleado == null) {
            return false;
        }
        return empleadoResponsable.getNombre().equals(empleado.getNombre());
    }

    public boolean estaFinalizada() {
        // Buscar si tiene un estado "Finalizada" activo
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual() && cambio.getEstado().esFinalizada()) {
                return true;
            }
        }
        return false;
    }

    public boolean estaCerrada() {
        // Buscar si tiene un estado "Cerrada" activo
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual() && cambio.getEstado().esCerrada()) {
                return true;
            }
        }
        return false;
    }

    public String getNombreEstacion() {
        return estacion != null ? estacion.getNombre() : "Estación no asignada";
    }

    public String getEstadoActual() {
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual()) {
                return cambio.getEstado().getNombre();
            }
        }
        return "Sin estado";
    }

    public void marcarComoFinalizada() {
        // Finalizar el estado actual
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(obtenerFechaActual(), obtenerHoraActual());
            }
        }
        
        // Crear nuevo estado "Finalizada"
        Estado estadoFinalizada = new Estado("Finalizada", "Orden finalizada", "OrdenInspeccion");
        CambioEstado nuevoCambio = new CambioEstado(null, null);
        nuevoCambio.setEstado(estadoFinalizada);
        cambiosEstado.add(nuevoCambio);
        
        System.out.println("Orden #" + numeroDeOrden + " marcada como finalizada");
    }

    public void cerrar(String observacion, String motivo, String comentario) {
        // Registrar la observación de cierre
        this.observacionCierre = observacion;
        
        // Finalizar el estado actual
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(obtenerFechaActual(), obtenerHoraActual());
            }
        }
        
        // Crear un nuevo cambio de estado a "Cerrada"
        Estado estadoCerrada = new Estado("Cerrada", "Orden cerrada - " + motivo, "OrdenInspeccion");
        CambioEstado nuevoCambio = new CambioEstado(null, null);
        nuevoCambio.setEstado(estadoCerrada);
        
        // Añadir el nuevo cambio de estado
        cambiosEstado.add(nuevoCambio);
        
        System.out.println("Orden #" + numeroDeOrden + " cerrada exitosamente");
        System.out.println("Motivo: " + motivo);
        System.out.println("Observación: " + observacion);
        if (comentario != null && !comentario.isEmpty()) {
            System.out.println("Comentario: " + comentario);
        }
    }

    public void enviarSismografoAReparar() {
        // Enviar el sismógrafo de la estación a reparar
        if (estacion != null) {
            estacion.enviarAReparar();
            System.out.println("Sismógrafo de " + estacion.getNombre() + " enviado a reparación");
        } else {
            System.out.println("Advertencia: No hay estación asignada a esta orden");
        }
    }

    public boolean validarObservacionComentarios(String observacion, String motivo) {
        return observacion != null && !observacion.trim().isEmpty() 
            && motivo != null && !motivo.trim().isEmpty();
    }

    public String getFechaFin() {
        // Buscar la fecha de finalización o cierre
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.getEstado().esFinalizada() || cambio.getEstado().esCerrada()) {
                return cambio.getFechaFin();
            }
        }
        return null;
    }

    // Métodos auxiliares
    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    // Método para mostrar información completa de la orden
    public String obtenerResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== ORDEN DE INSPECCIÓN #").append(numeroDeOrden).append(" ===\n");
        resumen.append("Fecha de generación: ").append(fechaGeneracion).append("\n");
        resumen.append("Tipo de motivo: ").append(tipoMotivo).append("\n");
        resumen.append("Estado actual: ").append(getEstadoActual()).append("\n");
        resumen.append("Estación: ").append(getNombreEstacion()).append("\n");
        
        if (empleadoResponsable != null) {
            resumen.append("Responsable: ").append(empleadoResponsable.getNombre()).append("\n");
        }
        
        if (observacionCierre != null) {
            resumen.append("Observación de cierre: ").append(observacionCierre).append("\n");
        }
        
        return resumen.toString();
    }

    @Override
    public String toString() {
        return "Orden #" + numeroDeOrden + " - " + getNombreEstacion() + " (" + getEstadoActual() + ")";
    }
}