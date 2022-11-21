package ifes.eric.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import ifes.eric.organizze.R;
import ifes.eric.organizze.model.DateCustom;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        campoData = findViewById(R.id.textInput_despesa_data);
        campoCategoria = findViewById(R.id.textInput_despesa_categoria);
        campoDescricao = findViewById(R.id.textInput_despesa_descricao);
        campoValor = findViewById(R.id.TextEdit_despesas_valor);


        // Preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());


    }
}