package com.ifes.ericgiobini.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
public class MainActivity extends AppCompatActivity {

    private Button buttonJogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    buttonJogar = findViewById(R.id.button_jogar);

    buttonJogar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        int numSorteio = new Random().nextInt(2);
        Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
        intent.putExtra("resultado", numSorteio);
        startActivity(intent);
        }
    });


    }
}