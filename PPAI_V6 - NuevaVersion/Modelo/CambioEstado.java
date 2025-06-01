package Modelo;

public class CambioEstado {
    private String fechaHoraFin;
    private String fechaHoraInicio;
    private Estado estado;
    private MotivoFueraServicio motivoFueraServicio;

    // Constructor
    public CambioEstado(String fechaHoraInicio, String fechaHoraFin, Estado estado, MotivoFueraServicio motivoFueraServicio) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado;
        this.motivoFueraServicio = motivoFueraServicio;
    }

    //Métodos Get y Set
    public String getFechaHoraFin() {
        return fechaHoraFin;
    }
    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }
    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setMotivoFueraServicio(MotivoFueraServicio motivoFueraServicio) {
        this.motivoFueraServicio = motivoFueraServicio;
    }
    public MotivoFueraServicio getMotivoFueraServicio() {
        return motivoFueraServicio;
    }

    //Métodos de Cambio de Estado
    public void esActual(){}

    public void crearMotivos(){}
}
