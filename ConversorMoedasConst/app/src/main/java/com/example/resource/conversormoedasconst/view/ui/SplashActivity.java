package com.example.resource.conversormoedasconst.view.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.resource.conversormoedasconst.R;

public class SplashActivity extends AppCompatActivity {
    public boolean conectado;
    public AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                mostrarLogin();
            }
        }, 2500);
    }

    public boolean verificaConexao() {

        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        conectado = conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo()
                .isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected();

        return conectado;
    }

    private void mostrarLogin() {
        Intent intent = new Intent(SplashActivity.this,
                MainActivity.class);
        if(verificaConexao()) {
            startActivity(intent);
            finish();
        }else{
            alerta();

        }
    }


    private void alerta() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sem Conexão");

        //define a mensagem
        builder.setMessage("Seu dispositivo não está conectado a internet, ligue o wi-fi " +
                "ou dados moveis para ter uma conversão precisa");

        //define um botão como positivo
        builder.setPositiveButton("Ok, vou ligar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true); // Liga o WiFi

                wifi.setWifiEnabled(true);


                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 4000);


                //finish();
            }
        });

        //define um botão como negativo
        builder.setNegativeButton("Ok, continuar mesmo assim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
