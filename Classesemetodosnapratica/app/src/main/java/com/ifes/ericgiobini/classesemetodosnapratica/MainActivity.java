package com.ifes.ericgiobini.classesemetodosnapratica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        Casa minhaCasa = new Casa();
        Casa minhaCasa2 = new Casa();

        minhaCasa.cor = "Azul";
        minhaCasa2.cor = "Preto";

        System.out.println(minhaCasa.cor);
        minhaCasa.abrirPorta();
        */

        Funcionario func1 = new Funcionario();


        func1.nome = "Eric";
        func1.salario = 1000;

        double salarioRecuperado = func1.recuperarSalario(200, 10);

        System.out.println("O salário do " + func1.nome + " funcionario é: " + salarioRecuperado);


    }
}
