package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.helper.DateCustom;
import ifes.eric.organizze.model.Movimentacao;
import ifes.eric.organizze.model.Usuario;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        campoData = findViewById(R.id.textInput_despesa_data);
        campoCategoria = findViewById(R.id.textInput_despesa_categoria);
        campoDescricao = findViewById(R.id.textInput_receita_descricao);
        campoValor = findViewById(R.id.TextEdit_receitas_valor);


        // Preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());
        recuperarDespesaTotal();

    }
    public void salvarDespesa (View view){

        if (validarCamposDespesa()){
            String dataEscolhida = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());


            movimentacao = new Movimentacao();
            movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(dataEscolhida);
            movimentacao.setTipo("D");

            Double despesaAtualizada = valorRecuperado + despesaTotal;
            atualizarDespesa(despesaAtualizada);


            movimentacao.salvar(dataEscolhida);
            Toast.makeText(this, "Movimentação Salva", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    public Boolean validarCamposDespesa() {

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();

        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()){
                return true;
            } else {
                Toast.makeText(this, "Informe a Data", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(this, "Informe o valor", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarDespesaTotal(){
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioREF = firebaseRef.child("usuarios").child(idUsuario);


        usuarioREF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DespesaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void atualizarDespesa(Double despesa){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioREF = firebaseRef.child("usuarios").child(idUsuario);


        usuarioREF.child("despesaTotal").setValue(despesa);

    }




}