package ifes.eric.whatsapp.Model;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ifes.eric.whatsapp.helper.Base64Custom;

public class Usuario {
    private String nome, email, senha, idUsuario;


    public void salvar(){

        DatabaseReference bandoDados = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuarioDB = bandoDados.child("usuarios");
        usuarioDB = usuarioDB.child(Base64Custom.codificarString(nome));
        usuarioDB.child("Nome").setValue(nome,1);
        usuarioDB.child("E-mail").setValue(email,2);
        usuarioDB.child("Senha").setValue(senha,3);


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
