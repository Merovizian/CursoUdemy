package com.ifes.ericgiobini.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void abrirDialog(View view){
        // Instanciar o AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // Configurar titulo e mensagem
        dialog.setTitle("Título da Dialog");
        dialog.setMessage("Mensagem da Dialog");

        // Configura acoes para sim e nao
        dialog.setNegativeButton("Aceito", new DialogInterface.OnClickListener() {
            // Cria uma instancia que faz a leitura do que foi clicado e retorna uma ação.
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(), "Contrato Aceito com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setPositiveButton("Discordo", new DialogInterface.OnClickListener() {
            // Cria uma instancia que faz a leitura do que foi clicado e retorna uma ação.
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(), "Contrado recusado", Toast.LENGTH_SHORT).show();
            }
        });

        // Criar e exibir AlertDialog
        dialog.create();
        dialog.show();









    }
}
