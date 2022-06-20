package com.ifes.ericgiobini.toggleeswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton togglePass;
    private Switch switchPass;
    private CheckBox checkPass;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        togglePass = findViewById(R.id.toggleSenha);
        switchPass = findViewById(R.id.switchSenha);
        textResultado = findViewById(R.id.textResultado);
        checkPass = findViewById(R.id.checkPass);

        adicionarListener();


    }


    public void adicionarListener() {

        checkPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean variavelDeRetorno) {

                if (variavelDeRetorno)  {
                    textResultado.setText("Check Ligado");
                } else textResultado.setText("Check Desligado");


        }
    });


        switchPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)  {
                    textResultado.setText("switchPass Ligado");
                } else textResultado.setText("switchPass Desligado");
            }
        });

}

    public void cliqueEnviar(View view) {
        if (checkPass.isChecked()) {
            textResultado.setText("Check Ligado");
        } else textResultado.setText("Check Desligado");


    }

}
