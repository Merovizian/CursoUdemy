package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import ifes.eric.organizze.R;
import ifes.eric.organizze.adapter.AdapterMovimentacao;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Banco de dados   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference userRF;

//   ***********************************   Banco de dados   ************************************


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Autenticador  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Referencia seu usuario do auth
    FirebaseAuth autenticador = ConfiguracaoFirebase.getFirebaseAutenticacao();
//   ***********************************  Autenticador  ************************************


    // Objeto que trata e recebe um evento value event listener
    private ValueEventListener valueEventListenerUsuario;

    // Objetos/variaveis padrões
    TextView textoValorFinal, textoNomeUsuario;
    private double receitaTotal, despesaTotal, resumoUsuario;

    // Objeto do calendario GIT
    private MaterialCalendarView calendario;

    // Recycler view
    private RecyclerView recyclerView;

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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter();


//   ***********************************  Configuração Adapter  ************************************




//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  MANIPULACAO CALENDAR  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Configurações de layout  -  Pode ser feito como uma função/metodo
        calendario.state().edit()
                .setMinimumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2024,1,1))
                .commit();
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril","Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendario.setTitleMonths(meses);

        // Listener
        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                int mes = date.getMonth() + 1;
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


    public void recuperarValorTotal(){
        String idUser = Base64Custom.codificarBase64(autenticador.getCurrentUser().getEmail().toString());
        userRF = database.child("usuarios").child(idUser);

        valueEventListenerUsuario = userRF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(resumoUsuario);

                textoValorFinal.setText("R$ " + resultadoFormatado);
                textoNomeUsuario.setText("Olá, " + usuario.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarValorTotal();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userRF.removeEventListener(valueEventListenerUsuario);
    }
}