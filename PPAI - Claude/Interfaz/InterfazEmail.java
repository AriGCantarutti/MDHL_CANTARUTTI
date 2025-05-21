package Interfaz;

public class InterfazEmail {
    private String notificacion;

    //Constructor
    public InterfazEmail(String notificacion) {
        this.notificacion = notificacion;
    }

    //Métodos get y set
    public String getNotificacion(){
        return notificacion;
    }

    public void setNotificacion(String notificacion){
        this.notificacion = notificacion;
    }


    //Métodos de la Interfaz Email
    public void enviarEmail() {}

    // Métodos a implementar
    public void enviarEmail(String[] destinatarios, String asunto, String cuerpo) {
        // Configurar y enviar el email
        this.notificacion = cuerpo;
        enviarEmail();
    }
}
