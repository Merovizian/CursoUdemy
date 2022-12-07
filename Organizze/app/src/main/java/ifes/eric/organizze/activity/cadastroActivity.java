package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Usuario;

public class cadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        campoEmail = findViewById(R.id.editText_cadastro_email);
        campoNome = findViewById(R.id.editText_cadastro_nome);
        campoSenha = findViewById(R.id.editText_Cadastro_senha);
   }
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  efetuarCadastro   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void efetuarCadastro(View view) {
        // Método que efetua a validação do cadastro.

        String textoNome = campoNome.getText().toString();
        String textoSenha = campoSenha.getText().toString();
        String textoEmail = campoEmail.getText().toString();

        // Cria uma instancaia para o metodo validação.
        Validacao validacao = new Validacao();

        // Se for validado os dados então cria-se uma instancia de usuário com esses dados
        if (validacao.validar(textoNome, textoSenha, textoEmail, cadastroActivity.this) == 0) {
            usuario = new Usuario();
            usuario.setNome(textoNome);
            usuario.setEmail(textoEmail);
            usuario.setSenha(textoSenha);
            cadastrarUsuario();
        }
    }
//****************************************  efetuarCadastro  ***************************************

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Cadastrar firebaseAuth   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void cadastrarUsuario(){
        // Método que realiza o cadastro no firebaseAuth

        // Usa a classe configuraçãoFirebase para criar uma instancia que referencia o FirebaseAuth
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // Usa a classe usuario para fornecer os dados para a criação autenticador
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
                                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            // Caso a tarefa seja realizada
            public void onComplete(@NonNull Task<AuthResult> task) {
                // caso senha realizada com sucesso
                if (task.isSuccessful()){
                    // o ID usuario será o codigo64 do email
                    usuario.setIdUsuario(Base64Custom.codificarBase64( usuario.getEmail()));
                    // método da classe usuario que salva o usuario na firebase
                    usuario.salvar();
                    Toast.makeText(cadastroActivity.this, "Sucesso ao cadastrar "
                            + usuario.getNome(), Toast.LENGTH_SHORT).show();
                    finish();
                // caso há erros. (essas exceções não são corretas, tem que verificar)
                }else{
                    String excecao;
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Digite um email valido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Email já cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(cadastroActivity.this, excecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
//**************************************  Cadastrar firebaseAuth  **********************************

}



