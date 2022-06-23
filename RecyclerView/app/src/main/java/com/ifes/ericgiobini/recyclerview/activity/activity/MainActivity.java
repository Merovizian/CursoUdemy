package com.ifes.ericgiobini.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.RelativeLayout;

import com.ifes.ericgiobini.recyclerview.R;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_View);

        // configurando o Adapter





        // Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // Configura para tamanhos fixos.
        recyclerView.setHasFixedSize(true);
        // Faz a conexao
        //recyclerView.setAdapter();
    }
}
