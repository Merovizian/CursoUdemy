package com.ifes.ericgiobini.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirToast (View view){

        //criando uma variavel imagem e instanciando ela em um Context
        ImageView imagem = new ImageView(getApplicationContext());

        //escolhendo uma imagem nos recursos do prprio studio e colocando ela na variavel imagem
        imagem.setImageResource(android.R.drawable.star_big_off);


        //criando uma variavel textview e instanciando ela em um Context
        TextView textView = new TextView(getApplicationContext());

        //configurando cores a variavel textview
        textView.setBackgroundResource(R.color.colorAccent);

        // Atribuindo valor à variavel textview
        textView.setText("Olá Toast");

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        // Pode-se colocar muitas coisas como toast: como imagem
        toast.setView(imagem);
        // ou um texto
        toast.setView( textView );
        toast.show();

        // Metodo já prédefinido
        /*
        Toast.makeText(
                getApplicationContext(),
                    "Ação realizada com sucesso!!",
                    Toast.LENGTH_SHORT
        ).show();
        */
    }


}
