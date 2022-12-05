package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import ifes.eric.organizze.R;
import ifes.eric.organizze.adapter.AdapterMovimentacao;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Movimentacao;
import ifes.eric.organizze.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Banco de dados   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference userRF;
    private DatabaseReference movimentacaoRF = ConfiguracaoFirebase.getFirebaseDatabase();
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
    private List<Movimentacao> movimentacoes = new ArrayList<>();

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
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");



//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Configuração Adapter  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Configurar Adapter
        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);

//   ***********************************  Configuração Adapter  ************************************




//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  MANIPULACAO CALENDAR  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Configurações de layout  -  Pode ser feito como uma função/metodo
        calendario.state().edit()
                .setMinimumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2024,1,1))
                .commit();
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril","Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendario.setTitleMonths(meses);

        // Mostra a movimentação sem usar o listener do calendário
        String mesSelecionado = String.format("%02d", (calendario.getCurrentDate().getMonth()+1));
        mesAno = mesSelecionado  +  String.valueOf(calendario.getCurrentDate().getYear());


        Toast.makeText(this, mesAno, Toast.LENGTH_LONG).show();
        // Listener
        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mesSelecionado = String.format("%02d", (calendario.getCurrentDate().getMonth()+1));
                mesAno = mesSelecionado  +  String.valueOf(calendario.getCurrentDate().getYear());

                movimentacaoRF.removeEventListener(valueEventListenerMovimentacoes);

                recuperarMovimentacoes();
                Log.i("DADOS", mesAno);
            }
        });


//   ***********************************  MANIPULACAO CALENDAR  ************************************

    }

    @Override
    //Inflar transforma um objeto XML em objeto do tipo view
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuSair:
                sair();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Botoes FAB e Menu  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void sair(){
        MainActivity main = new MainActivity();
        main.Logoff();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void adicionarReceita(View view    ){
        startActivity(new Intent(getApplicationContext(),ReceitasActivity.class));
    }

    public void adicionarDespesa(View view    ){
        startActivity(new Intent(getApplicationContext(),DespesaActivity.class));
    }

//   ***********************************  Botoes FAB e Menu  ************************************

    public void recuperarValorTotal(){
        String idUser = Base64Custom.codificarBase64(autenticador.getCurrentUser().getEmail().toString());
        userRF = database.child("usuarios").child(idUser);

        valueEventListenerUsuario = userRF.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(resumoUsuario);

                if (resumoUsuario < 0){
                    textoValorFinal.setTextColor((getResources().getColor(android.R.color.holo_red_dark)));
                    textoValorFinal.setText("R$ " + resultadoFormatado);
                }else {
                    textoValorFinal.setTextColor((getResources().getColor(android.R.color.holo_green_light)));
                    textoValorFinal.setText("R$ " + resultadoFormatado);
                }

                textoNomeUsuario.setText("Olá, " + usuario.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Metodo Recuperar Movimentações  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void recuperarMovimentacoes(){
        // Retorna o codigo em base 64 do email utilizado pelo usuario atual
        String idUser = Base64Custom.codificarBase64(autenticador.getCurrentUser().getEmail().toString());
        // direciona o ponteiro para o email dentro de movimentações
        movimentacaoRF = movimentacaoRF.child("movimentacao").child(idUser).child(mesAno);

        valueEventListenerMovimentacoes = movimentacaoRF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                movimentacoes.clear();

                Log.i("DADOS", "ENTROU");

                for (DataSnapshot dados: snapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);

                    Log.i("DADOS", "ENTROU4");

                    movimentacoes.add(movimentacao);
                    Log.i("DADOS", "ENTROU2");


                }
                Log.i("DADOS", "ENTROU3");

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