package Modelo;

public class CambioEstado {
    private String horaFin;
    private String fechaFin;

    // Atributos adicionales
    private Estado estado;

    //Constructor
    public CambioEstado(String horaFin, String fechaFin) {
        this.horaFin = horaFin;
        this.fechaFin = fechaFin;
    }

    //Métodos get y set
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    //Métodos de Cambio de Estado
     public void setFechaHoraFin(String fecha, String hora){
        this.fechaFin = fecha;
        this.horaFin = hora;
    }
    
    public void crearMotivos(){
        // Lógica para crear motivos asociados al cambio de estado
    }
    // Métodos a implementar
    public boolean esActual() {
        // Verifica si este cambio de estado es el actual (no tiene fecha fin)
        return horaFin == null || fechaFin == null;
    }

    public Estado getEstado() {
        return estado;
    }
}
