package ifes.eric.whatsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.Model.Validacao;
import ifes.eric.whatsapp.R;

public class LoginActivity extends AppCompatActivity {
    // variaveis de entrada de texto
    EditText email, senha;

    // variavel para impedir multiplos logins
    int teste = 0;

    // varival para criar um objeto usuario
    Usuario usuario;

    // Declarar uma instancia de FirebaseAuth
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // tirar o a elevação e o titulo.
        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);
       // Objects.requireNonNull(getSupportActionBar()).setTitle("");

        email = findViewById(R.id.textInput_main_email);
        senha = findViewById(R.id.textInput_main_senha);
        TextView tentativa = findViewById(R.id.text_TESTE);


        try {
            auth = FirebaseAuth.getInstance();
            tentativa.setText(auth.getCurrentUser().getEmail().toString());
        } catch (NullPointerException e){
            tentativa.setText("Usuario Deslogado");
        }



    }

    //   ------------------------- Verifica se os campos foram digitados  ------------------------------
    public void verificarcampos(View view) {
        String TextoSenha = senha.getText().toString();
        String TextoEmail = email.getText().toString();


        //Utiliza a classe validação para verificar se os dados são consistentes.
        Validacao validacao = new Validacao();


        if (validacao.validar(TextoSenha, TextoEmail, LoginActivity.this) == 0) {
            // caso sejam consistentes, cria-se uma instancia usuario.
            usuario  = new Usuario();
            usuario.setEmail(TextoEmail);
            usuario.setSenha(TextoSenha);

            // e utilizao o metodo para validar login
            validarLogin();
        }
    }

//   ************************   Verifica se os campos foram digitados   ****************************

    //   ------------------------- Metodo que faz os tramites para logar  ------------------------------
    public void validarLogin(){
        // objeto auth apontando para o firebase
        auth = FirebaseAuth.getInstance();



        //utiliza a instancia criada para get no email e senha e realizar o login pelo auth
        auth.signInWithEmailAndPassword(usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            // caso a task de login seja completada
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    // task completada com sucesso
                    abrirTelaPrincipal();
                    // caso ocorram problemas com a task (erros não verificados)
                }else{
                    TextView tentativa = findViewById(R.id.text_TESTE);

                    tentativa.setText(task.getException().toString());


                    String excecao;
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Senha Incorreta";

                    }catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Email não cadastrado ou excluído definitivamente";

                    }catch (Exception e){

                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
//   ************************   Metodo que faz os tramites apra logar   ****************************

    //   ---------------------------  Inicia a activity Principal  --------------------------------
    private void abrirTelaPrincipal() {
        // tratativa para evitar que o start activity seja pressionado varias vezes.
        if (teste == 0 ){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            teste = 1;

            Toast.makeText(this, "TELA PRINCIPAL ABERTA", Toast.LENGTH_SHORT).show();
        }
    }
//   **************************     Inicia a activity Principal      *******************************


    public void cadastro (View view){
        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
    }


    public void logoff (View view){
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        Toast.makeText(this, "Usuario Saiu", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtivo = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtivo != null){
            abrirTelaPrincipal();
        }


    }
}
