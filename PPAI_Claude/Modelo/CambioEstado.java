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

    //Métodos de Cambio de Estado
    public void setFechaHoraFin(){};
    public void crearMotivos(){};

    // Métodos a implementar
    public boolean esActual() {
        // Verifica si este cambio de estado es el actual (no tiene fecha fin)
        return horaFin == null || fechaFin == null;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setFechaHoraFin(String fecha, String hora) {
        this.fechaFin = fecha;
        this.horaFin = hora;

        System.out.println("Cambio de estado finalizado en fecha: " + fecha + " a las: " + hora);
    }
}
