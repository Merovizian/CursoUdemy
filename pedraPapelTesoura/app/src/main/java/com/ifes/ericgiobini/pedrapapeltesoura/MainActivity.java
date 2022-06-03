package com.ifes.ericgiobini.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void selecionadoPai(View view){

        this.paiSelecionado("pai");

    }
    public void selecionadoMae(View view) {
        this.paiSelecionado("mae");
    }

    public void selecionadoBruno(View view){
        this.opcaoSelecionada("bruno");

    }
    public void selecionadoEric(View view){
        this.opcaoSelecionada("eric");

    }
    public void selecionadoCarolina(View view){
        this.opcaoSelecionada("carolina");

    }



    public void paiSelecionado(String stringpai){


    }









    public void opcaoSelecionada(String stringSelecionada){






        ImageView imagemResultadoComputador = findViewById(R.id.resultadoComputador);
        ImageView imagemResultadoUsuario = findViewById(R.id.resultadoUsuario);
        TextView textoResultado = findViewById(R.id.resultadoFinal);



        int escolhaComputador = new Random().nextInt(3);
        String [] opcoes = {"bruno", "eric", "carolina"};
        String opcaoComputador = opcoes[escolhaComputador];

        switch (opcaoComputador) {
            case "bruno":
                imagemResultadoComputador.setImageResource(R.drawable.bruno);
                break;
            case "eric":
                imagemResultadoComputador.setImageResource(R.drawable.eric);
                break;
            case "carolina":
                imagemResultadoComputador.setImageResource(R.drawable.carolina);
                break;
        }
        switch (stringSelecionada) {
            case "bruno":
                imagemResultadoUsuario.setImageResource(R.drawable.bruno);
                break;
            case "eric":
                imagemResultadoUsuario.setImageResource(R.drawable.eric);
                break;
            case "carolina":
                imagemResultadoUsuario.setImageResource(R.drawable.carolina);
                break;
        }


            if (opcaoComputador == "bruno" && stringSelecionada == "eric"){
                textoResultado.setText("CELULAR GANHOU!! O filho do meio conseguiria no máximo um empate, se nascesse mulher");
            }else if(opcaoComputador == "bruno" && stringSelecionada == "carolina"){
                textoResultado.setText("EMPATE!! A filha preferida tem vantagem sobre todos, menos com o primogênito");
            }else if(opcaoComputador == "carolina" && stringSelecionada == "eric"){
                textoResultado.setText("CELULAR GANHOU!! O filho do meio já nasceu perdendo");
            }else if (opcaoComputador == "carolina" && stringSelecionada == "bruno"){
                textoResultado.setText("EMPATE!! o primogênito só ganharia se a filha predileta fosse a do meio.");
            }else if (opcaoComputador == "eric" && stringSelecionada == "carolina"){
                textoResultado.setText("JOGADOR GANHOU!! A filha tem seu proprio quarto, tem como competir com isso?");
            }else if (opcaoComputador == "eric" && stringSelecionada == "bruno"){
                textoResultado.setText("JOGADOR GANHOU!! O primogênito dominou sozinho por 4 anos. Coitado do filho do meio");
            } else if (opcaoComputador == "eric" && stringSelecionada == "eric"){
                textoResultado.setText("OS DOIS PERDERAM!! Não há vencedores quando se é o Filho do meio, só derrota.");
            } else {
                textoResultado.setText("VITORIA DOS DOIS!!");
            }

        }
}





