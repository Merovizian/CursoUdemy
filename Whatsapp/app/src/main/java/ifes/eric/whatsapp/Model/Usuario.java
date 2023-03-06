package ifes.eric.whatsapp.Model;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ifes.eric.whatsapp.helper.UserFacilities;

public class Usuario {
    private String Nome, Email, Senha, idUsuario, Foto;
    private ArrayList<String> listaContatos = new ArrayList<>();


    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

//   --------------------------- Metodo para Salvar Usuario no FB --------------------------------
    public void salvar(){

        // Aponta o bandoDados para a referencia do Firebase
        DatabaseReference bandoDados = FirebaseDatabase.getInstance().getReference();
        // Cria uma referencia que aponta para "usuarios"."email(codificado)
        DatabaseReference usuarioDB = bandoDados.child("usuarios").child(UserFacilities.codificarString(Email));
        // Salva todas as variaveis pertencentes a classe Usuario, exceto as que são marcadas com
        // @Exclude
        usuarioDB.setValue(this);
    }
//   **************************  Metodo para Salvar Usuario no FB   *******************************


//   --------------------------- Atualiza os dados do usuario no BD --------------------------------
    public void atualizarUsuario(){

        String identificacaoUser = UserFacilities.UsuarioEmailB64();

        // Acessa o BD do usuario atraves do email B64
        DatabaseReference usuarioDatabase = FirebaseDatabase.getInstance().getReference()
                .child("usuarios")
                .child(identificacaoUser);

        // Map é um objeto que mapeia valores por chaves
        Map<String, Object> valorUsuario = converteParaMap();

        // Função do proprio Firebase para atualizar (e não sobreescrever no BD)
        usuarioDatabase.updateChildren(valorUsuario);

    }

    @Exclude
    // Cria a função map com os valores e chaves fornecidos.
    public Map<String, Object> converteParaMap(){

        HashMap<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("email", getEmail());
        usuarioMap.put("nome", getNome());
        usuarioMap.put("foto", getFoto());

        return usuarioMap;
    }
//   *************************  Atualiza os dados do usuario no BD   *******************************




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
