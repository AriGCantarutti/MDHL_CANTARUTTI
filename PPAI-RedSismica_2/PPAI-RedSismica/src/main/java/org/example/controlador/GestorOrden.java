package org.example.controlador;

import org.example.Interfaz.InterfazEmail;
import org.example.Interfaz.InterfazPantalla;
import org.example.Interfaz.PantallaOrden;
import org.example.models.*;

import java.security.spec.RSAOtherPrimeInfo;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//CLASE GESTOR
public class GestorOrden {
    private PantallaOrden pantalla;
    private Sesion sesion;
    private OrdenInspeccion ordenSeleccionada;
    private List<OrdenInspeccion> ordenesInspeccion;
    private Empleado empleadoLogueado;
    private int numeroOrdenSeleccionado;
    private String observacionCierre;
    private String motivoSeleccionado;
    private String comentarioIngresado;
    private List<Estado> estados;
    private List<Empleado> empleados;
    private InterfazEmail interfazEmail;
    private InterfazPantalla interfazPantalla;

    public GestorOrden(Sesion sesion, List<OrdenInspeccion> ordenesInspeccion, List<Estado> estados, List<Empleado> empleados, InterfazEmail interfazEmail, InterfazPantalla interfazPantalla) {
        this.sesion = sesion;
        this.ordenesInspeccion = ordenesInspeccion;
        this.estados = estados;
        this.empleados = empleados;
        this.interfazEmail = interfazEmail;
        this.interfazPantalla = interfazPantalla;
    }

    public void setPantalla(PantallaOrden pantalla) {
        this.pantalla = pantalla;
    }

    public void tomarOptCerrarOrd() {
        empleadoLogueado = buscarEmpleadoRI();
        buscarOrdInspeccion();
        buscarMotivos();
    }

    private Empleado buscarEmpleadoRI() {
        var usuario = sesion.obtenerUsuario();
        return usuario.obtenerEmpleado();
    }

    private void buscarOrdInspeccion() {
        for (OrdenInspeccion orden : ordenesInspeccion) {
            if (orden.esDeEmpleado(empleadoLogueado) && orden.esRealizada()) {
                var numero = orden.getNumeroOrden();
                var fechaFin = orden.getFechaCierre();
                var nombreEstacion = orden.getNombreEstacion();
                pantalla.mostrarDatosOrden(numero, fechaFin, nombreEstacion);
            }
        }
    }

    public void buscarMotivos() {
        var motivos = MotivoTipo.obtenerMotivos();
        pantalla.mostrarMotivos(motivos);
    }

    public boolean tomarOrdenPorNumero(int numeroOrden) {
        this.numeroOrdenSeleccionado = numeroOrden;
        //busca orden seleccionada
        var OrdenSeleccionadaOptional = ordenesInspeccion.stream()
                .filter(orden -> orden.getNumeroOrden() == numeroOrdenSeleccionado)
                .findFirst();
        //validacion
        if(OrdenSeleccionadaOptional.isEmpty()) {
            pantalla.mostrarMensajeError("Orden no encontrada", "No se encontró una orden de inspección con el número: " + numeroOrdenSeleccionado);
            return false;
        }

        ordenSeleccionada = OrdenSeleccionadaOptional.get();
        return true;
    }

    public void tomarObservacion(String observacion) {
        this.observacionCierre = observacion;
    }

    public void tomarMotivosYComentarios(String motivo, String comentario) {
        this.motivoSeleccionado = motivo;
        this.comentarioIngresado = comentario;
    }

    public void tomarConfirmacion() {
        System.out.println("Confirmación recibida para cerrar la orden de inspección.");
        boolean valido = ordenSeleccionada.validarObservacionYMotivos(observacionCierre, motivoSeleccionado);
        boolean estaCerrada = ordenSeleccionada.estaCerrada();
        if (!valido || estaCerrada) {
            pantalla.mostrarMensajeError("La observación o el motivo seleccionado no son válidos.","Por favor, verifique los datos ingresados.");
        }

        EstacionSismologica estacion = buscarEstacionFS();

        Estado estadoFueraServico = null;
        for (Estado estado: estados) {
            if (estado.esAmbitoSismografo() && estado.esFueraDeServicio()) {
                estadoFueraServico = estado;
                break;
            }
        }

        String horaActual = obtenerHoraActual();
        String fechaActual = obtenerFechaActual();

        ordenSeleccionada.cerrar(observacionCierre, motivoSeleccionado, comentarioIngresado, horaActual, fechaActual);
        ordenSeleccionada.enviarSismografoAReparar();

        System.out.println("Orden de Inspección cerrada con éxito.");

        List<String> emailsResponsables = obtenerEmailsResponsables();
        enviarNotificacionEmail(emailsResponsables);

        publicarMonitor(estacion.getNombre());

        finCasoDeUso();

    }

    private EstacionSismologica buscarEstacionFS() {
        return ordenSeleccionada.getEstacion();
    }

    private String obtenerHoraActual() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private String obtenerFechaActual() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private List<String> obtenerEmailsResponsables() {
        List<String> emails = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.esResponsable()) {
                String email = empleado.obtenerEmail();
                if (email != null && !email.isEmpty()) {
                    emails.add(email);
                }
            }
        }
        return emails;
    }

    private void enviarNotificacionEmail(List<String> emails) {
        System.out.println("Enviando notificación por email a los responsables de la orden de inspección...");
        interfazEmail.enviarEmail(emails);
    }

    private void publicarMonitor(String nombreEstacion) {
        System.out.println("Publicando información en el monitor de la estación sismológica...");
        interfazPantalla.publicar(nombreEstacion);
    }

    private void finCasoDeUso() {
        System.out.println("Fin del caso de uso: Cierre de Orden de Inspección");
    }
}
