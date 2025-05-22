import Controlador.GestorOrden;

public class main {
    public static void main(String[] args) {
        // Crear instancias necesarias para la ejecución
        Interfaz.PantallaOrden pantalla = new Interfaz.PantallaOrden(0, 0, 0, 0);
        
        // Crear el gestor con valores iniciales
        GestorOrden gestor = new GestorOrden(0, "empleadoRI", 1, "datosOC", "motivo", "comentario", "confirmacion", 1, "emailResponsable", "notificacionesEmail", "monitor");
        
        // Establecer la pantalla en el gestor
        gestor.setPantalla(pantalla);
        
        // Iniciar el caso de uso
        gestor.opcCerrarOrden();
        
        // Simulación de interacción del usuario
        simularInteraccionUsuario(gestor, pantalla);
    }
    
    // Método para simular la interacción del usuario con el sistema
    private static void simularInteraccionUsuario(GestorOrden gestor, Interfaz.PantallaOrden pantalla) {
        System.out.println("\n--- SIMULACIÓN DE INTERACCIÓN DE USUARIO ---");
        
        // El usuario selecciona una orden (supongamos que la orden 1)
        System.out.println("\n[Usuario selecciona la orden #1]");
        gestor.tomarSeleccionOrden(1);
        
        // El usuario ingresa una observación
        System.out.println("\n[Usuario ingresa observación]");
        gestor.tomarObservacion("Sismógrafo presenta fallas en la lectura de datos");
        
        // El usuario selecciona un motivo y agrega un comentario
        System.out.println("\n[Usuario selecciona motivo y agrega comentario]");
        gestor.tomarMotivoYComentario("Falla de hardware", "Necesita reemplazo de componentes");
        
        // El usuario confirma la operación
        System.out.println("\n[Usuario confirma la operación]");
        gestor.tomarConfirmacion(true);
        
        System.out.println("\n--- FIN DE LA SIMULACIÓN ---");
    }
}
