package Interfaz;

import java.util.List;
import java.util.Scanner;
import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;

public class PantallaOrden {
    private boolean botonesHabilitados;
    private String mensajeActual;
    private Scanner scanner;

    // Constructor simplificado
    public PantallaOrden() {
        this.botonesHabilitados = false;
        this.mensajeActual = "";
        this.scanner = new Scanner(System.in);
    }

    // Métodos de la interfaz de usuario
    public void habilitarBotonesCerrarOrden() {
        this.botonesHabilitados = true;
        System.out.println(">>> PANTALLA: Botones para cerrar orden habilitados");
        System.out.println(">>> PANTALLA: Sistema listo para recibir número de orden");
    }

     // Método para mostrar el menú principal
    public void mostrarMenuPrincipal() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: SISTEMA DE GESTIÓN DE ÓRDENES");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: 1. Cerrar Orden de Inspección");
        System.out.println(">>> PANTALLA: 2. Salir");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Seleccione una opción:");
    }


    public void solicitarNumeroOrden() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: NÚMERO DE ORDEN A CERRAR");
        System.out.println(">>> PANTALLA: ================================");
    }

    public void mostrarDatosOrden(OrdenInspeccion orden) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: DATOS DE LA ORDEN ENCONTRADAS");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Número de Orden: #" + orden.getNumeroDeOrden());
        System.out.println(">>> PANTALLA: Fecha de Generación: " + orden.getFechaGeneracion());
        System.out.println(">>> PANTALLA: Tipo de Motivo: " + orden.getTipoMotivo());
        System.out.println(">>> PANTALLA: Estación: " + orden.getNombreEstacion());
        System.out.println(">>> PANTALLA: Estado Actual: " + orden.getEstadoActual());
        System.out.println(">>> PANTALLA: Responsable: " + orden.getEmpleadoResponsable().getNombre());
        System.out.println(">>> PANTALLA: ================================");
    }

    public void pedirObservacion() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: INGRESE OBSERVACIÓN DE CIERRE");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: La observación es obligatoria.");
        System.out.println(">>> PANTALLA: Describa los detalles del cierre de la orden:");
    }

    public void mostrarMotivosFC(List<TiposMotivos> motivos) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: SELECCIONE MOTIVO PARA FUERA DE SERVICIO");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Motivos disponibles:");
        
        for (int i = 0; i < motivos.size(); i++) {
            System.out.println(">>> PANTALLA: " + (i + 1) + ". " + motivos.get(i).getNombre());
        }
        
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Seleccione el número del motivo");
        System.out.println(">>> PANTALLA: También puede agregar comentarios adicionales (opcional)");
    }

    public void pedirConfirmacion(OrdenInspeccion orden, String observacion, String motivo, String comentario) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: CONFIRMAR CIERRE DE ORDEN");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: RESUMEN DE LA OPERACIÓN:");
        System.out.println(">>> PANTALLA: - Orden: #" + orden.getNumeroDeOrden());
        System.out.println(">>> PANTALLA: - Estación: " + orden.getNombreEstacion());
        System.out.println(">>> PANTALLA: - Observación: " + observacion);
        System.out.println(">>> PANTALLA: - Motivo: " + motivo);
        if (comentario != null && !comentario.trim().isEmpty()) {
            System.out.println(">>> PANTALLA: - Comentario: " + comentario);
        }
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: ¿Confirma el cierre de la orden? (S/N)");
        System.out.println(">>> PANTALLA: ATENCIÓN: Esta acción marcará el sismógrafo como Fuera de Servicio");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: MENSAJE DEL SISTEMA");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: " + mensaje);
        System.out.println(">>> PANTALLA: ================================");
        this.mensajeActual = mensaje;
    }

    public void mostrarError(String error) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: *** ERROR ***");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: " + error);
        System.out.println(">>> PANTALLA: ================================");
        this.mensajeActual = "ERROR: " + error;
    }

    // Métodos de entrada de datos (simulan la interacción del usuario)
    public int leerNumeroOrden() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(">>> PANTALLA: ERROR: Debe ingresar un número válido");
            return -1;
        }
    }

    public String leerObservacion() {
        System.out.print(">>> PANTALLA: Ingrese observación: ");
        return scanner.nextLine().trim();
    }

    public String leerMotivo(List<TiposMotivos> motivos) {
        try {
            System.out.print(">>> PANTALLA: Seleccione motivo (número): ");
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            
            if (opcion >= 1 && opcion <= motivos.size()) {
                return motivos.get(opcion - 1).getNombre();
            } else {
                System.out.println(">>> PANTALLA: ERROR: Opción no válida");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println(">>> PANTALLA: ERROR: Debe ingresar un número válido");
            return null;
        }
    }

    public String leerComentario() {
        System.out.print(">>> PANTALLA: Ingrese comentario (opcional, presione Enter para omitir): ");
        return scanner.nextLine().trim();
    }

    public boolean leerConfirmacion() {
        System.out.print(">>> PANTALLA: Su respuesta (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return "S".equals(respuesta) || "SI".equals(respuesta) || "YES".equals(respuesta) || "Y".equals(respuesta);
    }

    // Métodos de estado
    public boolean estanBotonesHabilitados() {
        return botonesHabilitados;
    }

    public String getMensajeActual() {
        return mensajeActual;
    }

    // Método para limpiar la pantalla (simulado)
    public void limpiarPantalla() {
        System.out.println("\n\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: LIMPIANDO PANTALLA...");
        System.out.println(">>> PANTALLA: ================================\n\n");
        this.mensajeActual = "";
    }

    // Método para cerrar recursos
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}