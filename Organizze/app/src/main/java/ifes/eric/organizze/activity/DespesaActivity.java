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

import java.util.Objects;

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.helper.DateCustom;
import ifes.eric.organizze.model.Movimentacao;
import ifes.eric.organizze.model.Usuario;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private final DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private final FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
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

//   ---------------------------- Salva a movimentacao de Despesa  ---------------------------------
    public void salvarDespesa (View view){

        // teste de validação utilizando o metodo validarCamposDespesas
        if (validarCamposDespesa()){

            // Instancia uma nova movimentação da classe movimentação
            Movimentacao movimentacao = new Movimentacao();

            // Valor escolhido pelo usuario, no campo.
            movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));

            // Campo categoria escolhido pelo usuario, no campo.
            movimentacao.setCategoria(Objects.requireNonNull(campoCategoria.getText()).toString());

            // Campo Descrição escolhido pelo usuario, no campo.
            movimentacao.setDescricao(Objects.requireNonNull(campoDescricao.getText()).toString());

            // Data escolhida pelo usuario, no campo.
            movimentacao.setData(Objects.requireNonNull(campoData.getText()).toString());

            // D que simboliza Despesa
            movimentacao.setTipo("D");

            // Usa o valor da receita recuperado, mais o valor digitado em campo para gerar o valor total
            Double despesaAtualizada = movimentacao.getValor() + despesaTotal;

            // Usa o metodo para atualizar no firebase
            atualizarDespesa(despesaAtualizada);


            movimentacao.salvar(campoData.getText().toString());
            Toast.makeText(this, "Movimentação Salva", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
//   *****************************  Salva a movimentacao de Despesa  *******************************

//   ----------------------------  Validação dos campos digitados   ---------------------------------
    public Boolean validarCamposDespesa() {

        String textoValor = campoValor.getText().toString();
        String textoData = Objects.requireNonNull(campoData.getText()).toString();

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
//   *****************************  Validação dos campos digitados   *******************************

//   --------------------------- Metodo que retorna a despesaTotal  --------------------------------
    public void recuperarDespesaTotal(){
        // Cria um cod64 do email de usuario que está logado.
        String idUsuario = Base64Custom.codificarBase64(Objects.requireNonNull
                (Objects.requireNonNull(autenticacao.getCurrentUser()).getEmail()));

        // Instancia um usuarioREF e o aponta para o child Usuario/cod64 do email
        DatabaseReference usuarioREF = firebaseRef.child("usuarios").child(idUsuario);

        // metodos para receber o que está no firebase de onde a  instancia UsuarioREF está apontando.
        usuarioREF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // instancia um usuario para receber os valores que estão no firebase.
                Usuario usuario = snapshot.getValue(Usuario.class);
                assert usuario != null;
                despesaTotal = usuario.getDespesaTotal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DespesaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
//   **************************  Metodo que retorna a despesaTotal   *******************************

//   ----------------------- Atualiza o valor descontado no Firebase  ------------------------------
    public void atualizarDespesa(Double despesa){
        // retorna o valor em cod64 do email de que esta logado.
        String emailUsuario = Objects.requireNonNull(autenticacao.getCurrentUser()).getEmail();
        assert emailUsuario != null;
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);

        // Instancia um usuarioREF e o aponta para o child Usuario/cod64 do email
        DatabaseReference usuarioREF = firebaseRef.child("usuarios").child(idUsuario);

        // apontado para Usuario/cod64 e adiciona nó com o valor do atribulo despesa recebido
        usuarioREF.child("despesaTotal").setValue(despesa);

    }
//   *********************  Atualiza o valor descontado no Firebase   ******************************




}