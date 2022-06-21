package com.ifes.ericgiobini.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listLocais;
    private String[] itens = {"Angra dos Reis","Caldas Novas","Campos do Jordão", "Costa do Sauípe", "Ilhéus","Aruba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listLocais = findViewById(R.id.list_Locais);

        // Cria adaptador para a lista.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens 
        );

        // Adiciona adaptador para a lista
        listLocais.setAdapter( adaptador );

        listLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String valorSelecionado = (String) listLocais.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), valorSelecionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
