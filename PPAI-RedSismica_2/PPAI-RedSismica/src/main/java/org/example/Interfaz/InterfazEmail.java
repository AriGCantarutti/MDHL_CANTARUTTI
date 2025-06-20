package org.example.Interfaz;
import javafx.scene.control.Alert;

import java.util.List;

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

    public void enviarEmail(List<String> destinatarios) {
        Alert alertaCorreo = new Alert(Alert.AlertType.INFORMATION);
        alertaCorreo.setTitle("Notificación por Correo");
        alertaCorreo.setHeaderText("Notificaciones enviadas por correo");
        alertaCorreo.setContentText("Se notificó a: " + String.join(", ", destinatarios));
        alertaCorreo.showAndWait();
    }
}
