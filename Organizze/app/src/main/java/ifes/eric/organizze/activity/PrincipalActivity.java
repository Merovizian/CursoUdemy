package ifes.eric.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ifes.eric.organizze.R;

public class PrincipalActivity extends AppCompatActivity {
    FloatingActionButton FAB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        // FAB = findViewById(R.id.floatingActionButton);



    }
    public void adicionarReceita(View view    ){
        startActivity(new Intent(getApplicationContext(),ReceitasActivity.class));
    }
    public void adicionarDespesa(View view    ){
        startActivity(new Intent(getApplicationContext(),DespesaActivity.class));
    }

}