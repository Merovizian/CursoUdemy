package com.ifes.ericgiobini.componentesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private EditText campoNome;
    private TextInputEditText campoEmail;
    private TextView textoResultado;

    private CheckBox checkVerde, checkVermelho, checkBranco;


    private RadioButton sexoMasculino, sexoFeminino;
    private RadioGroup opcaoSexo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNome       = findViewById(R.id.digiteNome);
        campoEmail      = findViewById(R.id.digiteEmail);
        textoResultado  = findViewById(R.id.textoResultado);

        checkBranco     = findViewById(R.id.checkBranco);
        checkVerde      = findViewById(R.id.checkVerde);
        checkVermelho   = findViewById(R.id.checkVermelho);

        sexoFeminino    = findViewById(R.id.radioFeminino);
        sexoMasculino   = findViewById(R.id.radioMasculino);
        opcaoSexo       = findViewById(R.id.radioGroupSexo);

        radiobutton();

    }


    public void checkbox(){

        String texto = "";


        if (checkVerde.isChecked()){
            String corSelecionada = checkVerde.getText().toString();
            texto = texto + corSelecionada + "\n";
            //texto = texto + "Verde selecionado - \n";

        }

        if (checkBranco.isChecked()){
            String corSelecionada = checkBranco.getText().toString();
            texto = texto + corSelecionada + "\n";

           // texto = texto + "Branco selecionado - \n";

        }

        if (checkVermelho.isChecked()){
            String corSelecionada = checkVermelho.getText().toString();
            texto = texto + corSelecionada + "\n";


           // texto = texto + "Vermelho selecionado - \n";

        }

        if (texto.length() > 0)
            texto = texto.substring(0, texto.length()-1);
        textoResultado.setText(texto);

    }




    public void radiobutton (){

        opcaoSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioMasculino){
                    textoResultado.setText("Masculino");
                } else if (i == R.id.radioFeminino){
                    textoResultado.setText("Feminino");
                }

            }
        });






        /*


        if (sexoMasculino.isChecked()) {
            textoResultado.setText("Masculino");

        }else if (sexoFeminino.isChecked())
            textoResultado.setText("Feminino");


         */
    }

    public void enviar(View view){


        //radiobutton();
        //checkbox();


        /*
        String Nome = campoNome.getText().toString();
        String Email = campoEmail.getText().toString();


        textoResultado.setText("Seu nome é: "+Nome + "\nSeu email é: " + Email);
        */

    }

    public void limpar(View view){

        campoEmail.setText("");
        campoNome.setText("");
        textoResultado.setText("Resultado");

    }










}
