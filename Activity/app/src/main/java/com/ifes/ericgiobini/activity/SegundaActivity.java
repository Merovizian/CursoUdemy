package com.ifes.ericgiobini.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    private TextView textNome, textIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textNome = findViewById(R.id.text_nome);
        textIdade = findViewById(R.id.text_idade);

        //Recuperar dados enviados
        Bundle dados = getIntent().getExtras();
        //String nome = dados.getString("nome");
        //String idade = dados.getString("idade");

        Usuario usuario = (Usuario) dados.getSerializable("objeto");


        textNome.setText(usuario.getNome());
        textIdade.setText(usuario.getEmail());

    }
}