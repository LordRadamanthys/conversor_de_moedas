package com.example.resource.conversormoedasconst.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Build {

    private Retrofit retrofit;

    public Build() {

        this.retrofit = new Retrofit.Builder().baseUrl("https://economia.awesomeapi.com.br/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public IMoedaApi getBuild(){
        return this.retrofit.create(IMoedaApi.class);
    }
}
