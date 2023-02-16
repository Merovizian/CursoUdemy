package ifes.eric.whatsapp.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.prefs.BackingStoreException;

import ifes.eric.whatsapp.helper.Base64Custom;

public class Usuario {
    private String Nome, Email, Senha, idUsuario;

//   --------------------------- Metodo para Salvar Usuario no FB --------------------------------
    public void salvar(){

        // Aponta o bandoDados para a referencia do Firebase
        DatabaseReference bandoDados = FirebaseDatabase.getInstance().getReference();
        // Cria uma referencia que aponta para "usuarios"."email(codificado)
        DatabaseReference usuarioDB = bandoDados.child("usuarios").child(Base64Custom.codificarString(Email));
        // Salva todas as variaveis pertencentes a classe Usuario, exceto as que são marcadas com
        // @Exclude
        usuarioDB.setValue(this);
    }
//   **************************  Metodo para Salvar Usuario no FB   *******************************
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public Usuario() {
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    @Exclude
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
