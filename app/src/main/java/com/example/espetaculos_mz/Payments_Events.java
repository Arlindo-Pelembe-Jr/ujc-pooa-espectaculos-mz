package com.example.espetaculos_mz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payments_Events extends AppCompatActivity {
    private TextView qtd;
    private Button btn;
    private Button btn2;

    int currentQtd=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments__events);
        qtd = findViewById(R.id.qtdPay);

        btn = findViewById(R.id.increment);
        btn2 = findViewById(R.id.decrement);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentQtd++;
//                qtd.setText("Quantidade:"+currentQtd);
////                qtd.notifyAll();
//            }
//        });

    }
}