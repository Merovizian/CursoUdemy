package ifes.eric.listadetarefas.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ifes.eric.listadetarefas.R;
import ifes.eric.listadetarefas.adapter.TarefaAdapter;
import ifes.eric.listadetarefas.databinding.ActivityMainBinding;
import ifes.eric.listadetarefas.model.Tarefa;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();



    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Busca o Recycler pelo id.
        recyclerView = findViewById(R.id.recycler_listaTarefas);







        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Cria uma activity e a vincula ao FAB button
                startActivity(new Intent(getApplicationContext(), AdicionarTarefaActivity.class));

            }
        });
    }

    // METODO PARA O RECYCLERVIEW - monta as tarefas.
    public void carregarListaTarefas (){

        // LISTAR TAREFAS
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Ir ao mercado");
        listaTarefas.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNomeTarefa("Pagar as contas");
        listaTarefas.add(tarefa2);





        // CONFIGURAR ADAPTER
        tarefaAdapter = new TarefaAdapter(listaTarefas);


        // CONFIGURAR RECYCLER
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        //recyclerView.setAdapter(adapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}