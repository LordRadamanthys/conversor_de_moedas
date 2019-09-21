package com.example.resource.conversormoedasconst.apiAntiga;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataGetter extends AsyncTask<String,Void,String> {
    public Context c;
    private double valor;
    private String moeda;

    public double getValor() {
        return valor;
    }


    public DataGetter() {

    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String result = NetworkToolkit.getJSONFromAPI(url);
        return result;
    }

    public String getMoeda() {
        return moeda;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray arrayJson = new JSONArray(s);

            JSONObject dataResponse = arrayJson.getJSONObject(0);

            String firstName = dataResponse.getString("bid");
            String lastName = dataResponse.getString("name");


            //Toast.makeText(c,firstName,Toast.LENGTH_SHORT).show();
            this.valor = Double.parseDouble(firstName.substring(0,4));
            this.moeda = lastName;

        }catch (JSONException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
            //this.txtNome.setText("Erro Nulo");
        }
    }
}