package Modelo;
import java.util.ArrayList;
import java.util.List;

public class TiposMotivos {
    private String nombre;

    //Constructor
    public TiposMotivos(String nombre) {
        this.nombre = nombre;
    }

    //Métodos get y set
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //Métodos de Tipos Motivos
    public void obtenerMorivosFS() {}

    // Métodos a implementar
    public List<String> obtenerMotivosFS() {
        // Obtener lista de motivos para poner fuera de servicio
        List<String> motivos = new ArrayList<>();
        // Lógica para obtener los motivos
        return motivos;
    }
}
