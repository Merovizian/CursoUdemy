package com.ericgiobini.projetosorteios6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ativar_botao (View view){

        TextView texto = findViewById(R.id.texto_jogo);

        int numero = new Random().nextInt(11);


        texto.setText( "Voce Sorteou: " + numero);

    }

}

