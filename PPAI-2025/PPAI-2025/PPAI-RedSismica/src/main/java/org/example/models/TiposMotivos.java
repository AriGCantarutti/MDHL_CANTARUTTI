package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class TiposMotivos {
    private String nombre;

    public TiposMotivos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> obtenerMotivosFS() {
        List<String> motivos = new ArrayList();
        motivos.add("Falla de hardware");
        motivos.add("Falla de software");
        motivos.add("Mantenimiento preventivo");
        motivos.add("Calibración necesaria");
        motivos.add("Daño por factores externos");
        return motivos;
    }
}

