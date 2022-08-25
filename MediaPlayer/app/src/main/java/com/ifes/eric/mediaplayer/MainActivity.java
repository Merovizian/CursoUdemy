package com.ifes.eric.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private TextView tocando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mediaPlayer = MediaPlayer.create(this, R.raw.teste);
        tocando = findViewById(R.id.text_tocando);
    }


    public void executarSom(View view){

        if (mediaPlayer != null){
            mediaPlayer.start();
            tocando.setText("I don't want be - REPRODUZINDO");
        }
        else
            Toast.makeText(this, "Não EXISTE MUSICA", Toast.LENGTH_SHORT).show();

    }
    public void pausarSom(View view){

        if (mediaPlayer != null){
            mediaPlayer.pause();
            tocando.setText("I don't want be - PAUSADA");
        }
        else
            Toast.makeText(this, "Como veio parar aqui? nem existe musica", Toast.LENGTH_SHORT).show();

    }
    public void pararSom(View view){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            tocando.setText("Sem Música");
        }
        else
            Toast.makeText(this, "Está parando o vento é?", Toast.LENGTH_SHORT).show();

    }
}

