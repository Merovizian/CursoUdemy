package com.ifes.eric.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonAbrir, buttonFechar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAbrir = findViewById(R.id.button_abrir);
        buttonFechar = findViewById(R.id.button_fechar);
        buttonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonAbrir.setText("ABRIR SNACKBAR");
                Snackbar snackbar = Snackbar.make(
                        view,
                        "Botão pressionado",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("Confirmar", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                buttonAbrir.setText("Botão abrir alterado");
                            }
                        });
                snackbar.setActionTextColor(getResources().getColor(R.color.eric)).show();

                buttonFechar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                        buttonAbrir.setText("ABRIR SNACKBAR");
                    }
                });



            }
        });
    }
}