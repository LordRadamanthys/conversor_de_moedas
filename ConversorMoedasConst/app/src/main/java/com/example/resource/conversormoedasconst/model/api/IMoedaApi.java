package com.example.resource.conversormoedasconst.model.api;

import com.example.resource.conversormoedasconst.model.moedaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IMoedaApi {



    @GET("{id}/")
    Call<List<moedaModel>> getMoeda(@Path("id") String moeda);


}
