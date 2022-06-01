package com.ifes.ericgiobini.classesemetodosnapratica;



public class Conta {

    int numeroConta;
    private double saldo = 100;

    void depositar(double valorDeposito){
        this.saldo = this.saldo + valorDeposito;

    }
    void sacar(double valorSaque){
        this.saldo = this.saldo - valorSaque;

    }

    double recuperarSaldo(){
        return this.saldo;

    }

}
