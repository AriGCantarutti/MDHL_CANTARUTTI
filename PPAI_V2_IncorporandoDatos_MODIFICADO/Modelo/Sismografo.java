package Modelo;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        this.cambiosEstado = new ArrayList<>();
        
        // Estado inicial "Operativo"
        this.estadoActual = new Estado("Operativo", "Sismógrafo operativo", "Sismografo");
        CambioEstado cambioInicial = new CambioEstado(null, null);
        cambioInicial.setEstado(estadoActual);
        cambiosEstado.add(cambioInicial);
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

    public Estado getEstadoActual() {
        return estadoActual;
    }

    // Métodos de Sismógrafo
    public void enviarAReparar() {
        // Finalizar el estado actual
        for (CambioEstado cambio : cambiosEstado) {
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(obtenerFechaActual(), obtenerHoraActual());
            }
        }
        
        // Cambiar el estado del sismógrafo a "Fuera de Servicio"
        Estado estadoFS = new Estado("Fuera de Servicio", "Sismógrafo fuera de servicio", "Sismografo");
        CambioEstado nuevoCambio = new CambioEstado(null, null);
        nuevoCambio.setEstado(estadoFS);
        
        // Añadir el nuevo cambio de estado
        cambiosEstado.add(nuevoCambio);
        estadoActual = estadoFS;
        
        System.out.println("Sismógrafo ID: " + id + " enviado a reparación. Estado: Fuera de Servicio");
    }

    // Métodos auxiliares
    private String obtenerHoraActual() {
        // Obtener la hora actual del sistema
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        // Obtener la fecha actual del sistema
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
