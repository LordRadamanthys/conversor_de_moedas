package com.example.resource.conversormoedasconst.apiAntiga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkToolkit {

    //carrega o json
    public static String getJSONFromAPI(String url){
        String retorno = "";

        try {
            URL apiEnd = new URL(url);
            int codResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();


            codResposta = conexao.getResponseCode();
            if (codResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            retorno = converteInputStremToString(is);
            is.close();
            conexao.disconnect();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  retorno;
    }

    //converte o inputstream em string
    private static String converteInputStremToString(InputStream is){
        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine()) != null) {
                buffer.append(linha);
            }
        }catch(IOException e ) {
            e.printStackTrace();
        }

        return buffer.toString();

    }
}