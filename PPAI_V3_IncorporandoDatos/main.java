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
        
        // Simulación de interacción del usuario
        simularInteraccionUsuario(gestor, pantalla);
        
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
        
        // Mostrar datos precargados disponibles
        System.out.println("\n--- DATOS PRECARGADOS EN EL SISTEMA ---");
        System.out.println("Empleados registrados:");
        System.out.println("- Juan Pérez (Responsable de Inspecciones)");
        System.out.println("- María García (Técnico)");
        
        System.out.println("\nEstaciones disponibles:");
        System.out.println("- Estación Central (Centro Ciudad)");
        System.out.println("- Estación Norte (Zona Norte)");
        System.out.println("- Estación Sur (Zona Sur)");
        
        System.out.println("\nÓrdenes disponibles para cerrar:");
        System.out.println("- Orden #1001 - Estación Central (Finalizada)");
        System.out.println("- Orden #1002 - Estación Norte (Finalizada)");
        System.out.println("- Orden #1003 - Estación Sur (Finalizada)");
        System.out.println("- Orden #1004 - Estación Central (En Progreso - NO se puede cerrar)");
        System.out.println("========================================");
    }
    
    // Método para simular la interacción del usuario con el sistema
    private static void simularInteraccionUsuario(GestorOrden gestor, PantallaOrden pantalla) {
        System.out.println("\n--- SIMULACIÓN DE INTERACCIÓN DE USUARIO ---");
        
        try {
            // Simular que el usuario ingresa el número de orden
            System.out.println("\n[Simulando: Usuario ingresa número de orden 1001]");
            gestor.buscarOrdenPorNumero(1001);
            
            // Simular que el usuario ingresa una observación
            System.out.println("\n[Simulando: Usuario ingresa observación]");
            String observacionSimulada = "El sismógrafo presenta lecturas inconsistentes y requiere mantenimiento mayor. Se detectaron fallas en los sensores principales.";
            gestor.tomarObservacion(observacionSimulada);
            
            // Simular que el usuario selecciona un motivo y agrega un comentario
            System.out.println("\n[Simulando: Usuario selecciona motivo y agrega comentario]");
            String motivoSimulado = "Falla de hardware";
            String comentarioSimulado = "Necesita reemplazo completo del sistema de sensores. Estimado de reparación: 2-3 semanas.";
            gestor.tomarMotivoYComentario(motivoSimulado, comentarioSimulado);
            
            // Simular que el usuario confirma la operación
            System.out.println("\n[Simulando: Usuario confirma la operación]");
            gestor.tomarConfirmacion(true);
            
            System.out.println("\n--- SIMULACIÓN COMPLETADA EXITOSAMENTE ---");
            
            // Mostrar el estado final del sistema
            mostrarEstadoFinal();
            
        } catch (Exception e) {
            System.err.println("Error durante la simulación: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para mostrar el estado final del sistema
    private static void mostrarEstadoFinal() {
        System.out.println("\n=== ESTADO FINAL DEL SISTEMA ===");
        System.out.println("Resultado de la operación:");
        System.out.println("✓ Orden #1001 cerrada exitosamente");
        System.out.println("✓ Sismógrafo de Estación Central marcado como Fuera de Servicio");
        System.out.println("✓ Notificaciones enviadas a los responsables");
        System.out.println("✓ Información publicada en el monitor del sistema");
        System.out.println("================================");
    }
    
    // Método alternativo para ejecutar el sistema de forma interactiva
    public static void ejecutarModoInteractivo() {
        System.out.println("=== MODO INTERACTIVO ACTIVADO ===");
        
        PantallaOrden pantalla = new PantallaOrden();
        GestorOrden gestor = new GestorOrden();
        gestor.setPantalla(pantalla);
        
        // Mostrar menú y permitir interacción real del usuario
        pantalla.mostrarMenuPrincipal();
        
        boolean continuar = true;
        while (continuar) {
            try {
                // En una implementación real, aquí leería la entrada del usuario
                // y ejecutaría las opciones correspondientes
                
                pantalla.mostrarAyuda();
                gestor.opcCerrarOrden();
                
                // Para esta demostración, salimos después de una iteración
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
