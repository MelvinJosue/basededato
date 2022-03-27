package com.example.basededato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(getApplicationContext(),Datos2.class);

        TimerTask siguiente=new TimerTask() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this,Datos2.class);
                startActivity(intent);
                finish();
            }
        };
        Timer Hora=new Timer();
        Hora.schedule(siguiente,7000);
    }
}