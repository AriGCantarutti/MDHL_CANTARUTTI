package org.example.Interfaz;

public class InterfazEmail {
    private String notificacion;

    public InterfazEmail(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacion() {
        return this.notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public void enviarEmail(String[] destinatarios, String asunto, String cuerpo) {
        System.out.println("Enviando correo a: " + String.join(", ", destinatarios));
        System.out.println("Asunto: " + asunto);
        System.out.println("Cuerpo del mensaje: " + cuerpo);
    }
}
