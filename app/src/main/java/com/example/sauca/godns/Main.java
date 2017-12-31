package com.example.sauca.godns;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends AppCompatActivity implements View.OnClickListener {

    Button bt_DNS,bt_Sair;
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

        bt_DNS = findViewById(R.id.BT_Dns);
        bt_Sair = findViewById(R.id.BT_Sair);

        tv_Ssid = findViewById(R.id.TV_Ssid);
        tv_Dns1 = findViewById(R.id.TV_Dns1);
        tv_Dns2 = findViewById(R.id.TV_Dns2);



        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo.isConnected()) {
            wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            connectionInfo = wifiManager.getConnectionInfo();
            connectionDhcp = wifiManager.getDhcpInfo();
            if (connectionInfo != null ) {
                tv_Ssid.setText(connectionInfo.getSSID());
                strIPAddess = ((connectionDhcp.dns1 ) & 0xFF) + "." + ((connectionDhcp.dns1 >> 8) & 0xFF) + "."
                        + ((connectionDhcp.dns1 >> 16) & 0xFF) + "." + ((connectionDhcp.dns1 >> 24) & 0xFF);
                tv_Dns1.setText(strIPAddess);
                strIPAddess = ((connectionDhcp.dns2 ) & 0xFF) + "." + ((connectionDhcp.dns2 >> 8) & 0xFF) + "."
                        + ((connectionDhcp.dns2 >> 16) & 0xFF) + "." + ((connectionDhcp.dns2 >> 24) & 0xFF);
                tv_Dns2.setText(strIPAddess);
            }else
                tv_Ssid.setText(getText(R.string.noconect));
        }else {
            Toast.makeText(this, R.string.noconect, Toast.LENGTH_SHORT).show();
            tv_Ssid.setText(getText(R.string.noconect));
        }
        bt_DNS.setOnClickListener(this);
        bt_Sair.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==findViewById(R.id.BT_Dns)){
            if (tv_Ssid.getCurrentTextColor() == getResources().getColor(R.color.red)) {
                tv_Ssid.setTextColor(getResources().getColor(R.color.green));
                Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show();
            } else
                tv_Ssid.setTextColor(getResources().getColor(R.color.red));
        }else if(view == findViewById(R.id.BT_Sair)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }
    }
}
