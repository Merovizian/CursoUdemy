package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.model.Usuario;

public class loginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Usuario usuario;

    // variavel criada para evitar duplicação de logins
    public int teste = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        campoEmail = findViewById(R.id.editText_login_email);
        campoSenha = findViewById(R.id.editText_login_senha);
    }


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Solicita Login    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void efetuarLogin(View view) {
        String textoSenha = campoSenha.getText().toString();
        String textoEmail = campoEmail.getText().toString();

        //Utiliza a classe validação para verificar se os dados são consistentes.
        Validacao validacao = new Validacao();


        if (validacao.validar(textoSenha, textoEmail, loginActivity.this) == 0) {
            // caso sejam consistentes, cria-se uma instancia usuario.
            usuario = new Usuario();
            usuario.setEmail(textoEmail);
            usuario.setSenha(textoSenha);

            // e utilizao o metodo para validar login
            validarLogin();
        }
    }
//*****************************************  Solicita Login   **************************************

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Valida o login no Auth    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void validarLogin(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //utiliza a instancia criada para get no email e senha e realizar o login pelo auth
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            // caso a task de login seja completada
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    // task completada com sucesso
                    abrirTelaPrincipal();
                // caso ocorram problemas com a task (erros não verificados)
                }else{
                    String excecao;
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email não cadastrado ou excluído definitivamente";

                    }catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Senha incorreta";

                    }catch (Exception e){

                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(loginActivity.this, excecao, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
//**********************************   Valida o login no Auth   ************************************


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Inicia Activity da tela principal    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void abrirTelaPrincipal() {
        // tratativa para evitar que o start activity seja pressionado varias vezes.
        if (teste == 0 ){

            startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
            teste = 1;
            finish();
        }
    }
//****************************   Inicia Activity da tela principal   *******************************

}