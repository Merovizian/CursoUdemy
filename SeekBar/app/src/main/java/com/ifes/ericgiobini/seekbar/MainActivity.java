package com.ifes.ericgiobini.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarEscala;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBarEscala = findViewById(R.id.seekBar);
        textResultado = findViewById(R.id.text_resultado);

        seekBarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // Primeiro metodo é quando o usuário clica e segura a barra
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textResultado.setText("Progresso: " + i + "/" + seekBar.getMax());

            }

            // Segundo metodo é quando o usuário clica em um ponto na barra mas não solta
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //textResultado.setText("USUARIO CLICOU");


            }

            // Terceiro metodo é quando o usuário solta o clique do metodo acima
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textResultado.setText("USUARIO SOLTOU");


            }
        });

    }



    public void cliqueBotao (View view){

        textResultado.setText("Escolhido: " + seekBarEscala.getProgress());


    }
}
