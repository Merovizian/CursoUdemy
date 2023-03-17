package ifes.eric.whatsapp.Model;

import com.google.firebase.database.Exclude;

public class Mensagem {
    String idUsuario, idDestinatario, mensagem, imagem;

    public Mensagem() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Exclude
    public String getIdDestinatario() {
        return idDestinatario;
    }

    @Exclude
    public void setIdDestinatario(String idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
