package ifes.eric.whatsapp.Model;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Conversa {
    private String idRemetente, idDestinatario, ultimaMensagem;
    private Usuario usuarioExibicao;


    public Conversa() {
    }

    public void salvar(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference convesaREF = databaseReference.child("conversas");

        convesaREF.child(this.getIdRemetente())
                .child(this.getIdDestinatario())
                .setValue(this);

    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(String idRemetente) {
        this.idRemetente = idRemetente;
    }

    public String getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(String idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    public Usuario getUsuarioExibicao() {
        return usuarioExibicao;
    }

    public void setUsuarioExibicao(Usuario usuarioExibicao) {
        this.usuarioExibicao = usuarioExibicao;
    }


}
