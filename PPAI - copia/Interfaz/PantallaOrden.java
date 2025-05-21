package Interfaz;

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
    public void opcCerrarOrden() {}
    public void habilitar() {}
    public void mostrarOrdInsp() {}
    public void pedirObservacion() {}
    public void tomarObservacion() {}
    public void mostrarMotivos() {}
    public void mostrarMotivosFC() {}
    public void tomarComentariosFC() {}
    public void pedirConfirmacion() {}
    public void tomarConfirmacion() {}
}
