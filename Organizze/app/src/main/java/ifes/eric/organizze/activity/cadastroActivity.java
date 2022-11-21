package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import org.w3c.dom.Text;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Usuario;

public class cadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        campoEmail = findViewById(R.id.editText_cadastro_email);
        campoNome = findViewById(R.id.editText_cadastro_nome);
        campoSenha = findViewById(R.id.editText_Cadastro_senha);
   }

    public void efetuarCadastro(View view) {
        String textoNome = campoNome.getText().toString();
        String textoSenha = campoSenha.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        Validacao validacao = new Validacao();


        if (validacao.validar(textoNome, textoSenha, textoEmail, cadastroActivity.this) == 0) {
            usuario = new Usuario();
            usuario.setNome(textoNome);
            usuario.setEmail(textoEmail);
            usuario.setSenha(textoSenha);
            cadastrarUsuario();
        }
    }

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
                                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    String idUsuario = Base64Custom.codificarBase64( usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    Toast.makeText(cadastroActivity.this, "Sucesso ao cadastrar "
                            + usuario.getNome(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
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
}



