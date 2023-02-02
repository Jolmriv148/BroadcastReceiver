package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    IntentFilter filtroCargador;
    IntentFilter filtroWifi;
    TextView tv;
    Receptor1 receptor1;
    Receptor2 receptor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);

        filtroCargador=new IntentFilter();
        filtroCargador.addAction(Intent.ACTION_POWER_CONNECTED);
        filtroCargador.addAction(Intent.ACTION_POWER_DISCONNECTED);

        filtroWifi=new IntentFilter();
        filtroWifi.addAction("android.net.wifi.WIFI_STATE_CHANGED");

        receptor1=new Receptor1();
        receptor2=new Receptor2();




    }

    public class Receptor1 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String accion=intent.getAction();
            tv.setText(accion);
        }
    }

    public class Receptor2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String accion=intent.getAction();
            Toast.makeText(context,accion,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receptor1,filtroCargador);
        registerReceiver(receptor2,filtroWifi);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerReceiver(receptor1,filtroCargador);
        registerReceiver(receptor2,filtroWifi);
    }
}