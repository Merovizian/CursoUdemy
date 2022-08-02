package com.ifes.ericgiobini.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {
    int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Criando as variáveis
        Button buttonVoltar = findViewById(R.id.button_voltar);
        ImageView caraoucoroa = findViewById(R.id.image_resultado);
        // Retornando valores da intent MainActivity
        Bundle dados = getIntent().getExtras();
        // Aplicando o valor retornado na variavel
        resultado = dados.getInt("resultado");
        // Logica do cara e coroa
        if (resultado == 1){
            Toast.makeText(this, "COROA", Toast.LENGTH_SHORT).show();
            caraoucoroa.setImageResource(R.drawable.moeda_cara);
        }else{
            Toast.makeText(this, "CARA", Toast.LENGTH_SHORT).show();
            caraoucoroa.setImageResource(R.drawable.moeda_coroa);
        }
         // Evento de click
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Criando uma Intent que mira o Main activity
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                // iniciando a Intent
                startActivity(intent1);
            }
        });











    }
}