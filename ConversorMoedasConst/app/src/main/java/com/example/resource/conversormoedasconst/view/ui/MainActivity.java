package com.example.resource.conversormoedasconst.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resource.conversormoedasconst.R;
import com.example.resource.conversormoedasconst.domain.Conversor;
import com.example.resource.conversormoedasconst.domain.Moeda;
import com.example.resource.conversormoedasconst.model.moedaModel;
import com.example.resource.conversormoedasconst.presenter.IApiPresenterView;
import com.example.resource.conversormoedasconst.presenter.MoedaApiPrsenter;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IApiPresenterView.view {

    public EditText editValue;
    public TextView textDolar;
    public TextView textEuro;
    public Button buttonCalcular;
    public Moeda dtgDolar;
    public Moeda dtgEuro;
    public boolean conectado;
    public AlertDialog alerta;
    private MoedaApiPrsenter moedaApiPrsenter;
    private Double valorEntrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        moedaApiPrsenter = new MoedaApiPrsenter(this);


        editValue = findViewById(R.id.edit_value);
        textDolar = findViewById(R.id.text_dolar);
        textEuro = findViewById(R.id.text_euro);
        buttonCalcular = findViewById(R.id.button_calcular);

        if (verificaConexao()) {

            moedaApiPrsenter.getMoedaDolar("USD");
            moedaApiPrsenter.getMoedaEuro("EUR");

        } else {
            alerta();
        }

        //verificar se a quantidade de caracteres no campo é igual a zero
        editValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    clearValues();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        this.clearValues();//inicia os campos  text limpos
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorEntrada = !editValue.getText().toString().isEmpty() ? Double.parseDouble(editValue.getText().toString()) : 0;
                buttonConversorOnClick();
            }
        });
    }


    public void buttonConversorOnClick() {
        try {
            if (verificaConexao()) {
                conversorOnline();
            } else {
                conversorOffline();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void pegarMoedaDolar(Object obj) throws Exception {
        List<moedaModel> listamoedaDolar = (List<moedaModel>) obj;
        double moeda = Double.parseDouble(listamoedaDolar.get(0).getValor());
        dtgDolar = new Moeda(moeda,listamoedaDolar.get(0).getNome());

       // Toast.makeText(this, listamoedaDolar.get(0).getNome(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void pegarMoedaEuro(Object obj) throws Exception {
        List<moedaModel> listaMoedaEuro = (List<moedaModel>) obj;
        double moeda = Double.parseDouble(listaMoedaEuro.get(0).getValor());
        dtgEuro = new Moeda(moeda,listaMoedaEuro.get(0).getNome());
        //Toast.makeText(this, listaMoedaEuro.get(0).getNome(), Toast.LENGTH_SHORT).show();
    }



    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void conversorOffline() throws Exception {
        Conversor dolar = new Conversor(valorEntrada,
                new Moeda(3, "dolar fixo"));

        Conversor euro = new Conversor(valorEntrada,
                new Moeda(4, "euro fixo"));

        textDolar.setText("$ " + String.format("%.2f", dolar.Converter()) + " " +
                dolar.getMoeda().getNome());

        textEuro.setText("€ " + String.format("%.2f", euro.Converter()) + " " +
                euro.getMoeda().getNome());
    }


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void conversorOnline() throws Exception {
        Conversor dolar = new Conversor(valorEntrada
                , new Moeda(dtgDolar.getValor(), dtgDolar.getNome()));

        Conversor euro = new Conversor(valorEntrada,
                new Moeda(dtgEuro.getValor(), dtgEuro.getNome()));

        textDolar.setText("$ " + String.format("%.2f", dolar.Converter()) + " " +
                dolar.getMoeda().getNome());

        textEuro.setText("€ " + String.format("%.2f", euro.Converter()) + " " +
                euro.getMoeda().getNome());
    }

    private void clearValues() {
        textEuro.setText("0");
        textDolar.setText("0");
    }

    public boolean verificaConexao() {

        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        conectado = conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo()
                .isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected();
        
        return conectado;
    }



    private void alerta() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sem Conexão");

        //define a mensagem
        builder.setMessage("Seu dispositivo não está conectado a internet, ligue o wi-fi " +
                "ou dados moveis para ter uma conversão precisa");

        builder.setPositiveButton("Ok, vou ligar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true); // Liga o WiFi

            wifi.setWifiEnabled(true);

        Intent i = new Intent(MainActivity.this,MainActivity.class);
        startActivity(i);
        finish();

            }
        });

        //define um botão como negativo
        builder.setNegativeButton("Ok, continuar mesmo assim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


    @Override
    public void onFailure(Object obj) {
        Throwable erro = (Throwable) obj;

        Toast.makeText(this, erro.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("TESTE",erro.toString());
        Log.e("TESTE",erro.getMessage());
    }


}
