package com.ifes.eric.fragments.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ifes.eric.fragments.R;
import com.ifes.eric.fragments.fragment.ContatosFragment;
import com.ifes.eric.fragments.fragment.ConversasFragment;

public class MainActivity extends AppCompatActivity {

    private Button buttonConversa, buttonContato;
    private ConversasFragment conversasFragment;
    private ContatosFragment contatosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContato = findViewById(R.id.button_contatos);
        buttonConversa = findViewById(R.id.button_conversas);

        // Remover a sombra da ActionBar
        getSupportActionBar().setElevation(0);

        // Instanciar o fragmento
        conversasFragment = new ConversasFragment();

        //Configurar objeto para o Fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_conteudo, conversasFragment);
        transaction.commit();






        buttonConversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conversasFragment = new ConversasFragment();

                //Configurar objeto para o Fragmento
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, conversasFragment);
                transaction.commit();


            }
        });


        buttonContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contatosFragment = new ContatosFragment();


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, contatosFragment);
                transaction.commit();
            }
        });


    }
}