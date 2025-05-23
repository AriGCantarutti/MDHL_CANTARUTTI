package org.example.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Sismografo {
    private int id;
    private String fabricante;
    private String modelo;
    private String fechaAdquisicion;
    private Estado estadoActual;
    private List<CambioEstado> cambiosEstado;

    public Sismografo(int id, String fabricante, String modelo, String fechaAdquisicion) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.cambiosEstado = new ArrayList();
        this.estadoActual = new Estado("Operativo", "Sismógrafo operativo", "Sismografo");
        CambioEstado cambioInicial = new CambioEstado((String)null, (String)null);
        cambioInicial.setEstado(this.estadoActual);
        this.cambiosEstado.add(cambioInicial);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFechaAdquisicion() {
        return this.fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Estado getEstadoActual() {
        return this.estadoActual;
    }

    public void enviarAReparar() {
        Iterator var2 = this.cambiosEstado.iterator();

        while(var2.hasNext()) {
            CambioEstado cambio = (CambioEstado)var2.next();
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(this.obtenerFechaActual(), this.obtenerHoraActual());
            }
        }

        Estado estadoFS = new Estado("Fuera de Servicio", "Sismógrafo fuera de servicio", "Sismografo");
        CambioEstado nuevoCambio = new CambioEstado((String)null, (String)null);
        nuevoCambio.setEstado(estadoFS);
        this.cambiosEstado.add(nuevoCambio);
        this.estadoActual = estadoFS;
        System.out.println("Sismógrafo ID: " + this.id + " enviado a reparación. Estado: Fuera de Servicio");
    }

    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }



}
