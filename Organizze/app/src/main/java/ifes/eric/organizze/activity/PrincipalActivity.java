package ifes.eric.organizze.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ifes.eric.organizze.R;
import ifes.eric.organizze.adapter.AdapterMovimentacao;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Movimentacao;
import ifes.eric.organizze.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrincipalActivity extends AppCompatActivity {

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Banco de dados   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference userRF;
    private final DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference movimentacaoRF;
//   ***********************************   Banco de dados   ************************************


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Autenticador  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Referencia seu usuario do auth
    FirebaseAuth autenticador = ConfiguracaoFirebase.getFirebaseAutenticacao();
//   ***********************************  Autenticador  ************************************


    // Objeto que trata e recebe um evento value event listener
    private ValueEventListener valueEventListenerUsuario;
    private ValueEventListener valueEventListenerMovimentacoes;

    // Objetos/variaveis padrões
    TextView textoValorFinal, textoNomeUsuario;
    private double receitaTotal, despesaTotal, resumoUsuario;

    // Objeto do calendario GIT
    private MaterialCalendarView calendario;
    private String mesAno ;

    // Recycler view
    private RecyclerView recyclerView;
    private AdapterMovimentacao adapterMovimentacao;
    private final List<Movimentacao> movimentacoes = new ArrayList<>();
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Conectando as variaveis com os IDs
        textoNomeUsuario = findViewById(R.id.text_nomeUsuario);
        textoValorFinal = findViewById(R.id.text_valorFinal);
        calendario = findViewById(R.id.calendarView_CalendarioNovo);
        recyclerView = findViewById(R.id.recycler_princiapal_movimentos);




        // Configurações da Toolbar - ActionBar existe por causa do MENU
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle("");



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Configuração Adapter  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Configurar Adapter
        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterMovimentacao);

//   ***********************************  Configuração Adapter  ************************************




//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  MANIPULACAO CALENDAR  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Configurações de layout  -  Pode ser feito como uma função/metodo
        calendario.state().edit()
                .setMinimumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2024,1,1))
                .commit();
        CharSequence[] meses = {"Janeiro", "Fevereiro", "Março", "Abril","Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendario.setTitleMonths(meses);

        // Mostra a movimentação sem usar o listener do calendário - format > formatação de string
        @SuppressLint("DefaultLocale") String mesSelecionado = String.format("%02d",
                (calendario.getCurrentDate().getMonth()+1));
        mesAno = mesSelecionado  + calendario.getCurrentDate().getYear();
        Log.i("DADOS", mesAno);

        // Listener
        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                @SuppressLint("DefaultLocale") String mesSelecionado = String.format("%02d",
                        (calendario.getCurrentDate().getMonth()+1));
                mesAno = mesSelecionado  + calendario.getCurrentDate().getYear();

                movimentacaoRF.removeEventListener(valueEventListenerMovimentacoes);

                recuperarMovimentacoes();
            }
        });
//   ***********************************  MANIPULACAO CALENDAR  ************************************
        swipe();
    }

    @Override
    //Inflater transforma um objeto XML em objeto do tipo view
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    // Menu com a opção de deslogar do sistema
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.menuSair) {

            MainActivity main = new MainActivity();
            main.Logoff();
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Botoes FAB e Menu  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void adicionarReceita(View view    ){
        startActivity(new Intent(getApplicationContext(),ReceitasActivity.class));
    }

    public void adicionarDespesa(View view    ){
        startActivity(new Intent(getApplicationContext(),DespesaActivity.class));
    }
//   ***********************************  Botoes FAB e Menu  ************************************

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Printa na tela o ValorTotal  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void recuperarValorTotal(){
        String idUser = Base64Custom.codificarBase64(Objects.requireNonNull(Objects.requireNonNull(autenticador.
                        getCurrentUser())
                .getEmail()));
        userRF = database.child("usuarios").child(idUser);

        // Faz uma ligação entre o objeto userRF e o firebase
        valueEventListenerUsuario = userRF.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            // snapshot é a instancia que gera essa ligação
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // usando os dados do Snapshot, cria-se um objeto usuario.
                Usuario usuario = snapshot.getValue(Usuario.class);
                assert usuario != null;
                // as variaveis globais receita e despesas são completadas com o que está no snapshot
                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                // metodo para colocar o double em formatação
                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                // a forma da formatação é aplicado ao double resumoUsuario.
                String resultadoFormatado = decimalFormat.format(resumoUsuario);

                if (resumoUsuario < 0){
                    textoValorFinal.setTextColor((getResources().getColor(android.R.color.holo_red_dark)));
                    textoValorFinal.setText("R$ " + resultadoFormatado);
                }else {
                    textoValorFinal.setTextColor((getResources().getColor(R.color.R)));
                    textoValorFinal.setText("R$ " + resultadoFormatado);
                }

                textoNomeUsuario.setText("Olá, " + usuario.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//   *******************************  Printa na tela o ValorTotal  *********************************

//   ------------------------------------  Atualizar os dados  -------------------------------------
    public void atualizarSaldo(){
        String idUser = Base64Custom.codificarBase64(Objects.requireNonNull(Objects.requireNonNull(autenticador
                .getCurrentUser()).getEmail()));
        userRF = firebase.child("usuarios").child(idUser);
        // verifica se a movimentação é uma receita ou uma despesa
        if ( movimentacao.getTipo().equals("R")){
            receitaTotal = receitaTotal - movimentacao.getValor();
            userRF.child("receitaTotal").setValue(receitaTotal);

        }else{
            receitaTotal = receitaTotal + movimentacao.getValor();
            userRF.child("receitaTotal").setValue(receitaTotal);

        }
    }
//   ************************************  Atualizar os dados  *************************************

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Metodo Exclui movimentos  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @SuppressLint("NotifyDataSetChanged")
    public void excluirMovimentacao(RecyclerView.ViewHolder viewHolder){
        // instancia um alerta dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Excluir Movimentação da conta");
        alertDialog.setMessage("Têm certeza que deseja excluir a movimentação?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // busca no recyclerview a posição que esta o viewHolder e a adciona no objeto movimentacao
                int itemPosition = viewHolder.getAdapterPosition();
                movimentacao = movimentacoes.get(itemPosition);
                // Retorna o codigo em base 64 do email utilizado pelo usuario atual
                String idUser = Base64Custom.codificarBase64(Objects.requireNonNull(Objects.requireNonNull(autenticador
                        .getCurrentUser()).getEmail()));

                // direciona o ponteiro para o email dentro de movimentações
                movimentacaoRF = firebase.child("movimentacao")
                        .child(idUser)
                        .child(mesAno);
                movimentacaoRF.child(movimentacao.getKeyID()).removeValue();
                // Chama o metodo atualizar Saldo apos excluir movimentção
                atualizarSaldo();

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PrincipalActivity.this, "Ação cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.create()
                .show();

        // atualiza o adapter
        adapterMovimentacao.notifyDataSetChanged();


    }
//   *********************************  Metodo Exclui movimentos  **********************************

//   ~~~~~~~~~~~~~~~~~~~~~~~  Metodo Dar Oções ao mover para os lados   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void swipe(){
        // instancia-se um objeto itemTouch
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override

            public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                        @NonNull RecyclerView.ViewHolder viewHolder) {
                // não sei para que serve
                int draflags = ItemTouchHelper.ACTION_STATE_IDLE;
                // modo de movimento para o inicio e para o final
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(draflags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            // ao jogar para um dos lados, da o objeto a ser usado e a direção que foi jogado
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                excluirMovimentacao(viewHolder);
            }
        };

        // atranha o swipe ao recyclerview
        new ItemTouchHelper( itemTouch ).attachToRecyclerView(recyclerView);
    }
//   ***********************  Metodo Dar Oções ao mover para os lados   ****************************

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Metodo Recuperar Movimentações  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void recuperarMovimentacoes(){
        // Retorna o codigo em base 64 do email utilizado pelo usuario atual
        String idUser = Base64Custom.codificarBase64(Objects.requireNonNull(Objects.
                requireNonNull(autenticador.getCurrentUser()).getEmail()));

        // direciona o ponteiro para o email dentro de movimentações
        movimentacaoRF = firebase.child("movimentacao").child(idUser).child(mesAno);
        valueEventListenerMovimentacoes = movimentacaoRF.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                movimentacoes.clear();

                Log.i("TESTE", "ENTROU");

                for (DataSnapshot dados: snapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);

                    // coloca no objeto movimentação, da classe Movimentacao, a chave de cada uma
                    // das movimentações.
                    assert movimentacao != null;
                    movimentacao.setKeyID( dados.getKey());
                    movimentacoes.add(movimentacao);


                }

               adapterMovimentacao.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//   ******************************  Metodo Recuperar Movimentações  *******************************

    @Override
    protected void onStart() {
        super.onStart();
        recuperarValorTotal();
        recuperarMovimentacoes();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userRF.removeEventListener(valueEventListenerUsuario);
        movimentacaoRF.removeEventListener(valueEventListenerMovimentacoes);
    }
}