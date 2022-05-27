package com.ifes.ericgiobini.frasesdodia;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int numero_anterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    public void gerarNovaFrase (View view) {


        TextView texto = findViewById(R.id.frase_inicial);
        TextView valor = findViewById(R.id.numero);


        int numero = new Random().nextInt(4);

        while (numero_anterior == numero){
            numero = new Random().nextInt(4);

        }

        switch (numero){
            case 3:
                texto.setText("Um pequeno pensamento positivo pela manhã pode mudar todo o seu dia.");
                break;
            case 2:
                texto.setText("Nunca deixe ninguém te dizer que não pode fazer alguma coisa. Se você tem um sonho tem que correr atrás dele.");
                break;
            case 1:
                texto.setText("As pessoas costumam dizer que a motivação não dura sempre. Bem, nem o efeito do banho, por isso recomenda-se diariamente.");
                break;
            case 0:
                texto.setText("O que quer que seja que estejamos esperando - paz de espírito, alegria, graça, consciência interior ou simples abundância - isso certamente virá para nós, mas somente quando estivermos prontos para receber com um coração aberto e agradecido.");
                break;




        }

        /*
        String [] frases = {
                "Um pequeno pensamento positivo pela manhã pode mudar todo o seu dia."
                "Nunca deixe ninguém te dizer que não pode fazer alguma coisa. Se você tem um sonho tem que correr atrás dele.",
                "As pessoas costumam dizer que a motivação não dura sempre. Bem, nem o efeito do banho, por isso recomenda-se diariamente.",
                "O que quer que seja que estejamos esperando - paz de espírito, alegria, graça, consciência interior ou simples abundância - isso certamente virá para nós, mas somente quando estivermos prontos para receber com um coração aberto e agradecido."
        };

        texto.setText(frases[numero]);

         */
        valor.setText("O numero é: " + numero);
        numero_anterior = numero;
    }

}
