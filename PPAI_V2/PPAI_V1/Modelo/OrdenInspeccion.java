package Modelo;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdenInspeccion {
    private int numeroDeOrden;
    private String fechaGeneracion;
    private String tipoMotivo;

    // Atributos adicionales
    private List<CambioEstado> cambiosEstado;
    private EstacionSismografica estacion;
    private Sismografo sismografo;
    private String observacionCierre;

    //Constructor
    public OrdenInspeccion(int numeroDeOrden, String fechaGeneracion, String tipoMotivo) {
        this.numeroDeOrden = numeroDeOrden;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoMotivo = tipoMotivo;
        this.cambiosEstado = new ArrayList<>();
    }

    //Métodos get y set
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

    //Métodos de Orden de inspeccion
    public boolean esDeEmpleado(Empleado empleado) {
    // Verifica si la orden pertenece al empleado
    return true; // Implementar lógica real
    }

    public boolean estaRealizada() {
        // Verifica si la orden está en estado Finalizada
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual() && cambio.getEstado().esFinalizada()) {
                return true;
            }
        }
        return false;
    }

    public int getNumero() {
        return numeroDeOrden;
    }

    public String getFechaFin() {
        // Buscar la fecha de finalización
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.getEstado().esFinalizada()) {
                return cambio.getFechaFin();
            }
        }
        return null;
    }

    public String getNombreEstacion() {
        return estacion != null ? estacion.getNombre() : "Estación no asignada";
    }

    public boolean validarObservacionComentarios(String observacion, String motivo) {
        // Validar que exista observación y al menos un motivo
        return observacion != null && !observacion.isEmpty() 
            && motivo != null && !motivo.isEmpty();
    }

    public boolean estaCerrada() {
        // Verifica si la orden ya está cerrada
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual() && cambio.getEstado().esCerrada()) {
                return true;
            }
        }
        return false;
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
            Estado estadoCerrada = new Estado("Cerrada", "Orden cerrada", "OrdenInspeccion");
            CambioEstado nuevoCambio = new CambioEstado(null, null);
            nuevoCambio.setEstado(estadoCerrada);
            
            // Añadir el nuevo cambio de estado
            cambiosEstado.add(nuevoCambio);
        }

    public void enviarSismografoAReparar() {
        // Enviar el sismógrafo a reparar si está disponible
        if (estacion != null) {
            estacion.enviarAReparar();
        }
    }

// Métodos auxiliares
    private String obtenerHoraActual() {
        // Obtener la hora actual del sistema
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        // Obtener la fecha actual del sistema
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}