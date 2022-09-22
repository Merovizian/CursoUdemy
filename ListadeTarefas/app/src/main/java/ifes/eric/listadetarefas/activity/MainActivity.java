package ifes.eric.listadetarefas.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ifes.eric.listadetarefas.R;
import ifes.eric.listadetarefas.adapter.TarefaAdapter;
import ifes.eric.listadetarefas.databinding.ActivityMainBinding;
import ifes.eric.listadetarefas.helper.RecyclerItemClickListener;
import ifes.eric.listadetarefas.model.Tarefa;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // CONFIGURAR O RECYCLER VIEW
        recyclerView = findViewById(R.id.recycler_listaTarefas);

        // Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("Clique","onItemClick" + "| item: " + position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("Clique", "onLongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )


        );


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Objeto que facilita a comunicação com outros componentes.
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);

            }
        });
    }


    public void carregarListaTarefas(){

        // Listar Tarefas
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Ir ao mercado");
        listaTarefas.add (tarefa1);
        Tarefa tarefa12 = new Tarefa();
        tarefa12.setNomeTarefa("Ir ao cinema");
        listaTarefas.add (tarefa12);

        // Configurar Adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);

        // Configurar Recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);


    }



    @Override
    protected void onStart() {
        carregarListaTarefas();
        Toast.makeText(this,"VOCE ENTROU NO onStart", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this,"VOCE ENTROU NO onRestart", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this,"VOCE ENTROU NO onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this,"VOCE ENTROU NO onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}