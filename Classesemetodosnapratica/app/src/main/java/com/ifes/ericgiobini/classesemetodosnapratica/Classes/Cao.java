package com.ifes.ericgiobini.classesemetodosnapratica.Classes;

class Cao extends Animal{

    int intensidadeLatido, intensidadeAgressivo;


    void latir(){

        System.out.println("Latir como um cachorro");

    }

    void correr() {
        super.correr();
        System.out.println("CAO");

    }

}
