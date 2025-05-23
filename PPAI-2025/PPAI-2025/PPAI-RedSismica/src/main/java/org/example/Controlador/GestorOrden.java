package org.example.Controlador;

import org.example.Interfaz.InterfazEmail;
import org.example.Interfaz.InterfazPantalla;
import org.example.Interfaz.PantallaOrden;
import org.example.models.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GestorOrden {
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
    private static List<OrdenInspeccion> baseDatosOrdenes;
    private static List<Empleado> baseDatosEmpleados;
    private static List<TiposMotivos> baseDatosMotivos;
    private static List<EstacionSismografica> baseDatosEstaciones;

    public GestorOrden() {
        this.inicializarBaseDatos();
        this.fechaHoraActual = new Date();
        this.interfazEmail = new InterfazEmail("");
        this.interfazPantalla = new InterfazPantalla("");
        this.emailsResponsables = new ArrayList();
        this.motivosFS = new ArrayList();
    }

    public void setPantalla(PantallaOrden pantalla) {
        this.pantalla = pantalla;
    }

    private void inicializarBaseDatos() {
        if (baseDatosOrdenes == null) {
            this.inicializarEmpleados();
            this.inicializarEstaciones();
            this.inicializarMotivos();
            this.inicializarOrdenes();
        }

    }

    private void inicializarEmpleados() {
        baseDatosEmpleados = new ArrayList();
        Empleado emp1 = new Empleado("Juan Pérez", "juan.perez@empresa.com");
        Rol rolResponsable = new Rol("Responsable de Inspecciones", "Encargado de gestionar inspecciones");
        rolResponsable.agregarPermiso("cerrar_orden_inspeccion");
        emp1.agregarRol(rolResponsable);
        baseDatosEmpleados.add(emp1);
        Empleado emp2 = new Empleado("María García", "maria.garcia@empresa.com");
        Rol rolTecnico = new Rol("Técnico", "Técnico de campo");
        emp2.agregarRol(rolTecnico);
        baseDatosEmpleados.add(emp2);
    }

    private void inicializarEstaciones() {
        baseDatosEstaciones = new ArrayList();
        EstacionSismografica est1 = new EstacionSismografica("Estación Central", "Centro Ciudad", "Lat: -34.6118, Lon: -58.3960");
        Sismografo sismo1 = new Sismografo(101, "Kinemetrics", "K2-2000", "2022-03-15");
        est1.setSismografo(sismo1);
        baseDatosEstaciones.add(est1);
        EstacionSismografica est2 = new EstacionSismografica("Estación Norte", "Zona Norte", "Lat: -34.5118, Lon: -58.4960");
        Sismografo sismo2 = new Sismografo(102, "Guralp", "CMG-3T", "2021-08-20");
        est2.setSismografo(sismo2);
        baseDatosEstaciones.add(est2);
        EstacionSismografica est3 = new EstacionSismografica("Estación Sur", "Zona Sur", "Lat: -34.7118, Lon: -58.2960");
        Sismografo sismo3 = new Sismografo(103, "Nanometrics", "Trillium-120", "2023-01-10");
        est3.setSismografo(sismo3);
        baseDatosEstaciones.add(est3);
    }

    private void inicializarMotivos() {
        baseDatosMotivos = new ArrayList();
        baseDatosMotivos.add(new TiposMotivos("Falla de hardware"));
        baseDatosMotivos.add(new TiposMotivos("Falla de software"));
        baseDatosMotivos.add(new TiposMotivos("Mantenimiento preventivo"));
        baseDatosMotivos.add(new TiposMotivos("Calibración necesaria"));
        baseDatosMotivos.add(new TiposMotivos("Daño por factores externos"));
        baseDatosMotivos.add(new TiposMotivos("Interferencia electromagnética"));
        baseDatosMotivos.add(new TiposMotivos("Problemas de conectividad"));
    }

    private void inicializarOrdenes() {
        baseDatosOrdenes = new ArrayList();
        OrdenInspeccion orden1 = new OrdenInspeccion(1001, "2025-05-15", "Mantenimiento preventivo");
        orden1.setEstacion((EstacionSismografica)baseDatosEstaciones.get(0));
        orden1.setEmpleadoResponsable((Empleado)baseDatosEmpleados.get(0));
        orden1.marcarComoFinalizada();
        baseDatosOrdenes.add(orden1);
        OrdenInspeccion orden2 = new OrdenInspeccion(1002, "2025-05-16", "Inspección rutinaria");
        orden2.setEstacion((EstacionSismografica)baseDatosEstaciones.get(1));
        orden2.setEmpleadoResponsable((Empleado)baseDatosEmpleados.get(0));
        orden2.marcarComoFinalizada();
        baseDatosOrdenes.add(orden2);
        OrdenInspeccion orden3 = new OrdenInspeccion(1003, "2025-05-17", "Verificación de sensores");
        orden3.setEstacion((EstacionSismografica)baseDatosEstaciones.get(2));
        orden3.setEmpleadoResponsable((Empleado)baseDatosEmpleados.get(0));
        orden3.marcarComoFinalizada();
        baseDatosOrdenes.add(orden3);
        OrdenInspeccion orden4 = new OrdenInspeccion(1004, "2025-05-18", "Inspección pendiente");
        orden4.setEstacion((EstacionSismografica)baseDatosEstaciones.get(0));
        orden4.setEmpleadoResponsable((Empleado)baseDatosEmpleados.get(1));
        baseDatosOrdenes.add(orden4);
    }

    public void tomarOpcCerrarOrden() {
        System.out.println("=== INICIANDO CASO DE USO: CERRAR ORDEN DE INSPECCIÓN ===");
        this.pantalla.habilitar();
        this.empleadoLogueado = this.buscarEmpleadoRI();
        System.out.println("Empleado logueado: " + this.empleadoLogueado.getNombre());
        if (!this.empleadoLogueado.esResponsable()) {
            this.pantalla.mostrarError("El empleado no tiene permisos para cerrar órdenes de inspección");
        }
    }

    public List<OrdenInspeccion> buscarOrdInspeccion() {
        List<OrdenInspeccion> ordenesDelEmpleado = new ArrayList();
        Iterator var3 = baseDatosOrdenes.iterator();

        while(var3.hasNext()) {
            OrdenInspeccion orden = (OrdenInspeccion)var3.next();
            if (orden.esRealizada() && orden.getEmpleadoResponsable().equals(this.empleadoLogueado)) {
                ordenesDelEmpleado.add(orden);
            }
        }

        return ordenesDelEmpleado;
    }

    public void tomarOrdenPorNumero(int numeroOrden) {
        OrdenInspeccion ordenEncontrada = null;
        Iterator var4 = baseDatosOrdenes.iterator();

        while(var4.hasNext()) {
            OrdenInspeccion orden = (OrdenInspeccion)var4.next();
            if (orden.getNumeroDeOrden() == numeroOrden) {
                ordenEncontrada = orden;
                break;
            }
        }

        if (ordenEncontrada == null) {
            this.pantalla.mostrarError("No se encontró la orden de inspección #" + numeroOrden);
        } else if (!ordenEncontrada.esDeEmpleado(this.empleadoLogueado)) {
            this.pantalla.mostrarError("La orden #" + numeroOrden + " no pertenece al empleado logueado");
        } else if (!ordenEncontrada.esRealizada()) {
            this.pantalla.mostrarError("La orden #" + numeroOrden + " no está finalizada. Solo se pueden cerrar órdenes finalizadas.");
        } else if (ordenEncontrada.estaCerrada()) {
            this.pantalla.mostrarError("La orden #" + numeroOrden + " ya está cerrada.");
        } else {
            this.ordenSeleccionada = ordenEncontrada;
            System.out.println("Orden #" + numeroOrden + " seleccionada correctamente");
            System.out.println("Estación: " + this.ordenSeleccionada.getNombreEstacion());
            System.out.println("Fecha de generación: " + this.ordenSeleccionada.getFechaGeneracion());
            this.pantalla.pedirObservacion();
        }
    }

    public void tomarObservacion(String observacion) {
        System.out.println("Observación ingresada: " + observacion);
        if (observacion != null && !observacion.trim().isEmpty()) {
            this.observacionCierre = observacion.trim();
            this.motivosFS = this.buscarMotivos();
            this.pantalla.mostrarMotivos(this.motivosFS);
        } else {
            this.pantalla.mostrarError("La observación es obligatoria");
        }
    }

    public List<TiposMotivos> buscarMotivos() {
        List<TiposMotivos> motivosDisponibles = new ArrayList(baseDatosMotivos);
        return motivosDisponibles;
    }

    public void tomarMotivosYComentarios(String motivo, String comentario) {
        System.out.println("Motivo seleccionado: " + motivo);
        System.out.println("Comentario ingresado: " + comentario);
        boolean motivoValido = false;
        Iterator var5 = this.motivosFS.iterator();

        while(var5.hasNext()) {
            TiposMotivos motivoDisponible = (TiposMotivos)var5.next();
            if (motivoDisponible.getNombre().equals(motivo)) {
                motivoValido = true;
                break;
            }
        }

        if (!motivoValido) {
            this.pantalla.mostrarError("El motivo seleccionado no es válido");
        } else {
            this.motivoSeleccionado = motivo;
            this.comentarioIngresado = comentario != null ? comentario.trim() : "";
            this.pantalla.pedirConfirmacion(this.ordenSeleccionada, this.observacionCierre, this.motivoSeleccionado, this.comentarioIngresado);
        }
    }

    public void tomarConfirmacion(boolean confirmado) {
        if (confirmado) {
            System.out.println("Usuario confirmó el cierre de la orden");
            boolean esValido = this.validarObservacionYMotivo();
            if (esValido) {
                System.out.println("Datos validados correctamente");
                String hora = this.obtenerHoraActual();
                String fecha = this.obtenerFechaActual();
                this.ordenSeleccionada.cerrar(this.observacionCierre, this.motivoSeleccionado, this.comentarioIngresado, hora, fecha);
                System.out.println("Orden #" + this.ordenSeleccionada.getNumeroDeOrden() + " cerrada exitosamente");
                this.actualizarSismografo();
                this.notificarCierre();
                this.pantalla.mostrarMensaje("La orden #" + this.ordenSeleccionada.getNumeroDeOrden() + " ha sido cerrada exitosamente");
            } else {
                System.out.println("Error: Los datos ingresados no son válidos");
                this.pantalla.mostrarError("Los datos ingresados no son válidos");
            }
        } else {
            System.out.println("Usuario canceló la operación");
            this.pantalla.mostrarMensaje("Operación cancelada");
        }

    }

    public Empleado buscarEmpleadoRI() {
        return (Empleado)baseDatosEmpleados.get(0);
    }

    private boolean validarObservacionYMotivo() {
        return this.observacionCierre != null && !this.observacionCierre.isEmpty() && this.motivoSeleccionado != null && !this.motivoSeleccionado.isEmpty();
    }

    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    private void actualizarSismografo() {
        System.out.println("Actualizando estado del sismógrafo a Fuera de Servicio");
        this.ordenSeleccionada.enviarSismografoAReparar();
        String nombreEstacion = this.ordenSeleccionada.getNombreEstacion();
        System.out.println("Sismógrafo de la estación " + nombreEstacion + " marcado como Fuera de Servicio");
    }

    private void notificarCierre() {
        System.out.println("Preparando notificaciones de cierre de orden");
        this.emailsResponsables = this.obtenerEmailsResponsables();
        String notificacion = this.crearNotificacion();
        if (!this.emailsResponsables.isEmpty()) {
            this.enviarNotificacionesEmail((String[])this.emailsResponsables.toArray(new String[0]), notificacion);
        }

        this.publicarMonitor(this.ordenSeleccionada.getNombreEstacion(), notificacion);
    }

    private List<String> obtenerEmailsResponsables() {
        List<String> emails = new ArrayList();
        emails.add("supervisor@empresa.com");
        emails.add("mantenimiento@empresa.com");
        emails.add("control.calidad@empresa.com");
        System.out.println("Se encontraron " + emails.size() + " destinatarios para notificación");
        return emails;
    }

    private String crearNotificacion() {
        String var10000 = this.ordenSeleccionada.getNombreEstacion();
        String notification = "SISMÓGRAFO FUERA DE SERVICIO\nEstación: " + var10000 + "\nMotivo: " + this.motivoSeleccionado + "\nObservación: " + this.observacionCierre;
        if (this.comentarioIngresado != null && !this.comentarioIngresado.isEmpty()) {
            notification = notification + "\nComentarios: " + this.comentarioIngresado;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notification = notification + "\nFecha de cierre: " + sdf.format(this.fechaHoraActual) + "\nOrden #" + this.ordenSeleccionada.getNumeroDeOrden();
        return notification;
    }

    private void enviarNotificacionesEmail(String[] destinatarios, String mensaje) {
        System.out.println("Enviando notificación por correo a: " + String.join(", ", destinatarios));
        this.interfazEmail.enviarEmail(destinatarios, "SISMÓGRAFO FUERA DE SERVICIO", mensaje);
    }

    private void publicarMonitor(String nombreEstacion, String mensaje) {
        System.out.println("Publicando en monitor para estación: " + nombreEstacion);
        this.interfazPantalla.setNotificacion(nombreEstacion);
        this.interfazPantalla.publicar(mensaje);
    }

    public static List<OrdenInspeccion> obtenerTodasLasOrdenes() {
        return new ArrayList(baseDatosOrdenes);
    }

    public static List<Empleado> obtenerTodosLosEmpleados() {
        return new ArrayList(baseDatosEmpleados);
    }

    public static List<TiposMotivos> obtenerTodosLosMotivos() {
        return new ArrayList(baseDatosMotivos);
    }
}
