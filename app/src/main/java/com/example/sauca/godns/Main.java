package com.example.sauca.godns;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button bt_DNS,bt_Refresh, bt_Sair;
    TextView tv_Ssid,tv_Dns1,tv_Dns2;

    ConnectivityManager cm;
    NetworkInfo networkInfo;
    WifiManager wifiManager;
    WifiInfo connectionInfo;
    DhcpInfo connectionDhcp;
    String strIPAddess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //Toast.makeText(this,getNetworkInfo().toString(),Toast.LENGTH_SHORT).show();

        bt_Refresh = findViewById(R.id.BT_Refresh);
        bt_DNS = findViewById(R.id.BT_Dns);
        bt_Sair = findViewById(R.id.BT_Sair);

        tv_Ssid = findViewById(R.id.TV_Ssid);
        tv_Dns1 = findViewById(R.id.TV_Dns1);
        tv_Dns2 = findViewById(R.id.TV_Dns2);

        if(getNetworkInfo().isConnected())
            connect();
        else
            Toast.makeText(this,R.string.noconect,Toast.LENGTH_LONG).show();

        bt_DNS.setOnClickListener(this);
        bt_Sair.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==findViewById(R.id.BT_Refresh)){
            if(getNetworkInfo().isConnected())
                connect();
            else {
                Toast.makeText(this, R.string.noconect, Toast.LENGTH_LONG).show();
                tv_Ssid.setText(getText(R.string.ssid));
                tv_Dns1.setText(getText(R.string.dns1));
                tv_Dns2.setText(getText(R.string.dns2));
                tv_Dns1.setTextColor(getResources().getColor(R.color.white));
                tv_Dns2.setTextColor(getResources().getColor(R.color.white));
            }
        }else if(view==findViewById(R.id.BT_Dns)){
            ChangeDns();
        }else if(view == findViewById(R.id.BT_Sair)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }
    }

    public NetworkInfo getNetworkInfo() {
        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (cm != null) {
            networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }

        return networkInfo;
    }

    public void connect(){

        if (networkInfo.isConnected()) {
            wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            if (wifiManager != null) {
                connectionInfo = wifiManager.getConnectionInfo();
                connectionDhcp = wifiManager.getDhcpInfo();
            }
            if (connectionInfo != null && connectionDhcp!= null ) {
                tv_Ssid.setText(connectionInfo.getSSID());
                strIPAddess = ((connectionDhcp.dns1 ) & 0xFF) + "." + ((connectionDhcp.dns1 >> 8) & 0xFF) + "."
                        + ((connectionDhcp.dns1 >> 16) & 0xFF) + "." + ((connectionDhcp.dns1 >> 24) & 0xFF);
                tv_Dns1.setText(strIPAddess);
                strIPAddess = ((connectionDhcp.dns2 ) & 0xFF) + "." + ((connectionDhcp.dns2 >> 8) & 0xFF) + "."
                        + ((connectionDhcp.dns2 >> 16) & 0xFF) + "." + ((connectionDhcp.dns2 >> 24) & 0xFF);
                tv_Dns2.setText(strIPAddess);
            }
        }
    }

    public void ChangeDns(){
        Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show();
        tv_Dns1.setText(getText(R.string.dns1));
        tv_Dns2.setText(getText(R.string.dns2));
        tv_Dns1.setTextColor(getResources().getColor(R.color.green));
        tv_Dns2.setTextColor(getResources().getColor(R.color.green));
    }
}
