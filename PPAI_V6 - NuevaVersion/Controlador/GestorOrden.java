package Controlador;

//Importamos Librerías
import java.util.List;
import java.util.Date;

//Declaramos clase GestorOrden
public class GestorOrden {
    private String ordenSeleccionada;
    private String ordenesInspecciones;
    private String empleadoLogueado;
    private String motivosFS;
    private String observacionCierre;
    private String motivoSeleccionado;
    private String comentarioIngresado;
    private Date fechaHoraActual;
    private List<String> emailsResponsables;    

    //Constructor
    public GestorOrden(String ordenSeleccionada, String ordenesInspecciones, String empleadoLogueado,
                       String motivosFS, String observacionCierre, String motivoSeleccionado,
                       String comentarioIngresado, Date fechaHoraActual, List<String> emailsResponsables) {
        this.ordenSeleccionada = ordenSeleccionada;
        this.ordenesInspecciones = ordenesInspecciones;
        this.empleadoLogueado = empleadoLogueado;
        this.motivosFS = motivosFS;
        this.observacionCierre = observacionCierre;
        this.motivoSeleccionado = motivoSeleccionado;
        this.comentarioIngresado = comentarioIngresado;
        this.fechaHoraActual = fechaHoraActual;
        this.emailsResponsables = emailsResponsables;
    }

    // Métodos Get y Set
    public String getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(String ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public String getOrdenesInspecciones() {
        return ordenesInspecciones;
    }

    public void setOrdenesInspecciones(String ordenesInspecciones) {
        this.ordenesInspecciones = ordenesInspecciones;
    }

    public String getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public void setEmpleadoLogueado(String empleadoLogueado) {
        this.empleadoLogueado = empleadoLogueado;
    }

    public String getMotivosFS() {
        return motivosFS;
    }

    public void setMotivosFS(String motivosFS) {
        this.motivosFS = motivosFS;
    }

    public String getObservacionCierre() {
        return observacionCierre;
    }

    public void setObservacionCierre(String observacionCierre) {
        this.observacionCierre = observacionCierre;
    }

    public String getMotivoSeleccionado() {
        return motivoSeleccionado;
    }

    public void setMotivoSeleccionado(String motivoSeleccionado) {
        this.motivoSeleccionado = motivoSeleccionado;
    }

    public String getComentarioIngresado() {
        return comentarioIngresado;
    }

    public void setComentarioIngresado(String comentarioIngresado) {
        this.comentarioIngresado = comentarioIngresado;
    }

    public Date getFechaHoraActual() {
        return fechaHoraActual;
    }

    public void setFechaHoraActual(Date fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
    }

    public List<String> getEmailsResponsables() {
        return emailsResponsables;
    }

    public void setEmailsResponsables(List<String> emailsResponsables) {
        this.emailsResponsables = emailsResponsables;
    }
    

    //Métodos del Caso de Uso
    public void tomarOpcCerrarOrden() {}

    //public Empleado buscarEmpleadoRI() {}

    //public List<OrdenInspeccion> buscarOrdInspeccion() {}

    public void tomarOrdenPorNumero(int numeroOrden) {}

    public void tomarObservacion(String observacion) {}

    //public List<MotivosTipos> buscarMotivos() {}

    public void tomarMotivosYComentarios(String motivo, String comentario) {}

    public void tomarConfirmacion(boolean confirmado) {}

    public void validarObservacionYComentario(){}

    public void buscarEstacionFS(){}

    public void buscarEstadoCerrada(){}
    //private String obtenerHoraActual() {}

    //private String obtenerFechaActual() {}

    public void obtenerEmailsResponsables(){}

    //private void enviarNotificacionesEmail(){}
    
    //private void publicarMonitor() {}
}