package com.ifes.ericgiobini.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText campoNome;
    private TextInputEditText campoEmail;
    private TextView textoResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNome = findViewById(R.id.digiteNome);
        campoEmail = findViewById(R.id.digiteEmail);
        textoResultado = findViewById(R.id.textoResultado);



    }

    public void enviar(View view){


        String Nome = campoNome.getText().toString();
        String Email = campoEmail.getText().toString();


        textoResultado.setText("Seu nome é: "+Nome + "\nSeu email é: " + Email);

    }

    public void limpar(View view){

        campoEmail.setText("");
        campoNome.setText("");
        textoResultado.setText("Resultado");

    }






}
