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

    //Constructor
    public GestorOrden(int opcCerrarOrden, String empleadoRI, int ordInsp, String datosOC, String motivo, String comentario,
                       String confirmacion, int estacionFS, String emailResponsable, String notificacionesEmail, String monitor) {
        this.opcCerrarOrden = opcCerrarOrden; // Faltaba esto
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

    //Métodos get y ser
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

    /*Métodos del Gestor*/
    public void opcCerrarOrden() {}
    //public Empleado buscarEmpleadoRI() { return new Empleado(); }
    //public List<OrdenInspeccion> buscarOrdInsp(Empleado e) { return List.of(new OrdenInspeccion()); }
    //public List<TipoMotivo> buscarMotivos() { return List.of(new TipoMotivo()); }
    //CAMBIAR EMAILS Y NOMBRE ESTACION POR LOS NOMBRES CORRECTOS.
    public void enviarNotificacionesEmail(String[] emails) {}
    public void publicarMonitor(String nombreEstacion) {}
}