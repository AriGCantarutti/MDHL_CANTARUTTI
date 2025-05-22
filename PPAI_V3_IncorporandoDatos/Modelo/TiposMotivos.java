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
    public List<String> obtenerMotivosFS() {
        // Obtener lista de motivos para poner fuera de servicio
        List<String> motivos = new ArrayList<>();
        
        // En una implementación real, obtendríamos los motivos de la base de datos
        // Para nuestro ejemplo, creamos algunos motivos predefinidos
        motivos.add("Falla de hardware");
        motivos.add("Falla de software");
        motivos.add("Mantenimiento preventivo");
        motivos.add("Calibración necesaria");
        motivos.add("Daño por factores externos");
        
        return motivos;
    }
}
