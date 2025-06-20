package org.example.Interfaz;

import org.example.controllers.PantallaOrdenController;
import org.example.models.MotivoTipo;

import java.util.List;

//CLASE BOUNDARY
public class PantallaOrden extends PantallaOrdenController {

    public PantallaOrden() {
        super();
        this.gestor.setPantalla(this);
    }

    @Override
    protected void opsCerrarOrden() {
        habilitar();
    }

    private void habilitar() {
        gestor.tomarOptCerrarOrd();
    }

    public void mostrarDatosOrden(int numeroOrden, String fechaFin, String nombreEstacion) {
        System.out.println("Orden de Inspección: " + numeroOrden);
        System.out.println("Fecha de Cierre: " + fechaFin);
        System.out.println("Estación Sismológica: " + nombreEstacion);

        // metodo java fx
        mostrarDatoEnTabla(numeroOrden, fechaFin, nombreEstacion);
    }

    //TODO: llamar una vez en el diagrama, no varias
    public void mostrarMotivos(List<MotivoTipo> motivos) {
        //metodo java fx
        mostrarMotivosEnPantalla(motivos);
    }

    @Override
    protected void tomarConfirmacion() {
        gestor.tomarConfirmacion();

        //metodo javar fx
        limpiarPantalla();
    }

    public void mostrarMensajeError(String titulo, String mensaje) {
        mostrarError(titulo, mensaje);
    }
}