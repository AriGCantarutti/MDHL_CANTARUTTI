package Controlador;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import Interfaz.PantallaOrden;
import Interfaz.InterfazEmail;
import Interfaz.InterfazPantalla;

import Modelo.Empleado;
import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;
import Modelo.EstacionSismografica;
import Modelo.Sismografo;
import Modelo.Estado;


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
    private InterfazEmail interfazEmail;
    private InterfazPantalla interfazPantalla;

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

         // Inicializar las listas y otras propiedades
        this.ordenesInspeccion = new ArrayList<>();
        this.motivosFS = new ArrayList<>();
        this.emailsResponsables = new ArrayList<>();
        this.fechaHoraActual = new Date();
        this.interfazEmail = new InterfazEmail("");
        this.interfazPantalla = new InterfazPantalla("");
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

    // Método para establecer la pantalla
    public void setPantalla(PantallaOrden pantalla) {
        this.pantalla = pantalla;
    }

    // Métodos a implementar del caso de uso
    //Sin uso de BASE DE DATO
    // Métodos del caso de uso
    public void opcCerrarOrden() {
        System.out.println("Iniciando caso de uso: Cerrar Orden de Inspección");
        
        // Habilitar los botones en la pantalla
        pantalla.habilitarBotonesCerrarOrden();
        
        // Buscar el empleado responsable de inspección
        empleadoLogueado = buscarEmpleadoRI();
        System.out.println("Empleado responsable: " + empleadoLogueado.getNombre());
        
        // Buscar órdenes de inspección del empleado
        ordenesInspeccion = buscarOrdInsp(empleadoLogueado);
        System.out.println("Se encontraron " + ordenesInspeccion.size() + " órdenes de inspección");
        
        // Mostrar las órdenes en la pantalla
        pantalla.mostrarOrdInsp();
    }

    public Empleado buscarEmpleadoRI() {
        // Simulamos la búsqueda del empleado
        Empleado empleado = new Empleado("Juan Pérez", "juan.perez@empresa.com");
        
        // Asignar un rol de responsable
        Modelo.Rol rolResponsable = new Modelo.Rol("Responsable de Inspecciones", "Encargado de gestionar inspecciones");
        rolResponsable.agregarPermiso("cerrar_orden_inspeccion");
        empleado.agregarRol(rolResponsable);
        
        return empleado;
    }

    public List<OrdenInspeccion> buscarOrdInsp(Empleado empleado) {
        // Simulamos la búsqueda de órdenes en BD
        List<OrdenInspeccion> ordenes = new ArrayList<>();
        
        // Crear algunas órdenes de ejemplo
        for (int i = 1; i <= 3; i++) {
            OrdenInspeccion orden = new OrdenInspeccion(i, "2025-05-" + (10 + i), "Mantenimiento");
            
            // Configurar la estación
            EstacionSismografica estacion = new EstacionSismografica("Estación " + i, "Ubicación " + i, "Coord " + i);
            Sismografo sismografo = new Sismografo(i, "Fabricante A", "Modelo X" + i, "2024-01-01");
            estacion.setSismografo(sismografo);
            //orden.setEstacion(estacion);
            
            // Crear un estado "Finalizada" para la orden
            Estado estadoFinalizada = new Estado("Finalizada", "Orden finalizada", "OrdenInspeccion");
            //orden.agregarEstado(estadoFinalizada);
            
            // Solo agregar si la orden pertenece al empleado (simplificación)
            if (orden.esDeEmpleado(empleado)) {
                ordenes.add(orden);
                System.out.println("Orden #" + i + " encontrada para el empleado " + empleado.getNombre());
            }
        }
        
        return ordenes;
    }
    
    public void tomarSeleccionOrden(int numeroOrden) {
        System.out.println("Procesando selección de orden: " + numeroOrden);
        
        // Buscar la orden seleccionada entre las disponibles
        ordenSeleccionada = buscarOrdenPorNumero(numeroOrden);
        
        if (ordenSeleccionada != null) {
            System.out.println("Orden #" + numeroOrden + " seleccionada correctamente");
            // Solicitar observación al usuario
            pantalla.pedirObservacion();
        } else {
            System.out.println("Error: No se encontró la orden #" + numeroOrden);
            pantalla.mostrarError("La orden seleccionada no existe");
        }
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
        System.out.println("Observación ingresada: " + observacion);
        
        // Guardar la observación
        this.observacionCierre = observacion;
        
        // Buscar los motivos para poner fuera de servicio
        this.motivosFS = buscarMotivos();
        
        // Mostrar los motivos en la pantalla
        pantalla.mostrarMotivosFC();
    }

    public List<TiposMotivos> buscarMotivos() {
        // Simulamos la búsqueda de motivos en BD
        List<TiposMotivos> motivos = new ArrayList<>();
        
        // Crear algunos motivos de ejemplo
        motivos.add(new TiposMotivos("Falla de hardware"));
        motivos.add(new TiposMotivos("Falla de software"));
        motivos.add(new TiposMotivos("Mantenimiento preventivo"));
        motivos.add(new TiposMotivos("Calibración necesaria"));
        motivos.add(new TiposMotivos("Daño por factores externos"));
        
        System.out.println("Se encontraron " + motivos.size() + " motivos para fuera de servicio");
        return motivos;
    }

    public void tomarMotivoYComentario(String motivo, String comentario) {
        System.out.println("Motivo seleccionado: " + motivo);
        System.out.println("Comentario ingresado: " + comentario);
        
        // Guardar el motivo y comentario
        this.motivo = motivo;
        this.comentario = comentario;
        
        // Solicitar confirmación al usuario
        pantalla.pedirConfirmacion();
    }

    public void tomarConfirmacion(boolean confirmado) {
        if (confirmado) {
            System.out.println("Usuario confirmó la operación");
            
            // Validar los datos ingresados
            boolean esValido = validarObservacionYMotivo();
            if (esValido) {
                System.out.println("Datos validados correctamente");
                
                // Cerrar la orden
                ordenSeleccionada.cerrar(observacionCierre, motivo, comentario);
                System.out.println("Orden #" + ordenSeleccionada.getNumeroDeOrden() + " cerrada exitosamente");
                
                // Actualizar el sismógrafo
                actualizarSismografo();
                
                // Enviar notificaciones
                notificarCierre();
                
                // Mostrar mensaje de éxito
                pantalla.mostrarMensaje("La orden #" + ordenSeleccionada.getNumeroDeOrden() + " ha sido cerrada exitosamente");
            } else {
                System.out.println("Error: Los datos ingresados no son válidos");
                pantalla.mostrarError("Los datos ingresados no son válidos");
            }
        } else {
            System.out.println("Usuario canceló la operación");
            pantalla.mostrarMensaje("Operación cancelada");
        }
    }

    private boolean validarObservacionYMotivo() {
        // Validar que exista observación y al menos un motivo
        return observacionCierre != null && !observacionCierre.isEmpty() 
            && motivo != null && !motivo.isEmpty();
    }

    private void actualizarSismografo() {
        System.out.println("Actualizando estado del sismógrafo a Fuera de Servicio");
        
        // Actualizar el estado del sismógrafo a fuera de servicio
        ordenSeleccionada.enviarSismografoAReparar();
        
        // Obtener el nombre de la estación para el log
        String nombreEstacion = ordenSeleccionada.getNombreEstacion();
        System.out.println("Sismógrafo de la estación " + nombreEstacion + " marcado como Fuera de Servicio");
    }

    private void enviarNotificacionesEmail(String[] destinatarios, String mensaje) {
        // Implementación para enviar correos electrónicos
        System.out.println("Enviando notificación por correo a: " + String.join(", ", destinatarios));
        System.out.println("Mensaje: " + mensaje);
        
        // Usar la interfaz de email para enviar el correo
        interfazEmail.enviarEmail(destinatarios, "Sismógrafo Fuera de Servicio", mensaje);
    }

    private void publicarMonitor(String nombreEstacion, String mensaje) {
        // Implementación para publicar en el sistema de monitoreo
        System.out.println("Publicando en monitor para estación: " + nombreEstacion);
        System.out.println("Mensaje: " + mensaje);
        
        // Usar la interfaz de pantalla para publicar el mensaje
        interfazPantalla.setNombre(nombreEstacion);
        interfazPantalla.publicar(mensaje);
    }

    private void notificarCierre() {
        System.out.println("Preparando notificaciones de cierre de orden");
        
        // Obtener emails de responsables
        emailsResponsables = obtenerEmailsResponsables();
        
        // Crear notificación
        String notificacion = crearNotificacion();
        
        // Enviar por email si hay destinatarios
        if (!emailsResponsables.isEmpty()) {
            enviarNotificacionesEmail(emailsResponsables.toArray(new String[0]), notificacion);
        }
        
        // Publicar en monitor
        publicarMonitor(ordenSeleccionada.getNombreEstacion(), notificacion);
    }

    private List<String> obtenerEmailsResponsables() {
        // Obtener emails de todos los responsables
        List<String> emails = new ArrayList<>();
        
        // Simulación: Agregar algunos emails de ejemplo
        emails.add("supervisor@empresa.com");
        emails.add("mantenimiento@empresa.com");
        
        // Si hay un email específico para el responsable, agregarlo
        if (emailResponsable != null && !emailResponsable.isEmpty()) {
            emails.add(emailResponsable);
        }
        
        System.out.println("Se encontraron " + emails.size() + " destinatarios para notificación");
        return emails;
    }

    private String crearNotificacion() {
        // Crear el texto de la notificación con los detalles del cierre
        String notification = "Sismógrafo " + ordenSeleccionada.getNombreEstacion() + 
            " fuera de servicio. Motivo: " + motivo;
        
        // Agregar comentarios si existen
        if (comentario != null && !comentario.isEmpty()) {
            notification += ". Comentarios: " + comentario;
        }
        
        // Agregar fecha y número de orden
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        notification += ". Fecha: " + sdf.format(fechaHoraActual) + ". Orden #" + ordenSeleccionada.getNumeroDeOrden();
        
        return notification;
    }

}