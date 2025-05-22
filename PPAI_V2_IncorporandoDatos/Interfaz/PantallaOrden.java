package Interfaz;

import java.util.List;
import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;

public class PantallaOrden {
    private boolean botonesHabilitados;
    private String mensajeActual;

    // Constructor simplificado
    public PantallaOrden() {
        this.botonesHabilitados = false;
        this.mensajeActual = "";
    }

    // Métodos de la interfaz de usuario
    public void habilitarBotonesCerrarOrden() {
        this.botonesHabilitados = true;
        System.out.println(">>> PANTALLA: Botones para cerrar orden habilitados");
        System.out.println(">>> PANTALLA: Sistema listo para recibir número de orden");
    }

    public void solicitarNumeroOrden() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: INGRESE EL NÚMERO DE ORDEN A CERRAR");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Órdenes disponibles en el sistema:");
        System.out.println(">>> PANTALLA: - Orden #1001 (Estación Central)");
        System.out.println(">>> PANTALLA: - Orden #1002 (Estación Norte)");
        System.out.println(">>> PANTALLA: - Orden #1003 (Estación Sur)");
        System.out.println(">>> PANTALLA: Por favor ingrese el número de orden:");
    }

    public void mostrarDatosOrden(OrdenInspeccion orden) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: DATOS DE LA ORDEN SELECCIONADA");
        System.out.println(">>> PANTALLA: ================================");
    }
}