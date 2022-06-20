package com.ifes.ericgiobini.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private ProgressBar progressBarCircular;
    private int progresso = 0;
    private int progressoTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarCircular = findViewById(R.id.progress_circular);
        progressBarHorizontal = findViewById(R.id.progress_horizontal);

    }

    public void cliqueCarregar(View view){

        this.progresso = this.progresso + 1;
        progressBarCircular.setProgress(this.progresso);

        if (progresso == 3){
            progresso = 0;
            progressoTotal = progressoTotal +2;
        }

        if (progressoTotal == 10){
            progressoTotal = 0;
            Toast.makeText(getApplicationContext(), "Progresso concluido com sucesso!", Toast.LENGTH_LONG).show();

        }
        progressBarHorizontal.setProgress(this.progressoTotal);


    }
}
