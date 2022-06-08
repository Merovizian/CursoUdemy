package com.ifes.ericgiobini.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText valorGasolina, valorAlcool;
    private TextView textoAposResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valorAlcool        = findViewById(R.id.valorAlcool);
        valorGasolina      = findViewById(R.id.valorGasolina);
        textoAposResultado = findViewById(R.id.textoResultado);


    }

    public void ativarBotao (View view){



        String precoAlcool   = valorAlcool.getText().toString();
        String precoGasolina = valorGasolina.getText().toString();

        String validado = validarCampos(precoAlcool, precoGasolina);



        if (validado == "Preenchido") {
            Double Gasolina = Double.parseDouble(precoGasolina);
            Double Alcool = Double.parseDouble(precoAlcool);


            if (Alcool / Gasolina >= 0.7) {
                textoAposResultado.setText("É Melhor Abastecer com Gasolina");
            } else
                textoAposResultado.setText("É Melhor Abastecer com Álcool");
        }else
            textoAposResultado.setText("Por valor, insira os dados no campo: \n" + validado);
    }


    public String validarCampos(String pAlcool, String pGasolina){
        String camposValidados = "Preenchido";
        int auxiliar = 0;

        if (pAlcool == null || pAlcool.equals("")){
            camposValidados = "Alcool";
            auxiliar = auxiliar + 1;
        }
        if (pGasolina == null || pGasolina.equals("")){
            camposValidados = "Gasolina";
            auxiliar = auxiliar + 1;
        }

        if (auxiliar > 1)
            camposValidados = "Gasolina e Alcool";

        return camposValidados;

    }




}
