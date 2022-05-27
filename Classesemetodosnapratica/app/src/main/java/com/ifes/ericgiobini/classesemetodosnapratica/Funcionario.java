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
