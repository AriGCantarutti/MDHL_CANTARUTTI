package Modelo;

public class CambioEstado {
    private String horaFin;
    private String fechaFin;

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

    //Métodos de Cambio de Estado
    public void esActual(){};
    public void setFechaHoraFin(){};
    public void crearMotivos(){};
}
