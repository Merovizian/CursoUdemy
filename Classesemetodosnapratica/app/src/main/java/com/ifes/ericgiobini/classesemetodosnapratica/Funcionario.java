package com.ifes.ericgiobini.classesemetodosnapratica;

class Funcionario {
    // propriedades
    String nome;
    double salario;

    // métodos
    double recuperarSalario ( double bonus, float imposto){
        this.salario = (this.salario+bonus)*((100-imposto)/100);
        return this.salario;

    }

}


class Animal{
    String Cor, Nome;
    double Tamanho, Peso;


    String correr (String intensidade){
        switch (intensidade){
            case "devagar":
                return "O animal esta andando";
            case "normal":
                return "O animal esta caminhando";
            case "correndo":
                return "O animal esta correndo";
            default:
                return "Animal está parado";
        }

    }

}
