package ifes.eric.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import ifes.eric.organizze.R;
import ifes.eric.organizze.helper.DateCustom;
import ifes.eric.organizze.model.Movimentacao;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

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
    public void salvarDespesa (View view){

        String dataEscolhida = campoData.getText().toString();

        movimentacao = new Movimentacao();
        movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
        movimentacao.setCategoria(campoCategoria.getText().toString());
        movimentacao.setDescricao(campoDescricao.getText().toString());
        movimentacao.setData(dataEscolhida);
        movimentacao.setTipo("d");
        movimentacao.salvar(dataEscolhida);
        Toast.makeText(this, "Movimentação Salva", Toast.LENGTH_SHORT).show();
        finish();

    }

}