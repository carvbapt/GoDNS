package com.example.sauca.godns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    Button bt_DNS,bt_Sair;
    TextView tv_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_DNS = findViewById(R.id.BT_Dns);
        bt_Sair = findViewById(R.id.BT_Sair);

        tv_Text = findViewById(R.id.TV_Text);

        bt_DNS.setOnClickListener(this);
        bt_Sair.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==findViewById(R.id.BT_Dns)){
            if (tv_Text.getCurrentTextColor() == getResources().getColor(R.color.red)) {
                tv_Text.setTextColor(getResources().getColor(R.color.green));
                Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show();
            } else
                tv_Text.setTextColor(getResources().getColor(R.color.red));
        }else if(view == findViewById(R.id.BT_Sair)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }
    }
}
