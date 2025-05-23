//Importamos Librerías
import java.util.InputMismatchException;
import java.util.Scanner;

//Importamos paquetes
import Controlador.GestorOrden;
import Interfaz.InterfazPantalla;
import Interfaz.PantallaOrden;
import Interfaz.InterfazEmail;
import java.util.List;

import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;

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
        //gestor.opcCerrarOrden();
        
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
    
    //Método para ejecutar el sistema de forma interactiva
    public static void ejecutarModoInteractivo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== MODO INTERACTIVO ACTIVADO ===");
        
        PantallaOrden pantalla = new PantallaOrden();
        GestorOrden gestor = new GestorOrden();
        gestor.setPantalla(pantalla);
        
        //Mostrar menú y permitir interacción real del usuario
        pantalla.mostrarMenuPrincipal();
        
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.print(">>> Seleccione una opción (1-2): ");
                int opcion = input.nextInt();
                input.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        // Opción 1: Cerrar Orden de Inspección
                        ejecutarCierreOrden(gestor, input, pantalla);
                        break;
                        
                    case 2:
                        // Opción 2: Salir
                        continuar = false;
                        System.out.println(">>> Saliendo del sistema...");
                        break;
                        
                    default:
                        System.out.println(">>> ERROR: Opción no válida. Seleccione 1 o 2.");
                        break;
                }
                
                if (continuar) {
                    System.out.println("\n>>> ¿Desea realizar otra operación? (S/N)");
                    String respuesta = input.nextLine().trim().toUpperCase();
                    if ("N".equals(respuesta) || "NO".equals(respuesta)) {
                        continuar = false;
                        System.out.println(">>> Saliendo del sistema...");
                    } else {
                        pantalla.mostrarMenuPrincipal();
                    }
                }
                
            } catch (InputMismatchException e) {
                System.err.println(">>> ERROR: Debe ingresar un número válido.");
                input.nextLine(); // Limpiar buffer después del error
            } catch (Exception e) {
                System.err.println(">>> ERROR en modo interactivo: " + e.getMessage());
                input.nextLine(); // Limpiar buffer
            }
        }
        
        input.close();
        pantalla.cerrar();
        System.out.println("=== MODO INTERACTIVO FINALIZADO ===");
    }

//Método auxiliar para ejecutar el proceso de cierre de orden
    private static void ejecutarCierreOrden(GestorOrden gestor, Scanner input, PantallaOrden pantalla) {
        try {
            //Inicializar el caso de uso
            gestor.opcCerrarOrden();

            //Buscamos y mostramos Órdenes de Inspección
             List<OrdenInspeccion> ordenes = gestor.buscarOrdInspeccion();

            //Mostrar órdenes encontradas
            for (OrdenInspeccion orden : ordenes) {
            pantalla.mostrarDatosOrden(orden);
            }

            //Solicitamos que ingrese número de orden
            pantalla.solicitarNumeroOrden();
            
            //Ingresar número de orden
            System.out.print(">>> Ingrese el número de orden a cerrar: ");
            int nroOrden = input.nextInt();
            input.nextLine(); // Limpiar buffer
            
            //Validar que el número sea positivo
            if (nroOrden <= 0) {
                System.out.println(">>> ERROR: El número de orden debe ser positivo.");
                return;
            }
            
            //gestor.buscarOrdenPorNumero(nroOrden);
            
            //Ingresar observación
            System.out.print(">>> Ingrese la observación de cierre: ");
            String observacion = input.nextLine().trim();
            
            if (observacion.isEmpty()) {
                System.out.println(">>> ERROR: La observación no puede estar vacía.");
                return;
            }
            
            gestor.tomarObservacion(observacion);
            
            //Seleccionar motivo
            System.out.print(">>> Seleccione el número del motivo (1-7): ");
            int posicionMotivo = input.nextInt();
            input.nextLine(); // Limpiar buffer
            
            //Obtener los motivos disponibles
            java.util.List<Modelo.TiposMotivos> listaMotivos = gestor.buscarMotivos();
            
            //Validar la selección
            if (posicionMotivo < 1 || posicionMotivo > listaMotivos.size()) {
                System.out.println(">>> ERROR: Selección de motivo no válida.");
                return;
            }
            
            String motivoSeleccionado = listaMotivos.get(posicionMotivo - 1).getNombre();
            
            //Comentario opcional
            System.out.print(">>> Ingrese comentario adicional (opcional, presione Enter para omitir): ");
            String comentario = input.nextLine().trim();
            
            gestor.tomarMotivoYComentario(motivoSeleccionado, comentario);
            
            //Confirmación
            System.out.print(">>> ¿Confirma el cierre de la orden? (S/N): ");
            String confirmacion = input.nextLine().trim().toUpperCase();
            
            boolean confirmado = "S".equals(confirmacion) || "SI".equals(confirmacion) || 
                            "YES".equals(confirmacion) || "Y".equals(confirmacion);
            
            gestor.tomarConfirmacion(confirmado);
                        
        } catch (InputMismatchException e) {
            System.err.println(">>> ERROR: Entrada no válida. Debe ingresar un número.");
            input.nextLine(); // Limpiar buffer
        } catch (IndexOutOfBoundsException e) {
            System.err.println(">>> ERROR: Selección de motivo fuera de rango.");
        } catch (Exception e) {
            System.err.println(">>> ERROR durante el cierre de orden: " + e.getMessage());
        }
    }
}

/*// Método auxiliar para mostrar las órdenes disponibles
    private static void mostrarOrdenesDisponibles() {
        System.out.println("\n>>> ÓRDENES DISPONIBLES EN EL SISTEMA:");
        System.out.println(">>> =====================================");
        
        java.util.List<Modelo.OrdenInspeccion> ordenes = Controlador.GestorOrden.obtenerTodasLasOrdenes();
        
        for (Modelo.OrdenInspeccion orden : ordenes) {
            System.out.println(">>> Orden #" + orden.getNumeroDeOrden() + 
                            " - " + orden.getNombreEstacion() + 
                            " (" + orden.getEstadoActual() + ")");
            System.out.println(">>>   Fecha: " + orden.getFechaGeneracion());
            System.out.println(">>>   Motivo: " + orden.getTipoMotivo());
            System.out.println(">>>   Responsable: " + orden.getEmpleadoResponsable().getNombre());
            System.out.println(">>> ------------------------------------");
        }
        
        System.out.println(">>> =====================================");
    }
}*/
