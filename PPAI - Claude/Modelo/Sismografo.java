package Modelo;
import java.util.List;

public class Sismografo {
    private int id;
    private String fabricante;
    private String modelo;
    private String fechaAdquisicion;

    // Atributos adicionales
    private Estado estadoActual;
    private List<CambioEstado> cambiosEstado;

    //Constructor
    public Sismografo(int id, String fabricante, String modelo, String fechaAdquisicion) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    //Métodos get y set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    // Métodos a implementar
    public void enviarAReparar() {
        // Cambiar el estado del sismógrafo a "Fuera de Servicio"
        Estado estadoFS = new Estado("Fuera de Servicio", "Sismógrafo fuera de servicio", "Sismografo");
        CambioEstado nuevoCambio = new CambioEstado(obtenerHoraActual(), obtenerFechaActual());
        // Añadir el nuevo cambio de estado
        cambiosEstado.add(nuevoCambio);
        estadoActual = estadoFS;
    }

    // Métodos auxiliares
    private String obtenerHoraActual() {
        // Obtener la hora actual del sistema
        return "12:00"; // Implementar con fecha real
    }

    private String obtenerFechaActual() {
        // Obtener la fecha actual del sistema
        return "2025-05-21"; // Implementar con fecha real
}
}
