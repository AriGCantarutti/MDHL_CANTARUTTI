package Modelo;
import java.util.List;

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
    public void esDeEmpleado(){};
    public void validarObservacionComentarios(){};
    public void cerrar(){};

    // Métodos a implementar completamente
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
        return estacion.getNombre();
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
        
        // Crear un nuevo cambio de estado a "Cerrada"
        Estado estadoCerrada = new Estado("Cerrada", "Orden cerrada", "OrdenInspeccion");
        CambioEstado nuevoCambio = new CambioEstado(obtenerHoraActual(), obtenerFechaActual());
        // Añadir el nuevo cambio de estado
        cambiosEstado.add(nuevoCambio);
    }

    public void enviarSismografoAReparar() {
        // Enviar el sismógrafo a reparar
        sismografo.enviarAReparar();
    }

// Métodos auxiliares
    private String obtenerHoraActual() {
        // Obtener la hora actual del sistema
        return "12:00"; // Implementar con fecha real
    }

    private String obtenerFechaActual() {
        // Obtener la fecha actual del sistema
        return "2025-05-21"; // Implementar con fecha real
    }
}