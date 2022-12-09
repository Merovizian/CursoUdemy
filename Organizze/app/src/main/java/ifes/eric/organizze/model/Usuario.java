package ifes.eric.organizze.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import ifes.eric.organizze.config.ConfiguracaoFirebase;

public class Usuario {
    private String nome, email, senha, idUsuario;

    public Double getReceitaTotal() {
        return 0.0;
    }


    public Double getDespesaTotal() {
        return 0.0;
    }

    public Usuario() {

    }

    // Salva o usuári no Firebase
    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    @Exclude
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
