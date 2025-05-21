package Modelo;

public class OrdenInspeccion {
    private int numeroDeOrden;
    private String fechaGeneracion;
    private String tipoMotivo;

    //Constructor
    public OrdenInspeccion(int numeroDeOrden, String fechaGeneracion, String tipoMotivo) {
        this.numeroDeOrden = numeroDeOrden;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoMotivo = tipoMotivo;
    }

    //Métodos get y set
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

    public String getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    //Métodos de Orden de inspeccion
    public void esDeEmpleado(){};
    public void estaRealizada(){};
    public void getNumero(){};
    public void getFechaFin(){};
    public void getNombreEstacion(){};
    public void validarObservacionComentarios(){};
    public void estaCerrada(){};
    public void cerrar(){};
    public void enviarSismografoAReparar(){};
}