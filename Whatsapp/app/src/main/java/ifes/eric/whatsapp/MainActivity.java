package ifes.eric.whatsapp;

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

import ifes.eric.whatsapp.Model.Usuario;
import ifes.eric.whatsapp.Model.Validacao;

public class MainActivity extends AppCompatActivity {
    // variaveis de entrada de texto
    EditText email, senha;

    // variavel para impedir multiplos logins
    int teste = 0;

    // varival para criar um objeto usuario
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tirar o a elevação e o titulo.
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        email = findViewById(R.id.textInput_main_email);
        senha = findViewById(R.id.textInput_main_senha);


    }

//   ------------------------- Verifica se os campos foram digitados  ------------------------------
    public void verificarcampos(View view) {
        String TextoSenha = senha.getText().toString();
        String TextoEmail = email.getText().toString();


        //Utiliza a classe validação para verificar se os dados são consistentes.
        Validacao validacao = new Validacao();


        if (validacao.validar(TextoSenha, TextoEmail, MainActivity.this) == 0) {
            // caso sejam consistentes, cria-se uma instancia usuario.
            usuario  = new Usuario();
            usuario.setEmail(TextoEmail);
            usuario.setIdUsuario(TextoSenha);

            // e utilizao o metodo para validar login
            validarLogin();
        }
    }
//   ************************   Verifica se os campos foram digitados   ****************************

//   ------------------------- Metodo que faz os tramites para logar  ------------------------------
    public void validarLogin(){
        // objeto auth apontando para o firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();


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
                    Toast.makeText(MainActivity.this, excecao, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
//   ************************   Metodo que faz os tramites apra logar   ****************************

//   ---------------------------  Inicia a activity Principal  --------------------------------
    private void abrirTelaPrincipal() {
        // tratativa para evitar que o start activity seja pressionado varias vezes.
        if (teste == 0 ){

            //startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
            teste = 1;

            Toast.makeText(this, "TELA PRINCIPAL ABERTA", Toast.LENGTH_SHORT).show();
        }
    }
//   **************************     Inicia a activity Principal      *******************************


    public void cadastro (View view){
        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
    }
}

//   --------------------------- Metodo que retorna a receitaTotal  --------------------------------

//   **************************  Metodo que retorna a receitaTotal   *******************************