package com.example.resource.conversormoedasconst.model;

import com.google.gson.annotations.SerializedName;

public class moedaModel {

    @SerializedName("bid")
    private String valor;

    @SerializedName("name")
    private  String nome;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
