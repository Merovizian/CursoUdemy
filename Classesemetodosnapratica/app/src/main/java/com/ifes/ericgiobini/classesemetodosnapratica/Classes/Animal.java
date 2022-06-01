package com.ifes.ericgiobini.classesemetodosnapratica.Classes;

public class Animal {

    protected int tamanho;
    protected String cor;
    protected double peso;



    void setCor(String cor){

        this.cor = cor;


    }

    int getTamanho() {
        return tamanho;
    }

    int getPeso() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    String getCor(){

        return this.cor;

    }

    void dormir(){

        System.out.println("Dormir como um ");

    }

    void correr(){

        System.out.println("Correr como um ");

    }



}
