package com.example.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.activity.RecyclerItemClickListener;
import com.example.recyclerview.activity.adapter.Adapter;
import com.example.recyclerview.activity.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        this.criarFilmes();

        Adapter adapter = new Adapter(listaFilmes);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = listaFilmes.get(position);

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item " + filme.getTituloFilme() + " Pressionado",
                                        Toast.LENGTH_SHORT
                                ).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = listaFilmes.get(position);

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Configurações para " + filme.getTituloFilme(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )



        );



    }

    public void criarFilmes(){

        Filme filme = new Filme("Titulo", "genero", "2007");
        this.listaFilmes.add( filme );

        filme = new Filme("Homem Aranha", "Acao-Ficcao", "2017");
        this.listaFilmes.add( filme );

        filme = new Filme("Mulher Maravilha", "Fantasia", "2018");
        this.listaFilmes.add( filme );

        filme = new Filme("Liga da Justiça", "Ficção", "2016");
        this.listaFilmes.add( filme );

        filme = new Filme("Capitão América", "Ação-Aventura", "2013");
        this.listaFilmes.add( filme );

        filme = new Filme("It a coisa", "Terror", "2015");
        this.listaFilmes.add( filme );

        filme = new Filme("Pica Pau", "Comédia-Animação", "2000");
        this.listaFilmes.add( filme );

        filme = new Filme("A Mumia", "Terror", "2002");
        this.listaFilmes.add( filme );

        filme = new Filme("A Bela e a Fera", "Romance", "2004");
        this.listaFilmes.add( filme );

        filme = new Filme("Meu malvado favorito 3", "Comédia", "2011");
        this.listaFilmes.add( filme );

        filme = new Filme("Carros 3", "Animação", "2004");
        this.listaFilmes.add( filme );

        filme = new Filme("Homem Aranha", "Acao-Ficcao", "2017");
        this.listaFilmes.add( filme );

        filme = new Filme("Mulher Maravilha", "Fantasia", "2018");
        this.listaFilmes.add( filme );

        filme = new Filme("Liga da Justiça", "Ficção", "2016");
        this.listaFilmes.add( filme );

        filme = new Filme("Capitão América", "Ação-Aventura", "2013");
        this.listaFilmes.add( filme );

        filme = new Filme("It a coisa", "Terror", "2015");
        this.listaFilmes.add( filme );

        filme = new Filme("Pica Pau", "Comédia-Animação", "2000");
        this.listaFilmes.add( filme );

        filme = new Filme("A Mumia", "Terror", "2002");
        this.listaFilmes.add( filme );

        filme = new Filme("A Bela e a Fera", "Romance", "2004");
        this.listaFilmes.add( filme );

        filme = new Filme("Meu malvado favorito 3", "Comédia", "2011");
        this.listaFilmes.add( filme );

        filme = new Filme("Carros 3", "Animação", "2004");
        this.listaFilmes.add( filme );

    }


}