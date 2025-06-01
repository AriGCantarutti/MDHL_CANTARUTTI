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
    public void enviarEmail(String[] destinatarios, String asunto, String cuerpo) {
        // Simular el envío de un correo electrónico
        System.out.println("Enviando correo a: " + String.join(", ", destinatarios));
        System.out.println("Asunto: " + asunto);
        System.out.println("Cuerpo del mensaje: " + cuerpo);
    }
}
