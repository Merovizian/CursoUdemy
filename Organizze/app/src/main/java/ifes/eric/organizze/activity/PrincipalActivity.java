package ifes.eric.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import ifes.eric.organizze.R;
import ifes.eric.organizze.config.ConfiguracaoFirebase;
import ifes.eric.organizze.helper.Base64Custom;
import ifes.eric.organizze.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {
    DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
    FirebaseAuth autenticador = ConfiguracaoFirebase.getFirebaseAutenticacao();
    TextView textoValorFinal, textoNomeUsuario;
    private MaterialCalendarView calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Conectando as variaveis com os IDs
        textoNomeUsuario = findViewById(R.id.text_nomeUsuario);
        textoValorFinal = findViewById(R.id.text_valorFinal);

        recuperarValorTotal();


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  MANIPULACAO CALENDAR  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Configurações de layout  -  Pode ser feito como uma função/metodo
        calendario = findViewById(R.id.calendarView_CalendarioNovo);
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

    public void adicionarReceita(View view    ){
        startActivity(new Intent(getApplicationContext(),ReceitasActivity.class));
    }
    public void adicionarDespesa(View view    ){
        startActivity(new Intent(getApplicationContext(),DespesaActivity.class));
    }

    public void Logoff(View view){

        MainActivity main = new MainActivity();
        main.Logoff();
        finish();
    }

    public void recuperarValorTotal(){
        String idUser = Base64Custom.codificarBase64(autenticador.getCurrentUser().getEmail().toString());
        DatabaseReference userRF = database.child("usuarios").child(idUser);

        userRF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue(Usuario.class);
                Double despesaTotal = usuario.getDespesaTotal();
                Double receitaTotal = usuario.getReceitaTotal();
                Double valorFinal = -despesaTotal + receitaTotal;

                textoValorFinal.setText("R$ " + valorFinal.toString());
                textoNomeUsuario.setText(usuario.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}