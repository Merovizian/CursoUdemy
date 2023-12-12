package hobby.eric.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import hobby.eric.whatsapp.Helper.ConfiguracaoFirebase;
import hobby.eric.whatsapp.Helper.Usuario;
import hobby.eric.whatsapp.Helper.Validador;
import hobby.eric.whatsapp.R;

public class LoginActivity extends AppCompatActivity {
    public EditText inputEmail, inputSenha;
    private String textEmail, textSenha;
    public FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutentificacao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.v("ERICTAG", "LOGIN ACTIVITY OnCreate");

        inputEmail = findViewById(R.id.textInput_login_email);
        inputSenha = findViewById(R.id.textInput_login_senha);



        Log.v("ERICTAG", "LOGIN / ONCREATE - firebaseAuth.getCurrentUser(): " + firebaseAuth.getCurrentUser().getEmail().toString());

    }

    public void abrirTelaCadastro(View view){
        Intent abrirActivityCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(abrirActivityCadastro);

    }

    public void abrirBotaoLogin(View view){

        // Anexa os dados lidos nos inputs nas respectivas variveis STRINGS
        textEmail = inputEmail.getText().toString();
        textSenha = inputSenha.getText().toString();

        // Criando uma instancia especifica de usuario.
        Usuario usuario = new Usuario();

        // Validador de dados inseridos para evitar que o usuario falte com algum dado.
        Validador validador = new Validador();

        if (validador.validar(textSenha, textEmail, this) == 0){
            usuario.setUserEmail(textEmail);
            usuario.setUserSenha(textSenha);

            // Aplicando essa instancia especifica na função realizar login
            realizarLogin(usuario);
        }


    }

    public void realizarLogin(Usuario usuario){


        firebaseAuth.signInWithEmailAndPassword(
                usuario.getUserEmail(),
                usuario.getUserSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "USUARIO: " + firebaseAuth.getCurrentUser().getEmail()
                            + "\nLogado com sucesso!", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }else{
                    String erro = "";
                    try {
                        throw Objects.requireNonNull(task.getException());

                    } catch (FirebaseAuthInvalidUserException e){
                        erro = "Usuário Inválido";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Email mal formatado";
                    } catch (Exception e ){
                        erro = "Erro:  " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, erro, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal (){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "REINICIE O PROGRAMA", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}