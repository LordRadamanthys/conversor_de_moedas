package com.example.resource.conversormoedasconst.domain;

public class Conversor {
    private double valor;
    private Moeda moeda;

    public Conversor(double valor, Moeda moeda) throws Exception {
        if(valor<=0)
            throw new Exception("O valor não pode ser menor que zero ou vazio");

        if(moeda==null)
            throw new Exception("O moeda não pode ser vazio");

        this.valor = valor;
        this.moeda = moeda;
    }

    public double getValor() { return valor;  }

    public Moeda getMoeda() { return moeda; }

    public double Converter() {
        try {
            return valor / moeda.getValor();
        } catch (Exception e) {
            return  -1;
        }
    }
}
