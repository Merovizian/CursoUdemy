package ifes.eric.minhasanotaes;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ifes.eric.minhasanotaes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // A classe sera utilizada atraves dessa iniciação.
    private AnotacaoPreferencias preferencias;

    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editAnotacao = findViewById(R.id.edit_anotacao);

        preferencias = new AnotacaoPreferencias(getApplicationContext());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editAnotacao.getText().toString().equals("")){
                    Snackbar.make(view, "Preencha a anotação!", Snackbar.LENGTH_LONG).show();
                }else{

                    preferencias.salvarAnotacao(editAnotacao.getText().toString());
                    Snackbar.make(view, "Anotação Salva com Sucesso!", Snackbar.LENGTH_LONG).show();


                }

            }
        });

        // Recuperar anotação
        String anotacao = preferencias.recuperarAnotacao();

        if (!anotacao.equals("")){
            editAnotacao.setText(anotacao);
        }

    }
}