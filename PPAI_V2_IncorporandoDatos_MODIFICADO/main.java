import java.util.InputMismatchException;
import java.util.Scanner;

import Controlador.GestorOrden;
import Interfaz.PantallaOrden;

public class main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE GESTIÓN DE ÓRDENES DE INSPECCIÓN ===");
        
        // Crear instancias necesarias para la ejecución
        PantallaOrden pantalla = new PantallaOrden();
        GestorOrden gestor = new GestorOrden();
        
        // Establecer la pantalla en el gestor
        gestor.setPantalla(pantalla);
        
        // Mostrar información inicial del sistema
        mostrarInformacionSistema();
        
        // Iniciar el caso de uso
        gestor.opcCerrarOrden();
        
        // Ejecutar Modo interactivo
        ejecutarModoInteractivo();        
        
        // Cerrar recursos
        pantalla.cerrar();
        
        System.out.println("\n=== SISTEMA FINALIZADO ===");
    }
    
    // Método para mostrar información del sistema
    private static void mostrarInformacionSistema() {
        System.out.println("\n=== INFORMACIÓN DEL SISTEMA ===");
        System.out.println("Sistema: Gestión de Órdenes de Inspección Sismográfica");
        System.out.println("Versión: 2.0 - Con datos precargados");
        System.out.println("Funcionalidad: Cerrar Orden de Inspección");
        System.out.println("================================");
    }
    
    // Método para ejecutar el sistema de forma interactiva
    public static void ejecutarModoInteractivo() {
        Scanner input = new Scanner(System.in);

        System.out.println("=== MODO INTERACTIVO ACTIVADO ===");
        
        PantallaOrden pantalla = new PantallaOrden();
        GestorOrden gestor = new GestorOrden();
        gestor.setPantalla(pantalla);
        
        // Mostrar menú y permitir interacción real del usuario
        pantalla.mostrarMenuPrincipal();
        
        boolean continuar = true;
        while (continuar) {
            try {
                gestor.opcCerrarOrden();
                
                // Paso 1: ingresar numero de orden
                System.out.println("Ingrese número de orden: ");
                int nroOrden = input.nextInt(); 
                input.nextLine(); // LIMPIAR BUFFER

                    //if (nroOrden < 0) {
                    //    throw new Error("El numero de orden no puede ser negativo.");
                    // } 
                
                gestor.buscarOrdenPorNumero(nroOrden);

                // Paso 2: Observación
                System.out.println("Ingrese observacion final: ");
                String observaciones = input.nextLine();
                gestor.tomarObservacion(observaciones);

                
                // Paso 3: Motivo de cierre
                System.out.println("Motivos disponibles: ");
                List<TiposMotivos> listaMotivos = gestor.buscarMotivos();

                System.out.println("Selección de motivo: ");
                int posicionMotivoSeleccionado = input.nextInt();
                String motivoSelecc = listaMotivos.get(motivoSeleccionado)            



                pantalla.mostrarAyuda();
                gestor.opcCerrarOrden();
                
                // Para esta demostración, salimos después de una iteración
                continuar = false;
                
            } catch (InputMismatchException e) {
                System.err.println("Error en modo interactivo: " + e.getMessage());
                continuar = false;
            } catch (Exception e) {
                System.err.println("Error en modo interactivo: " + e.getMessage());
                continuar = false;
            }
        }
        
        pantalla.cerrar();
        System.out.println("=== MODO INTERACTIVO FINALIZADO ===");
    }
}
