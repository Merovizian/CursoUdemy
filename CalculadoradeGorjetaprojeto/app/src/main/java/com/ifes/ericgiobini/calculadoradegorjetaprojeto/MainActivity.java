package com.ifes.ericgiobini.calculadoradegorjetaprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private TextView textoGorjeta;
    private TextView textoResultado;
    private TextView textoPorcentagem;
    private SeekBar seekBarEscala;
    private TextInputEditText imputValor;

    private double porcentagem = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoGorjeta = findViewById(R.id.text_valorGorjeta);
        textoResultado = findViewById(R.id.text_valorTotal);
        textoPorcentagem = findViewById(R.id.text_textoPorcentagem);
        seekBarEscala = findViewById(R.id.seek_bar);
        imputValor = findViewById(R.id.text_imputValor);

        seekBarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textoPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calcular();

            }
        });

    }

    public void calcular(){

        String valorRecuperado = imputValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(getApplicationContext(), "Insira o valor da conta.", Toast.LENGTH_SHORT).show();
        }
        else{
            double valor = Double.parseDouble(valorRecuperado);
            double valorGorjeta = valor*(porcentagem/100);
            textoGorjeta.setText("R$ " + Math.round(valorGorjeta) );
            textoResultado.setText("R$ " + Math.round(valorGorjeta+valor));


        }

    }

}
