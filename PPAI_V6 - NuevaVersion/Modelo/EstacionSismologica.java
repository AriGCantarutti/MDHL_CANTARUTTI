package Modelo;

public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCertificacionAdq;
    private String fechaSolicitudCertificacion;
    private int latitud;
    private int longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

    // Constructor
    public EstacionSismologica(int codigoEstacion, String documentoCertificacionAdq, String fechaSolicitudCertificacion,
                               int latitud, int longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    // Métodos Get y Set
    public int getCodigoEstacion() {
        return codigoEstacion;
    }
    public void setCodigoEstacion(int codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }
    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }

    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }

    public String getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(String fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(int nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    
    //Método get y set
    

    // Métodos de Estación Sismológica
    public void enviarAReparar() {}
}
