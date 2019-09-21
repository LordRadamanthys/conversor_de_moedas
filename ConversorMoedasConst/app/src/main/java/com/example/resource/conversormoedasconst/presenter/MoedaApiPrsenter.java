package com.example.resource.conversormoedasconst.presenter;

import com.example.resource.conversormoedasconst.model.api.Build;
import com.example.resource.conversormoedasconst.model.api.IMoedaApi;
import com.example.resource.conversormoedasconst.model.moedaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoedaApiPrsenter implements IApiPresenterView.presenter{
    private IMoedaApi moedaApi;
    private IApiPresenterView.view view;
    private Throwable erro;

    public MoedaApiPrsenter(IApiPresenterView.view view) {
        this.moedaApi = new Build().getBuild();
        this.view = view;
    }

    @Override
    public void getMoedaDolar(String moeda) {
        moedaApi.getMoeda(moeda).enqueue(new Callback<List<moedaModel>>() {
            @Override
            public void onResponse(Call<List<moedaModel>> call, Response<List<moedaModel>> response) {

                if(!response.isSuccessful()){
                    erro = new Exception("Erro nao sucesso");
                    view.onFailure(erro);
                }

                if(response.body() == null){
                    erro = new Exception("Erro vazio");
                    view.onFailure(erro);
                }

                try {
                    view.pegarMoedaDolar(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<moedaModel>> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    @Override
    public void getMoedaEuro(String moeda) {
        moedaApi.getMoeda(moeda).enqueue(new Callback<List<moedaModel>>() {
            @Override
            public void onResponse(Call<List<moedaModel>> call, Response<List<moedaModel>> response) {
                if(!response.isSuccessful()){
                    erro = new Exception("Erro nao sucesso");
                    view.onFailure(erro);
                }

                if(response.body() == null){
                    erro = new Exception("Erro vazio");
                    view.onFailure(erro);
                }

                try {
                    view.pegarMoedaEuro(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<moedaModel>> call, Throwable t) {

            }
        });
    }
}
