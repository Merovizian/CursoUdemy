package ifes.eric.whatsapp.Activity;

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

import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.Model.Validacao;
import ifes.eric.whatsapp.R;
import ifes.eric.whatsapp.helper.UserFacilities;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha, campoNome;

    Usuario usuario;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoEmail = findViewById(R.id.imput_cadastro_email);
        campoNome = findViewById(R.id.imput_cadastro_nome);
        campoSenha = findViewById(R.id.imput_cadastro_senha);


        // configuração do titulo
        //getSupportActionBar().setTitle("Cadastro de novo Usuario");
        //getSupportActionBar().setElevation(0);
    }


//   ----------------------------------  Validação dos Dados  --------------------------------------
    public void validarDados(View view){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        // coloca nas variaveis acima o que foi digitado

        Validacao validacao = new Validacao();
        // Objeto de validação

        if(validacao.validar(nome, senha, email, CadastroActivity.this) == 0 ){
            // Verifica se o usuario não digitou algum dos campos acima

            usuario = new Usuario();
            // Cria-se uma instancia de objeto de usuarios.
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            efetuarCadastro();
        }
    }
//   ******************************     Validação dos Dados      ***********************************



//   ----------------------------------  Validação dos Dados  --------------------------------------
    public void efetuarCadastro(){

        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // Verifica se foi finalizada corretamente o cadastro.
                if (task.isSuccessful()) {

                    Toast.makeText(CadastroActivity.this, usuario.getNome() +
                            " Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    UserFacilities.AtualizarNomeUsuario(usuario.getNome());

                    finish();

                    // Bloco que faz a codificação do email de usuario para uma string
                    try{

                        String identificadorUsuario = UserFacilities.codificarString(usuario.getEmail());
                        usuario.setIdUsuario(identificadorUsuario);
                        usuario.salvar();

                    } catch (Exception e){
                        e.printStackTrace();
                    }


                } else {
                    String excecao;

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "A senha deve ter pelo menos 6 caracteres";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email invalido ou mal formatado";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Usuario já existente";
                    } catch (Exception e) {
                        excecao = e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_LONG).show();

                }
            }
            });



    }


//   ******************************     Validação dos Dados      ***********************************
}


