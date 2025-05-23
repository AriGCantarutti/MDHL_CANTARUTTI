package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Rol {
    private String nombre;
    private String descripcion;
    private List<String> permisos;

    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.permisos = new ArrayList();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPermisos() {
        return this.permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public boolean esResponsable() {
        return "Responsable de Inspecciones".equals(this.nombre) || this.permisos.contains("cerrar_orden_inspeccion");
    }

    public void agregarPermiso(String permiso) {
        if (!this.permisos.contains(permiso)) {
            this.permisos.add(permiso);
        }

    }

    public boolean tienePermiso(String permiso) {
        return this.permisos.contains(permiso);
    }
}
