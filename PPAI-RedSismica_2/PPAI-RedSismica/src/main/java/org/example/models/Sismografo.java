//CONSULTAR CHICAS LOS OTROS METODOS QUE NO USAMOS EN EL DIAGRAMA DE SECUENCIA
package org.example.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Sismografo {
    private int identificadorSismografo;
    private String fechaAdquisicion;
    private int nroSerie;
    private EstacionSismologica estacion;
    private Estado estadoActual;
    private List<CambioEstado> cambiosEstado;

    public Sismografo(int identificadorSismografo, String fechaAdquisicion, int nroSerie) {
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.fechaAdquisicion = fechaAdquisicion;
        this.cambiosEstado = new ArrayList();
        this.estadoActual = Estado.OPERATIVO;
        CambioEstado cambioInicial = new CambioEstado((String)null, (String)null, (String)null, (String)null);
        cambioInicial.setEstado(this.estadoActual);
        this.cambiosEstado.add(cambioInicial);
    }

    public int getIdentificadorSismografo() {
        return this.identificadorSismografo;
    }

    public void setIdentificadorSismografo(int identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public int getNroSerie() {
        return this.nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
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
        Iterator CambioEstadoIterator = this.cambiosEstado.iterator();

        while(CambioEstadoIterator.hasNext()) {
            CambioEstado cambio = (CambioEstado)CambioEstadoIterator.next();
            if (cambio.esActual()) {
                cambio.setFechaHoraFin(this.obtenerFechaActual(), this.obtenerHoraActual());
            }
        }

        Estado estadoFS = Estado.FUERA_DE_SERVICIO;
        CambioEstado nuevoCambio = new CambioEstado((String)null, (String)null, (String)null, (String)null);
        nuevoCambio.setEstado(estadoFS);
        this.cambiosEstado.add(nuevoCambio);
        this.estadoActual = estadoFS;
        System.out.println("Sismógrafo ID: " + this.identificadorSismografo + " enviado a reparación. Estado: Fuera de Servicio");
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
