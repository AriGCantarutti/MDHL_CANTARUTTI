package org.example.Interfaz;

import org.example.models.OrdenInspeccion;
import org.example.models.TiposMotivos;

import java.util.List;
import java.util.Scanner;

public class PantallaOrden {
    private boolean botonesHabilitados = false;
    private String mensajeActual = "";
    private Scanner scanner;

    public PantallaOrden() {
        this.scanner = new Scanner(System.in);
    }

    public void habilitar() {
        this.botonesHabilitados = true;
        System.out.println(">>> PANTALLA: Botones para cerrar orden habilitados");
        System.out.println(">>> PANTALLA: Sistema listo para recibir número de orden");
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: SISTEMA DE GESTIÓN DE ÓRDENES");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: 1. Cerrar Orden de Inspección");
        System.out.println(">>> PANTALLA: 2. Salir");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Seleccione una opción:");
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

    public void tomarNumeroOrden() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: NÚMERO DE ORDEN A CERRAR");
        System.out.println(">>> PANTALLA: ================================");
    }

    public void pedirObservacion() {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: INGRESE OBSERVACIÓN DE CIERRE");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: La observación es obligatoria.");
        System.out.println(">>> PANTALLA: Describa los detalles del cierre de la orden:");
    }

    public String tomarObservacion() {
        System.out.print(">>> Ingrese la observación de cierre: ");
        String observacion = this.scanner.nextLine().trim();
        if (observacion.isEmpty()) {
            System.out.println(">>> ERROR: La observación no puede estar vacía.");
            return null;
        } else {
            return observacion;
        }
    }

    public void mostrarMotivos(List<TiposMotivos> motivos) {
        System.out.println("\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: SELECCIONE MOTIVO PARA FUERA DE SERVICIO");
        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Motivos disponibles:");

        for(int i = 0; i < motivos.size(); ++i) {
            System.out.println(">>> PANTALLA: " + (i + 1) + ". " + ((TiposMotivos)motivos.get(i)).getNombre());
        }

        System.out.println(">>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: Seleccione el número del motivo");
        System.out.println(">>> PANTALLA: También puede agregar comentarios adicionales (opcional)");
    }

    public String tomarMotivoFS(List<TiposMotivos> listaMotivos) {
        System.out.print(">>> Seleccione el número del motivo (1-7): ");
        int posicionMotivo = this.scanner.nextInt();
        this.scanner.nextLine();
        if (posicionMotivo >= 1 && posicionMotivo <= listaMotivos.size()) {
            String motivoSeleccionado = ((TiposMotivos)listaMotivos.get(posicionMotivo - 1)).getNombre();
            return motivoSeleccionado;
        } else {
            System.out.println(">>> ERROR: Selección de motivo no válida.");
            return null;
        }
    }

    public String tomarComentariosFS() {
        System.out.print(">>> Ingrese comentario adicional (opcional, presione Enter para omitir): ");
        String comentario = this.scanner.nextLine().trim();
        return comentario;
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

    public int leerNumeroOrden() {
        try {
            String input = this.scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException var2) {
            System.out.println(">>> PANTALLA: ERROR: Debe ingresar un número válido");
            return -1;
        }
    }

    public String leerObservacion() {
        System.out.print(">>> PANTALLA: Ingrese observación: ");
        return this.scanner.nextLine().trim();
    }

    public String leerMotivo(List<TiposMotivos> motivos) {
        try {
            System.out.print(">>> PANTALLA: Seleccione motivo (número): ");
            int opcion = Integer.parseInt(this.scanner.nextLine().trim());
            if (opcion >= 1 && opcion <= motivos.size()) {
                return ((TiposMotivos)motivos.get(opcion - 1)).getNombre();
            } else {
                System.out.println(">>> PANTALLA: ERROR: Opción no válida");
                return null;
            }
        } catch (NumberFormatException var3) {
            System.out.println(">>> PANTALLA: ERROR: Debe ingresar un número válido");
            return null;
        }
    }

    public String leerComentario() {
        System.out.print(">>> PANTALLA: Ingrese comentario (opcional, presione Enter para omitir): ");
        return this.scanner.nextLine().trim();
    }

    public boolean leerConfirmacion() {
        System.out.print(">>> PANTALLA: Su respuesta (S/N): ");
        String respuesta = this.scanner.nextLine().trim().toUpperCase();
        return "S".equals(respuesta) || "SI".equals(respuesta) || "YES".equals(respuesta) || "Y".equals(respuesta);
    }

    public boolean estanBotonesHabilitados() {
        return this.botonesHabilitados;
    }

    public String getMensajeActual() {
        return this.mensajeActual;
    }

    public void limpiarPantalla() {
        System.out.println("\n\n>>> PANTALLA: ================================");
        System.out.println(">>> PANTALLA: LIMPIANDO PANTALLA...");
        System.out.println(">>> PANTALLA: ================================\n\n");
        this.mensajeActual = "";
    }

    public void cerrar() {
        if (this.scanner != null) {
            this.scanner.close();
        }

    }
}
