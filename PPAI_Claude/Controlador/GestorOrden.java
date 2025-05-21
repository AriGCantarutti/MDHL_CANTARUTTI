import java.util.ArrayList;
import java.util.List;
import Interfaz.PantallaOrden;
import Modelo.Empleado;
import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;
import java.util.Date; 

public class GestorOrden {
    private int opcCerrarOrden;
    private String empleadoRI;
    private int ordInsp;
    private String datosOC;
    private String motivo;
    private String comentario;
    private String confirmacion;
    private int estacionFS;
    private String emailResponsable;
    private String notificacionesEmail;
    private String monitor;

    //Atributos adicionales
    private PantallaOrden pantalla;
    private OrdenInspeccion ordenSeleccionada;
    private List<OrdenInspeccion> ordenesInspeccion;
    private Empleado empleadoLogueado;
    private List<TiposMotivos> motivosFS;
    private String observacionCierre;
    private Date fechaHoraActual;
    private List<String> emailsResponsables;

    //Constructor
    public GestorOrden(int opcCerrarOrden, String empleadoRI, int ordInsp, String datosOC, String motivo, String comentario,
                       String confirmacion, int estacionFS, String emailResponsable, String notificacionesEmail, String monitor) {
        this.opcCerrarOrden = opcCerrarOrden;
        this.empleadoRI = empleadoRI;
        this.ordInsp = ordInsp;
        this.datosOC = datosOC;
        this.motivo = motivo;
        this.comentario = comentario;
        this.confirmacion = confirmacion;
        this.estacionFS = estacionFS;
        this.emailResponsable = emailResponsable;
        this.notificacionesEmail = notificacionesEmail;
        this.monitor = monitor;
    }

    //Métodos get y set
    public int getOpcCerrarOrden() {
        return opcCerrarOrden;
    }
    public void setOpcCerrarOrden(int opcCerrarOrden) {
        this.opcCerrarOrden = opcCerrarOrden;
    }
    public String getEmpleadoRI() {
        return empleadoRI;
    }
    public void setEmpleadoRI(String empleadoRI) {
        this.empleadoRI = empleadoRI;
    }
    public int getOrdInsp() {
        return ordInsp;
    }
    public void setOrdInsp(int ordInsp) {
        this.ordInsp = ordInsp;
    }
    public String getDatosOC() {
        return datosOC;
    }
    public void setDatosOC(String datosOC) {
        this.datosOC = datosOC;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public String getConfirmacion() {
        return confirmacion;
    }
    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    public int getEstacionFS() {
        return estacionFS;
    }
    public void setEstacionFS(int estacionFS) {
        this.estacionFS = estacionFS;
    }
    public String getEmailResponsable() {
        return emailResponsable;
    }
    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }
    public String getNotificacionesEmail() {
        return notificacionesEmail;
    }
    public void setNotificacionesEmail(String notificacionesEmail) {
        this.notificacionesEmail = notificacionesEmail;
    }
    public String getMonitor() {
        return monitor;
    }
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    // Métodos a implementar
    public void opcCerrarOrden() {
        // Implementación para iniciar el caso de uso
        pantalla.habilitarBotonesCerrarOrden();
        empleadoLogueado = buscarEmpleadoRI();
        ordenesInspeccion = buscarOrdInsp(empleadoLogueado);
        pantalla.mostrarOrdInsp();
    }

    public Empleado buscarEmpleadoRI() {
        // Obtener el empleado logueado
        // Debe implementarse para obtener el usuario actual y su información
        return new Empleado("Nombre", "email@example.com");
    }

    public List<OrdenInspeccion> buscarOrdInsp(Empleado empleado) {
        // Buscar órdenes de inspección del empleado en estado finalizada
        // Debe implementarse la lógica de búsqueda en la BD o repositorio
        return new ArrayList<>(); // Retorna lista de órdenes encontradas
    }

    public void tomarSeleccionOrden(int numeroOrden) {
        // Buscar la orden seleccionada entre las órdenes disponibles
        ordenSeleccionada = buscarOrdenPorNumero(numeroOrden);
        pantalla.pedirObservacion();
    }

    private OrdenInspeccion buscarOrdenPorNumero(int numeroOrden) {
        // Buscar la orden por su número entre las disponibles
        for (OrdenInspeccion orden : ordenesInspeccion) {
            if (orden.getNumeroDeOrden() == numeroOrden) {
                return orden;
            }
        }
        return null;
    }

    public void tomarObservacion(String observacion) {
        // Guardar la observación ingresada
        observacionCierre = observacion;
        motivosFS = buscarMotivos();
        pantalla.mostrarMotivosFC();
    }

    public List<TiposMotivos> buscarMotivos() {
        // Buscar los tipos de motivos para poner en fuera de servicio
        // Debe implementarse la lógica para obtener los motivos de la BD
        return new ArrayList<>(); // Retorna lista de motivos
    }

    public void tomarMotivoYComentario(String motivo, String comentario) {
        // Guardar el motivo y comentario seleccionados
        this.motivo = motivo;
        this.comentario = comentario;
        pantalla.pedirConfirmacion();
    }

    public void tomarConfirmacion(boolean confirmado) {
        if (confirmado) {
            // Validar los datos ingresados
            boolean esValido = validarObservacionYMotivo();
            if (esValido) {
                // Cerrar la orden
                ordenSeleccionada.cerrar(observacionCierre, motivo, comentario);
                // Actualizar el sismógrafo
                actualizarSismografo();
                // Enviar notificaciones
                notificarCierre();
            } else {
                pantalla.mostrarError("Los datos ingresados no son válidos");
            }
        } else {
            pantalla.mostrarMensaje("Operación cancelada");
        }
    }

    private boolean validarObservacionYMotivo() {
        // Validar que exista observación y al menos un motivo
        return observacionCierre != null && !observacionCierre.isEmpty() 
            && motivo != null && !motivo.isEmpty();
    }

    private void actualizarSismografo() {
        // Actualizar el estado del sismógrafo a fuera de servicio
        ordenSeleccionada.enviarSismografoAReparar();
    }

   private void enviarNotificacionesEmail(String[] destinatarios, String mensaje) {
    // Implementación para enviar correos electrónicos
    System.out.println("Enviando notificación por correo a: " + String.join(", ", destinatarios));
    System.out.println("Mensaje: " + mensaje);
    }

    private void publicarMonitor(String nombreEstacion, String mensaje) {
        // Implementación para publicar en el sistema de monitoreo
        System.out.println("Publicando en monitor para estación: " + nombreEstacion);
        System.out.println("Mensaje: " + mensaje);
    }

    private void notificarCierre() {
        // Obtener emails de responsables
        emailsResponsables = obtenerEmailsResponsables();
        
        // Crear notificación
        String notificacion = crearNotificacion();
        
        // Enviar por email
        enviarNotificacionesEmail(emailsResponsables.toArray(new String[0]),notificacion);
        
        // Publicar en monitor
        publicarMonitor(ordenSeleccionada.getNombreEstacion(),notificacion);
    }

    private List<String> obtenerEmailsResponsables() {
        // Obtener emails de todos los responsables
        List<String> emails = new ArrayList<>();
        // Lógica para obtener los emails
        return emails;
    }

    private String crearNotificacion() {
        // Crear el texto de la notificación con los detalles del cierre
        return "Sismógrafo " + ordenSeleccionada.getNombreEstacion() + 
            " fuera de servicio. Motivo: " + motivo + 
            ". Comentarios: " + comentario;
    }
}