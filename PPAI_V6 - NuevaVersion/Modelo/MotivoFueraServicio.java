package Modelo;

public class MotivoFueraServicio {
    private String comentario;
    private MotivosTipos motivosTipos;

    //Constructor
    public MotivoFueraServicio(String comentario, MotivosTipos motivosTipos) {
        this.comentario = comentario;
        this.motivosTipos = motivosTipos;
    }

    //Método Get y Set
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public void setMotivoTipos(MotivosTipos motivosTipos) {
        this.motivosTipos = motivosTipos;
    }
    public MotivosTipos getMotivosTipos() {
        return motivosTipos;
    }
}
