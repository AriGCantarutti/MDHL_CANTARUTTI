package Controlador;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import Interfaz.PantallaOrden;
import Interfaz.InterfazEmail;
import Interfaz.InterfazPantalla;

import Modelo.*;

public class GestorOrden {
    // Atributos del caso de uso
    private PantallaOrden pantalla;
    private OrdenInspeccion ordenSeleccionada;
    private List<OrdenInspeccion> ordenesInspeccion;
    private Empleado empleadoLogueado;
    private List<TiposMotivos> motivosFS;
    private String observacionCierre;
    private String motivoSeleccionado;
    private String comentarioIngresado;
    private Date fechaHoraActual;
    private List<String> emailsResponsables;
    private InterfazEmail interfazEmail;
    private InterfazPantalla interfazPantalla;

    // Simulación de base de datos - DATOS PRECARGADOS
    private static List<OrdenInspeccion> baseDatosOrdenes;
    private static List<Empleado> baseDatosEmpleados;
    private static List<TiposMotivos> baseDatosMotivos;
    private static List<EstacionSismografica> baseDatosEstaciones;

    // Constructor simplificado
    public GestorOrden() {
        inicializarBaseDatos();
        this.fechaHoraActual = new Date();
        this.interfazEmail = new InterfazEmail("");
        this.interfazPantalla = new InterfazPantalla("");
        this.emailsResponsables = new ArrayList<>();
        this.motivosFS = new ArrayList<>();
    }

    // Método para establecer la pantalla
    public void setPantalla(PantallaOrden pantalla) {
        this.pantalla = pantalla;
    }

    // INICIALIZACIÓN DE DATOS PRECARGADOS
    private void inicializarBaseDatos() {
        if (baseDatosOrdenes == null) {
            inicializarEmpleados();
            inicializarEstaciones();
            inicializarMotivos();
            inicializarOrdenes();
        }
    }

    private void inicializarEmpleados() {
        baseDatosEmpleados = new ArrayList<>();
        
        // Empleado 1 - Responsable de Inspecciones
        Empleado emp1 = new Empleado("Juan Pérez", "juan.perez@empresa.com");
        Rol rolResponsable = new Rol("Responsable de Inspecciones", "Encargado de gestionar inspecciones");
        rolResponsable.agregarPermiso("cerrar_orden_inspeccion");
        emp1.agregarRol(rolResponsable);
        baseDatosEmpleados.add(emp1);

        // Empleado 2 - Técnico
        Empleado emp2 = new Empleado("María García", "maria.garcia@empresa.com");
        Rol rolTecnico = new Rol("Técnico", "Técnico de campo");
        emp2.agregarRol(rolTecnico);
        baseDatosEmpleados.add(emp2);
    }

    private void inicializarEstaciones() {
        baseDatosEstaciones = new ArrayList<>();
        
        // Estación 1
        EstacionSismografica est1 = new EstacionSismografica("Estación Central", "Centro Ciudad", "Lat: -34.6118, Lon: -58.3960");
        Sismografo sismo1 = new Sismografo(101, "Kinemetrics", "K2-2000", "2022-03-15");
        est1.setSismografo(sismo1);
        baseDatosEstaciones.add(est1);

        // Estación 2
        EstacionSismografica est2 = new EstacionSismografica("Estación Norte", "Zona Norte", "Lat: -34.5118, Lon: -58.4960");
        Sismografo sismo2 = new Sismografo(102, "Guralp", "CMG-3T", "2021-08-20");
        est2.setSismografo(sismo2);
        baseDatosEstaciones.add(est2);

        // Estación 3
        EstacionSismografica est3 = new EstacionSismografica("Estación Sur", "Zona Sur", "Lat: -34.7118, Lon: -58.2960");
        Sismografo sismo3 = new Sismografo(103, "Nanometrics", "Trillium-120", "2023-01-10");
        est3.setSismografo(sismo3);
        baseDatosEstaciones.add(est3);
    }

    private void inicializarMotivos() {
        baseDatosMotivos = new ArrayList<>();
        baseDatosMotivos.add(new TiposMotivos("Falla de hardware"));
        baseDatosMotivos.add(new TiposMotivos("Falla de software"));
        baseDatosMotivos.add(new TiposMotivos("Mantenimiento preventivo"));
        baseDatosMotivos.add(new TiposMotivos("Calibración necesaria"));
        baseDatosMotivos.add(new TiposMotivos("Daño por factores externos"));
        baseDatosMotivos.add(new TiposMotivos("Interferencia electromagnética"));
        baseDatosMotivos.add(new TiposMotivos("Problemas de conectividad"));
    }

    private void inicializarOrdenes() {
        baseDatosOrdenes = new ArrayList<>();
        
        // Orden 1001 - Finalizada, lista para cerrar
        OrdenInspeccion orden1 = new OrdenInspeccion(1001, "2025-05-15", "Mantenimiento preventivo");
        orden1.setEstacion(baseDatosEstaciones.get(0)); // Estación Central
        orden1.setEmpleadoResponsable(baseDatosEmpleados.get(0)); // Juan Pérez
        orden1.marcarComoFinalizada();
        baseDatosOrdenes.add(orden1);

        // Orden 1002 - Finalizada, lista para cerrar
        OrdenInspeccion orden2 = new OrdenInspeccion(1002, "2025-05-16", "Inspección rutinaria");
        orden2.setEstacion(baseDatosEstaciones.get(1)); // Estación Norte
        orden2.setEmpleadoResponsable(baseDatosEmpleados.get(0)); // Juan Pérez
        orden2.marcarComoFinalizada();
        baseDatosOrdenes.add(orden2);

        // Orden 1003 - Finalizada, lista para cerrar
        OrdenInspeccion orden3 = new OrdenInspeccion(1003, "2025-05-17", "Verificación de sensores");
        orden3.setEstacion(baseDatosEstaciones.get(2)); // Estación Sur
        orden3.setEmpleadoResponsable(baseDatosEmpleados.get(0)); // Juan Pérez
        orden3.marcarComoFinalizada();
        baseDatosOrdenes.add(orden3);

        // Orden 1004 - En progreso (no se puede cerrar)
        OrdenInspeccion orden4 = new OrdenInspeccion(1004, "2025-05-18", "Inspección pendiente");
        orden4.setEstacion(baseDatosEstaciones.get(0));
        orden4.setEmpleadoResponsable(baseDatosEmpleados.get(1)); // María García
        baseDatosOrdenes.add(orden4);
    }

    // MÉTODOS DEL CASO DE USO
    public void opcCerrarOrden() {
        System.out.println("=== INICIANDO CASO DE USO: CERRAR ORDEN DE INSPECCIÓN ===");
        
        // Habilitar los botones en la pantalla
        pantalla.habilitarBotonesCerrarOrden();
        
        // Buscar el empleado responsable de inspección logueado
        empleadoLogueado = buscarEmpleadoRI();
        System.out.println("Empleado logueado: " + empleadoLogueado.getNombre());
        
        // Verificar que el empleado tenga permisos
        if (!empleadoLogueado.esResponsable()) {
            pantalla.mostrarError("El empleado no tiene permisos para cerrar órdenes de inspección");
            return;
        }
        
        // Se solicita al usuario que ingrese el número de orden
        pantalla.solicitarNumeroOrden();
    }

    public void buscarOrdenPorNumero(int numeroOrden) {
        System.out.println("Buscando orden de inspección #" + numeroOrden);
        
        // Buscar la orden en la "base de datos"
        OrdenInspeccion ordenEncontrada = null;
        for (OrdenInspeccion orden : baseDatosOrdenes) {
            if (orden.getNumeroDeOrden() == numeroOrden) {
                ordenEncontrada = orden;
                break;
            }
        }
        
        if (ordenEncontrada == null) {
            pantalla.mostrarError("No se encontró la orden de inspección #" + numeroOrden);
            return;
        }
        
        // Verificar que la orden pertenezca al empleado logueado
        if (!ordenEncontrada.esDeEmpleado(empleadoLogueado)) {
            pantalla.mostrarError("La orden #" + numeroOrden + " no pertenece al empleado logueado");
            return;
        }
        
        // Verificar que la orden esté finalizada
        if (!ordenEncontrada.estaFinalizada()) {
            pantalla.mostrarError("La orden #" + numeroOrden + " no está finalizada. Solo se pueden cerrar órdenes finalizadas.");
            return;
        }
        
        // Verificar que la orden no esté ya cerrada
        if (ordenEncontrada.estaCerrada()) {
            pantalla.mostrarError("La orden #" + numeroOrden + " ya está cerrada.");
            return;
        }
        
        // Orden válida para cerrar
        this.ordenSeleccionada = ordenEncontrada;
        System.out.println("Orden #" + numeroOrden + " seleccionada correctamente");
        System.out.println("Estación: " + ordenSeleccionada.getNombreEstacion());
        System.out.println("Fecha de generación: " + ordenSeleccionada.getFechaGeneracion());
        
        // Mostrar datos de la orden y solicitar observación
        pantalla.mostrarDatosOrden(ordenSeleccionada);
        pantalla.pedirObservacion();
    }

    public void tomarObservacion(String observacion) {
        System.out.println("Observación ingresada: " + observacion);
        
        // Validar que la observación no esté vacía
        if (observacion == null || observacion.trim().isEmpty()) {
            pantalla.mostrarError("La observación es obligatoria");
            return;
        }
        
        // Guardar la observación
        this.observacionCierre = observacion.trim();
        
        // Buscar los motivos para poner fuera de servicio
        this.motivosFS = buscarMotivos();
        
        // Mostrar los motivos en la pantalla
        pantalla.mostrarMotivosFC(motivosFS);
    }

    public List<TiposMotivos> buscarMotivos() {
        System.out.println("Consultando motivos para fuera de servicio desde la base de datos");
        
        // Retornar una copia de los motivos precargados
        List<TiposMotivos> motivosDisponibles = new ArrayList<>(baseDatosMotivos);
        
        System.out.println("Se encontraron " + motivosDisponibles.size() + " motivos disponibles:");
        int index = 1;
        for (TiposMotivos motivo : motivosDisponibles) {
            System.out.println(index + " - " + motivo.getNombre());
            index++;
        }
        
        return motivosDisponibles;
    }

    public void tomarMotivoYComentario(String motivo, String comentario) {
        System.out.println("Motivo seleccionado: " + motivo);
        System.out.println("Comentario ingresado: " + comentario);
        
        // Validar que el motivo sea válido
        boolean motivoValido = false;
        for (TiposMotivos motivoDisponible : motivosFS) {
            if (motivoDisponible.getNombre().equals(motivo)) {
                motivoValido = true;
                break;
            }
        }
        
        if (!motivoValido) {
            pantalla.mostrarError("El motivo seleccionado no es válido");
            return;
        }
        
        // Guardar el motivo y comentario
        this.motivoSeleccionado = motivo;
        this.comentarioIngresado = (comentario != null) ? comentario.trim() : "";
        
        // Solicitar confirmación al usuario
        pantalla.pedirConfirmacion(ordenSeleccionada, observacionCierre, motivoSeleccionado, comentarioIngresado);
    }

    public void tomarConfirmacion(boolean confirmado) {
        if (confirmado) {
            System.out.println("Usuario confirmó el cierre de la orden");
            
            // Validar los datos ingresados una vez más
            boolean esValido = validarObservacionYMotivo();
            if (esValido) {
                System.out.println("Datos validados correctamente");
                
                // Cerrar la orden
                ordenSeleccionada.cerrar(observacionCierre, motivoSeleccionado, comentarioIngresado);
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

    // MÉTODOS AUXILIARES
    public Empleado buscarEmpleadoRI() {
        Sesion sesion = new Sesion("Usuario1");
        Usuario usuario = sesion.obtenerUsuario();
        return usuario.obtenerEmpleado();
    }

    private boolean validarObservacionYMotivo() {
        return observacionCierre != null && !observacionCierre.isEmpty() 
            && motivoSeleccionado != null && !motivoSeleccionado.isEmpty();
    }

    private void actualizarSismografo() {
        System.out.println("Actualizando estado del sismógrafo a Fuera de Servicio");
        
        // Actualizar el estado del sismógrafo a fuera de servicio
        ordenSeleccionada.enviarSismografoAReparar();
        
        // Obtener el nombre de la estación para el log
        String nombreEstacion = ordenSeleccionada.getNombreEstacion();
        System.out.println("Sismógrafo de la estación " + nombreEstacion + " marcado como Fuera de Servicio");
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
        List<String> emails = new ArrayList<>();
        
        // Agregar emails de supervisores y técnicos responsables
        emails.add("supervisor@empresa.com");
        emails.add("mantenimiento@empresa.com");
        emails.add("control.calidad@empresa.com");
        
        System.out.println("Se encontraron " + emails.size() + " destinatarios para notificación");
        return emails;
    }

    private String crearNotificacion() {
        String notification = "SISMÓGRAFO FUERA DE SERVICIO\n" +
            "Estación: " + ordenSeleccionada.getNombreEstacion() + "\n" +
            "Motivo: " + motivoSeleccionado + "\n" +
            "Observación: " + observacionCierre;
        
        if (comentarioIngresado != null && !comentarioIngresado.isEmpty()) {
            notification += "\nComentarios: " + comentarioIngresado;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notification += "\nFecha de cierre: " + sdf.format(fechaHoraActual) + 
            "\nOrden #" + ordenSeleccionada.getNumeroDeOrden();
        
        return notification;
    }

    private void enviarNotificacionesEmail(String[] destinatarios, String mensaje) {
        System.out.println("Enviando notificación por correo a: " + String.join(", ", destinatarios));
        interfazEmail.enviarEmail(destinatarios, "SISMÓGRAFO FUERA DE SERVICIO", mensaje);
    }

    private void publicarMonitor(String nombreEstacion, String mensaje) {
        System.out.println("Publicando en monitor para estación: " + nombreEstacion);
        interfazPantalla.setNombre(nombreEstacion);
        interfazPantalla.publicar(mensaje);
    }

    // MÉTODOS PARA CONSULTA DE DATOS (útiles para testing y debugging)
    public static List<OrdenInspeccion> obtenerTodasLasOrdenes() {
        return new ArrayList<>(baseDatosOrdenes);
    }

    public static List<Empleado> obtenerTodosLosEmpleados() {
        return new ArrayList<>(baseDatosEmpleados);
    }

    public static List<TiposMotivos> obtenerTodosLosMotivos() {
        return new ArrayList<>(baseDatosMotivos);
    }
}