package org.example.Interfaz;

public class InterfazPantalla {
    private String notificacion;

    public InterfazPantalla(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacion() {
        return this.notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public void publicar(String mensaje) {
        this.notificacion = mensaje;
    }
}
