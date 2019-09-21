package com.example.resource.conversormoedasconst.presenter;

public interface IApiPresenterView {

    interface view{
        void onFailure(Object obj);

        void pegarMoedaDolar(Object obj) throws Exception;

        void pegarMoedaEuro(Object obj) throws Exception;
    }

    interface presenter{
        void getMoedaDolar(String moeda);

        void getMoedaEuro(String moeda);
    }
}
