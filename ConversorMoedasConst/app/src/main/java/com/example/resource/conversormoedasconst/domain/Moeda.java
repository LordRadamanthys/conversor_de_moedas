package com.example.resource.conversormoedasconst.domain;

public class Moeda {


    private double valor;
    private  String nome;

    public Moeda(double valor, String nome) throws Exception {
        if(nome == null || nome.isEmpty())
            throw new Exception("O nome não pode ser vazio");

        if(valor<=0)
            throw new Exception("O valor não pode ser menor ou igual a zero");

        this.valor = valor;
        this.nome = nome;
    }

    public double getValor() { return valor;}
    public String getNome() {return nome; }

}
