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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviar(View view){
        EditText campoNome = findViewById(R.id.digiteNome);
        TextInputEditText campoEmail = findViewById(R.id.digiteEmail);
        TextView textoResultado = findViewById(R.id.textoResultado);

        String Nome = campoNome.getText().toString();
        String Email = campoEmail.getText().toString();

        textoResultado.setText("Seu nome é: "+Nome + "\nSeu email é: " + Email);



    }
}
