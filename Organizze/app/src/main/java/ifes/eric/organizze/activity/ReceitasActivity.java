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


public class ReceitasActivity extends AppCompatActivity {
    private Movimentacao movimentacao;
    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
    private Double receitaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);
        campoData = findViewById(R.id.textInput_receita_data);
        campoCategoria = findViewById(R.id.textInput_receita_categoria);
        campoDescricao = findViewById(R.id.textInput_receita_descricao);
        campoValor = findViewById(R.id.TextEdit_receitas_valor);


        // Preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());

        // Metodo para recuperar a receita do firebase
        recuperarReceitaTotal();

    }

    /* Metodo que gera toda a movimentação necessaria para salvar a receita */
    public void salvarReceita (View view){


        // Instancia uma nova movimentação da classe movimentação
        movimentacao = new Movimentacao();

        // Valor escolhido pelo usuario, no campo.
        movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));

        // Campo categoria escolhido pelo usuario, no campo.
        movimentacao.setCategoria(campoCategoria.getText().toString());

        // Campo Descrição escolhido pelo usuario, no campo.
        movimentacao.setDescricao(campoDescricao.getText().toString());

        // Data escolhida pelo usuario, no campo.
        movimentacao.setData(campoData.getText().toString());

        // R que simboliza RECEITA
        movimentacao.setTipo("R");

        // utiliza o metodo salvar dentro da classe movimentação
        movimentacao.salvar(campoData.getText().toString());

        // Usa o valor da receita recuperado, mais o valor digitado em campo para gerar o valor total
        Double ValorAtualizado = receitaTotal + Double.parseDouble(campoValor.getText().toString());

        // Usa o metodo para atualizar no firebase
        atualizarReceita(ValorAtualizado);

        Toast.makeText(this, "Movimentação Salva", Toast.LENGTH_SHORT).show();
        finish();

    }



    /* Metodo que recupera do firebase o valor da receitaTotal*/
    public void recuperarReceitaTotal(){
        // Cria um cod64 do email de usuario que está logado.
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail().toString());

        // Instancia um usuarioREF e o aponta para o child Usuario/cod64 do email
        DatabaseReference usuarioREF = database.child("usuarios").child(idUsuario);

        // metodos para receber o que está no firebase de onde a  instancia UsuarioREF está apontando.
        usuarioREF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // instancia um usuario para receber os valores que estão no firebase.
                Usuario usuario = snapshot.getValue(Usuario.class);
                receitaTotal =  usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    /* Metodo que atualiza no firebase o valor da receita total */
    public void atualizarReceita (Double despesa){
        // retorna o valor em cod64 do email de que esta logado.
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);

        // Instancia um usuarioREF e o aponta para o child Usuario/cod64 do email
        DatabaseReference usuarioREF = database.child("usuarios").child(idUsuario);

        // apontado para Usuario/cod64 e adiciona nó com o valor do atribulo despesa recebido
        usuarioREF.child("receitaTotal").setValue(despesa);

    }


}