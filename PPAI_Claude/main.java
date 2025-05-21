public class main {
    public static void main (String[] args) {
        GestorOrden gestor = new GestorOrden(0, "empleadoRI", 1, "datosOC", "motivo", "comentario", "confirmacion", 1, "emailResponsable", "notificacionesEmail", "monitor");
        gestor.opcCerrarOrden();
    }
}