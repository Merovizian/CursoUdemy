package com.ifes.ericgiobini.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private String escolhaPaiMae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void selecionadoPai(View view) {
        escolhaPaiMae = "pai";
        ImageView imagemResultadoPaiMae = findViewById(R.id.resultadoEscolhaPaiMae);
        switch (escolhaPaiMae) {
            case "mae":
                imagemResultadoPaiMae.setImageResource(R.drawable.mae);
                break;
            case "pai":
                imagemResultadoPaiMae.setImageResource(R.drawable.pai);
                break;
        }

    }

    public void selecionadoMae(View view) {
        escolhaPaiMae = "mae";
        ImageView imagemResultadoPaiMae = findViewById(R.id.resultadoEscolhaPaiMae);
        switch (escolhaPaiMae) {
            case "mae":
                imagemResultadoPaiMae.setImageResource(R.drawable.mae);
                break;
            case "pai":
                imagemResultadoPaiMae.setImageResource(R.drawable.pai);
                break;
        }

    }

    public void selecionadoBruno(View view) {
        this.opcaoSelecionada("PrimogĂȘnito");

    }

    public void selecionadoEric(View view) {
        this.opcaoSelecionada("FilhodoMeio");

    }

    public void selecionadoCarolina(View view) {
        this.opcaoSelecionada("Preferida");

    }


    public void opcaoSelecionada(String stringSelecionada) {


        ImageView imagemResultadoComputador = findViewById(R.id.resultadoComputador);
        ImageView imagemResultadoUsuario = findViewById(R.id.resultadoUsuario);
        TextView textoResultado = findViewById(R.id.resultadoFinal);


        TextView nomeEscolhaUsuario = findViewById(R.id.escolhaUsuario);
        nomeEscolhaUsuario.setText("Usuario Escolheu:");
        TextView nomeEscolhaCelular = findViewById(R.id.escolhaCelular);
        nomeEscolhaCelular.setText("Celular Escolheu:");


        int escolhaComputador = new Random().nextInt(3);


        String[] opcoes = {"PrimogĂȘnito", "FilhodoMeio", "Preferida"};
        String opcaoComputador = opcoes[escolhaComputador];

        switch (opcaoComputador) {
            case "PrimogĂȘnito":
                imagemResultadoComputador.setImageResource(R.drawable.bruno);
                break;
            case "FilhodoMeio":
                imagemResultadoComputador.setImageResource(R.drawable.eric);
                break;
            case "Preferida":
                imagemResultadoComputador.setImageResource(R.drawable.carolina);
                break;
        }
        switch (stringSelecionada) {
            case "PrimogĂȘnito":
                imagemResultadoUsuario.setImageResource(R.drawable.bruno);
                break;
            case "FilhodoMeio":
                imagemResultadoUsuario.setImageResource(R.drawable.eric);
                break;
            case "Preferida":
                imagemResultadoUsuario.setImageResource(R.drawable.carolina);
                break;
        }

        TextView escolhaUsuario = findViewById(R.id.nomeEscolhaUsuario);
        escolhaUsuario.setText(stringSelecionada);
        TextView escolhaCelular = findViewById(R.id.nomeEscolhaCelular);
        escolhaCelular.setText(opcaoComputador);


        if (escolhaPaiMae == "mae") {
            if (opcaoComputador == "PrimogĂȘnito" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("CELULAR GANHOU!! O filho do meio sĂł ganha se nascesse mulher");
            } else if (opcaoComputador == "PrimogĂȘnito" && stringSelecionada == "Preferida") {
                textoResultado.setText("JOGADOR GANHOU O primeiro filho sĂł ganharia se nascesse mulher");
            } else if (opcaoComputador == "Preferida" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("CELULAR GANHOU!! ASUHEUAHSEUS O filho do meio sĂł existe pq nasceu Homem");
            } else if (opcaoComputador == "Preferida" && stringSelecionada == "PrimogĂȘnito") {
                textoResultado.setText("CELULAR GANHOU!! O primeiro filho foi preferido por 9 anos");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "Preferida") {
                textoResultado.setText("JOGADOR GANHOU!! A filha tem seu proprio quarto, tem como competir com isso?");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "PrimogĂȘnito") {
                textoResultado.setText("JOGADOR GANHOU!! O PrimogĂȘnito escravizou Eric por anos e anos ");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("OS DOIS PERDERAM!! NĂŁo hĂĄ vencedores quando se Ă© o Filho do meio, sĂł derrota.");
            } else {
                textoResultado.setText("VITORIA DOS DOIS!!");
            }
        }
        if (escolhaPaiMae == "pai") {
            if (opcaoComputador == "PrimogĂȘnito" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("CELULAR GANHOU!! O filho do meio conseguiria no mĂĄximo um empate, se nascesse mulher");
            } else if (opcaoComputador == "PrimogĂȘnito" && stringSelecionada == "Preferida") {
                textoResultado.setText("EMPATE!! A filha preferida tem vantagem sobre todos, menos com o primogĂȘnito");
            } else if (opcaoComputador == "Preferida" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("CELULAR GANHOU!! O filho do meio jĂĄ nasceu perdendo");
            } else if (opcaoComputador == "Preferida" && stringSelecionada == "PrimogĂȘnito") {
                textoResultado.setText("EMPATE!! o primogĂȘnito sĂł ganharia se a filha predileta fosse a do meio.");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "Preferida") {
                textoResultado.setText("JOGADOR GANHOU!! A filha tem seu proprio quarto, tem como competir com isso?");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "PrimogĂȘnito") {
                textoResultado.setText("JOGADOR GANHOU!! O primogĂȘnito dominou sozinho por 4 anos. Coitado do filho do meio");
            } else if (opcaoComputador == "FilhodoMeio" && stringSelecionada == "FilhodoMeio") {
                textoResultado.setText("OS DOIS PERDERAM!! NĂŁo hĂĄ vencedores quando se Ă© o Filho do meio, sĂł derrota.");
            } else {
                textoResultado.setText("VITORIA DOS DOIS!!");
            }
        }
    }
}





