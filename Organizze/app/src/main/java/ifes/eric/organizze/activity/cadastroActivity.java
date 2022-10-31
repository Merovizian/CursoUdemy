package ifes.eric.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ifes.eric.organizze.R;

public class cadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    //private Button botaoCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        campoEmail = findViewById(R.id.editText_cadastro_email);
        campoNome = findViewById(R.id.editText_cadastro_nome);
        campoSenha = findViewById(R.id.editText_Cadastro_senha);
   }

    public void efetuarCadastro(View view){
        String textoNome = campoNome.getText().toString();
        String textoSenha = campoSenha.getText().toString();
        String textoEmail = campoEmail.getText().toString();

        if (validacao(textoNome,textoSenha,textoEmail) == 0){
            cadastrarUsuario();
        }

    }


    public int validacao(String nome, String senha, String email){

        if (!nome.isEmpty() ){
            if (!email.isEmpty() ){
                if (!senha.isEmpty() ){

                }else{
                    Toast.makeText(this, "Preencha o senha", Toast.LENGTH_SHORT).show();
                    return 3;

                }
            }else{
                Toast.makeText(this, "Preencha a email", Toast.LENGTH_SHORT).show();
                return 2;

            }
        }else{
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
            return 1;

        }
        return 0;
    }
    public void cadastrarUsuario(){

    }
}

