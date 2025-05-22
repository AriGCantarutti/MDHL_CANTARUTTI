package Interfaz;
import java.util.List;
import Modelo.OrdenInspeccion;
import Modelo.TiposMotivos;

public class PantallaOrden {
    private int checkboxOrden;
    private int boxObservacion;
    private int boxMotivos;
    private int checkboxConfirmacion;

    //Constructor
    public PantallaOrden(int checkboxOrden, int boxObservacion, int boxMotivos, int checkboxConfirmacion) {
        this.checkboxOrden = checkboxOrden;
        this.boxObservacion = boxObservacion;
        this.boxMotivos = boxMotivos;
        this.checkboxConfirmacion = checkboxConfirmacion;
    }

    //Métodos get y set
    public int getCheckboxOrden() {
        return checkboxOrden;
    }

    public void setCheckboxOrden(int checkboxOrden) {
        this.checkboxOrden = checkboxOrden;
    }

    public int getBoxObservacion() {
        return boxObservacion;
    }

    public void setBoxObservacion(int boxObservacion) {
        this.boxObservacion = boxObservacion;
    }

    public int getBoxMotivos() {
        return boxMotivos;
    }

    public void setBoxMotivos(int boxMotivos) {
        this.boxMotivos = boxMotivos;
    }

    public int getCheckboxConfirmacion() {
        return checkboxConfirmacion;
    }

    public void setCheckboxConfirmacion(int checkboxConfirmacion) {
        this.checkboxConfirmacion = checkboxConfirmacion;
    }

    //Métodos de la Pantalla de Orden
    ///Métodos de la Pantalla de Orden
    // Habilita los botones necesarios para cerrar una orden
    public void habilitarBotonesCerrarOrden() {
        // Implementación para habilitar botones en la interfaz
        System.out.println("Botones para cerrar orden habilitados");
        // En una interfaz gráfica real cambiarías el estado de componentes
        // Ejemplo: btnCerrarOrden.setEnabled(true);
    }
    
    // Muestra la lista de órdenes de inspección disponibles
    public void mostrarOrdInsp() {
        // Método para mostrar las órdenes de inspección
        System.out.println("Mostrando órdenes de inspección disponibles");
        
        // En una interfaz gráfica real mostrarías los datos en una tabla o lista
        // Ejemplo: tablaOrdenes.setModel(new OrdenesTableModel(listaOrdenes));
    }

    //El usuario ingresa la Orden de inspección seleccionada
    public void tomarOrdInsp(){
        // Simular la selección de una orden por parte del usuario
        int numeroOrdenSeleccionada = 1; // Este valor podría ser ingresado por el usuario en una interfaz real
        System.out.println("Orden seleccionada: " + numeroOrdenSeleccionada);
    }
    
    //Solicita al usuario que ingrese una observación
    public void pedirObservacion() {
        // Método para solicitar una observación
        System.out.println("Por favor ingrese una observación:");
        // En una interfaz gráfica real mostrarías un campo de texto
        // Ejemplo: panelObservacion.setVisible(true);
    }

    //El usuario ingresa una Observación
    public void tomarObservacion(){
        // Simular la entrada de una observación
        String observacion = "El sismógrafo muestra lecturas inestables";
        System.out.println("Observación ingresada: " + observacion);
        // En una aplicación real esto vendría de un campo de texto
    }
    
    //Muestra los motivos para poner un sismógrafo fuera de servicio
    public void mostrarMotivosFC() {
        // Método para mostrar los motivos
        System.out.println("Seleccione motivos para poner fuera de servicio:");
        // En una interfaz gráfica real mostrarías una lista o combo
        // Ejemplo: comboMotivos.setModel(new MotivosComboModel(listaMotivos));
    }

    //El usuario ingresa un Motivo
    public void tomarMotivoFS(){
        // Simular la selección de un motivo
        String motivo = "Falla de hardware";
        System.out.println("Motivo seleccionado: " + motivo);
        // En una aplicación real esto vendría de un combobox o lista
    }

    //El usuario ingresa un Comentario
    public void tomarComentarioFS(){
        // Simular la entrada de un comentario
        String comentario = "Necesita reemplazo de sensor principal";
        System.out.println("Comentario ingresado: " + comentario);
        // En una aplicación real esto vendría de un campo de texto
    }

    //El usuario ingresa la confirmación
    public void tomarConfirmacion() {
        // Simular la confirmación del usuario
        boolean confirmado = true;
        System.out.println("Usuario confirmó: " + confirmado);
        // En una aplicación real esto vendría de un diálogo de confirmación
    }
    
    //Solicita confirmación al usuario
    public void pedirConfirmacion() {
        // Método para pedir confirmación
        System.out.println("¿Confirma cerrar la orden? (S/N)");
        
        // En una interfaz gráfica real mostrarías un diálogo de confirmación
        // Ejemplo: boolean confirma = JOptionPane.showConfirmDialog(...) == JOptionPane.YES_OPTION;
    }

    //Métodos Adicionales
    public void mostrarError(String mensaje) {
        System.err.println("ERROR: " + mensaje);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("MENSAJE: " + mensaje);
    }
}
