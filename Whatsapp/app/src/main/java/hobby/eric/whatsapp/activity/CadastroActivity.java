package hobby.eric.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import java.util.Objects;

import hobby.eric.whatsapp.Helper.ConfiguracaoFirebase;
import hobby.eric.whatsapp.Helper.Usuario;
import hobby.eric.whatsapp.Helper.Validador;
import hobby.eric.whatsapp.R;

public class CadastroActivity extends AppCompatActivity {
    public EditText inputNome, inputEmail, inputSenha;
    private String textNome, textEmail, textSenha;
    private Usuario usuario = new Usuario();
    public FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Log.d("ERICTAG", "Cadastro Usuario ONCREATE");


        // Anexar os objetos do layout nas respectivas variáveis.
        inputNome = findViewById(R.id.textInput_cadastro_nome);
        inputEmail = findViewById(R.id.textInput_cadastro_email);
        inputSenha = findViewById(R.id.textInput_cadastro_senha);

        // Retorna a instãncia aberta do FirebaseAuth
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutentificacao();




    }
    // Evento de clique no botão cadastrar
    public void validarDados (View v){

        // Anexa os dados lidos nos inputs nas respectivas variveis STRINGS
        textNome = inputNome.getText().toString();
        textEmail = inputEmail.getText().toString();
        textEmail = textEmail.replaceAll("\\s+","");
        textSenha = inputSenha.getText().toString();

        // Validador de dados inseridos para evitar que o usuario falte com algum dado.
        Validador validacao = new Validador();

        if (validacao.validar(textNome,textEmail,textSenha, CadastroActivity.this) ==0 ){
            usuario.setUserNome(textNome);
            usuario.setUserEmail(textEmail);
            usuario.setUserSenha(textSenha);

            realizarCadastro();
        }


    }

    public void realizarCadastro(){
        Toast.makeText(this, "Cadastrando usuario, por favor espere....", Toast.LENGTH_SHORT).show();

        // Cria um usuario FirebaseAuth
        firebaseAuth.createUserWithEmailAndPassword(textEmail,textSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(textNome).build();
                    assert  user != null;

                    Toast.makeText(CadastroActivity.this, "Usuario " + usuario.getUserNome() + "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    String erro = "";
                    try {
                        throw Objects.requireNonNull(task.getException());

                    } catch (FirebaseAuthUserCollisionException e ){
                        erro = "Email já cadastrado";
                    } catch (FirebaseAuthWeakPasswordException e){
                        erro = "Senha Fraca";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Email mal formatado";
                    } catch (Exception e ){
                        erro = "Erro:  " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, erro, Toast.LENGTH_SHORT).show();
                }

            }
        });



    }














    @Override
    protected void onStart() {
        super.onStart();

    }
}