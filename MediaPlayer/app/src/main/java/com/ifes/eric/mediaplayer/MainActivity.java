package com.ifes.eric.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private TextView tocando;
    private SeekBar seekvolume;
    private AudioManager audioManager;
    private TextView volume;
    private int volumeAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tocando = findViewById(R.id.text_tocando);
        inicializarSeekBar();
        volume = findViewById(R.id.text_volume);
        volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume.setText(String.valueOf(volumeAtual));

    }

    private void inicializarSeekBar(){

        seekvolume = findViewById(R.id.seek_volume);

        //configura o audio mananger
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // recupera os volumes
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // configura os valores maximos para o Seekbar
        seekvolume.setMax(volumeMaximo);

        // configura o progresso atual do seekBar
        seekvolume.setProgress(volumeAtual);

        // volume da musica baseada na seekbar
        seekvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_SHOW_UI);
                volumeAtual = i;
                volume.setText(String.valueOf(volumeAtual));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.teste);



    }

    public void executarSom(View view){
        if (!mediaPlayer.isPlaying())
            mediaPlayer = MediaPlayer.create(this, R.raw.teste);
        if (mediaPlayer != null){
            mediaPlayer.start();
            tocando.setText("I don't want be - REPRODUZINDO");
            Toast.makeText(this, "OMG ASHUESAHEU", Toast.LENGTH_SHORT).show();
        }
    }
    public void pausarSom(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            tocando.setText("I don't want be - PAUSADA");
        }
     }
    public void pararSom(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            tocando.setText("Sem Música");
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if ( mediaPlayer.isPlaying() && mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
 }


