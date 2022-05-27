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

        Animal pegeout = new Animal();
        pegeout.Nome = "Pegeout";
        pegeout.Cor = "Amarelo/branco";
        pegeout.Tamanho = 35;
        pegeout.Peso = 320;
        String locomocao = "devagar";

        System.out.println("O animal escolhido é o " + pegeout.Nome + " ele pesa " + pegeout.Peso + " gramas e mede " + pegeout.Tamanho +
                " cm e suas cores são " + pegeout.Cor + ". Ele costuma se locomover " + pegeout.correr(locomocao));

    }
}
