package ifes.eric.preferenciasusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText nomeUsuario;
    private Button botaoSalvar;
    private TextView nomeSalvo;
    private final String ARQUIVO_PREFERENCIA = "BancoDados";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoSalvar = findViewById(R.id.button_salvar);
        nomeUsuario = findViewById(R.id.input_nome);
        nomeSalvo = findViewById(R.id.text_usuariosalvo);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                // Ferramenta para salvar preferencias no dispositivo
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (nomeUsuario.getText().toString().equals("")){
                    Toast.makeText(getApplication(), "Digite o Nome!", Toast.LENGTH_LONG).show();}
                else{
                    String nome = nomeUsuario.getText().toString();
                    editor.putString("nome", nome);
                    editor.apply();
                    Toast.makeText(getApplication(), "Usuario " + nomeUsuario.getText() + " Salvo!", Toast.LENGTH_LONG).show();
                    nomeSalvo.setText("Olá, " + nome);

                }
                }
        });
        // Recupera os itens salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (preferences.contains("nome")){

            // Retorna o que há em "nome", se não tiver nada, apresenta a "s1" que no caso é "não tem nada aqui"
            String nome = preferences.getString("nome","não tem nada aqui");
            nomeSalvo.setText("Olá, "+ nome);

        }else{
            nomeSalvo.setText("Olá, Usuário não definido");
        }


    }







}