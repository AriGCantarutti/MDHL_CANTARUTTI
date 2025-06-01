package Interfaz;

public class InterfazPantalla {
    private String notificacion;

    //Constructor
    public InterfazPantalla(String notificacion) {
        this.notificacion = notificacion;
    }

    //Métodos get y set
    public String getNotificacion(){
        return notificacion;
    }
    public void setNotificacion(String notificacion){
        this.notificacion = notificacion;
    }

    //Métodos del Monitor
    public void publicar(String mensaje) {
        // Publicar el mensaje en la pantalla/monitor
        this.notificacion = mensaje;
}
}
