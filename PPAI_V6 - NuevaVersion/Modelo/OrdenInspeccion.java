package Modelo;

public class OrdenInspeccion {
    private int numeroDeOrden;
    private String fechaGeneracion;
    private String observacionCierre;
    private String fechaHoraCierre;
    private String fechaHoraFinalizacion;
    private Empleado empleado;
    private Estado estado;
    private EstacionSismologica estacionSismologica;

    // Constructor
    public OrdenInspeccion(int numeroDeOrden, String fechaGeneracion, String observacionCierre,
                           String fechaHoraCierre, String fechaHoraFinalizacion, Empleado empleado,
                           Estado estado, EstacionSismologica estacionSismologica) {
        this.numeroDeOrden = numeroDeOrden;
        this.fechaGeneracion = fechaGeneracion;
        this.observacionCierre = observacionCierre;
        this.fechaHoraCierre = fechaHoraCierre;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.empleado = empleado;
        this.estado = estado;
        this.estacionSismologica = estacionSismologica;
    }

    // Métodos Get y Set
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
    public String getObservacionCierre() {
        return observacionCierre;
    }
    public void setObservacionCierre(String observacionCierre){
        this.observacionCierre = observacionCierre;
    }
    public String getFechaHoraCierre(){
        return fechaHoraCierre;
    }
    public void setFechaHoraCierre(String fechaHoraCierre){
        this.fechaHoraCierre = fechaHoraCierre;
    }
    public String getFechaHoraFinalizacion(){
        return fechaHoraFinalizacion;
    }
    public void setFechaHoraFinalizacion(String fechaHoraFinalizacion){
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;}
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    // Métodos de Orden de Inspeccion
    public void esDeEmpleado(Empleado empleado) {}
    public void esRealizada() {}
    public void validarObservacionYMotivo(String observacion, String motivo) {}
    public void estaCerrada() {}
    public void cerrar(String observacion, String motivo, String comentario, String hora, String fecha) {}
    public void enviarSismografoAReparar() {}
}