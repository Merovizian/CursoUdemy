package ifes.eric.listadetarefas.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ifes.eric.listadetarefas.R;
import ifes.eric.listadetarefas.adapter.TarefaAdapter;
import ifes.eric.listadetarefas.databinding.ActivityMainBinding;
import ifes.eric.listadetarefas.helper.DbHelper;
import ifes.eric.listadetarefas.helper.RecyclerItemClickListener;
import ifes.eric.listadetarefas.helper.TarefaDAO;
import ifes.eric.listadetarefas.model.Tarefa;
import kotlin.jvm.internal.Ref;

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
    private Tarefa tarefaSelecionada;


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

        // Instanciar a classe


        // Adicionar eventos de cliques
        recyclerView.addOnItemTouchListener(

                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                // Recuperar tarefa para edicao
                                Tarefa tarefaselecionada = listaTarefas.get(position);


                                // Enviar tarefa para tela adicionar tarefa
                                Intent intent = new Intent (MainActivity.this,
                                        AdicionarTarefaActivity.class);
                                intent.putExtra("tagtarefaSelecionada", tarefaselecionada);
                                startActivity(intent);


                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                
                                // Recuperar a tarefa para deletar
                                tarefaSelecionada = listaTarefas.get(position);

                                AlertDialog.Builder dialog =
                                        new AlertDialog.Builder(MainActivity.this);

                                // Configura título e mensagem
                                dialog.setTitle("Confirmar Exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: "+
                                        tarefaSelecionada.getNomeTarefa() + "?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {



                                    }
                                });
                                dialog.setNegativeButton("Não", null);
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView,
                                                    View view, int i, long l) {

                            }
                        }
                )

        );


            binding.fab.setOnClickListener(view -> {

                // Cria uma activity e a vincula ao FAB button
                startActivity(new Intent(getApplicationContext(), AdicionarTarefaActivity.class));

            });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // CArregar a lista
        carregarListaTarefas();
    }

    // METODO PARA O RECYCLERVIEW - monta as tarefas.
    public void carregarListaTarefas() {

        // LISTAR TAREFAS
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listar();


        // CONFIGURAR ADAPTER
        tarefaAdapter = new TarefaAdapter(listaTarefas);


        // CONFIGURAR RECYCLER
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);


    }
}