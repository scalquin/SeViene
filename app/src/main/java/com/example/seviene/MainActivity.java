package com.example.seviene;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    private long START_TIME_IN_MILLIS = experiment();
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.Caja);
        startTimer();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                txt.setText("A TOMARSH!!!");
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(){
        long dias = (mTimeLeftInMillis/1000)/86400;
        long hours = (((mTimeLeftInMillis/1000)/60)/60)%24;
        long minutes = ((mTimeLeftInMillis/1000)/60)%60;
        long seconds = (mTimeLeftInMillis/1000)%60;

        String time = "Faltan "+dias+" días con "+hours+" horas,"+minutes+" minutos y "+seconds+" segundos";
        txt.setText(time);
    }

    private long experiment(){
        Time today = new Time();
        today.setToNow();

        int mes = today.month;
        int hora = today.hour;
        int minutos = today.minute;
        int segundos = today.second;

        String hoy = today.year+"-"+(mes+1)+"-"+today.monthDay+" "+hora+":"+minutos+":"+segundos;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Date fechaInicial = dateFormat.parse(hoy);
            Date fechaFinal=dateFormat.parse("2020-09-18 00:00:00");

            long dias=(long) ((fechaFinal.getTime()-fechaInicial.getTime()));
            return dias;
        }catch (java.text.ParseException e){
            System.out.println("Cagó");
        }
            return 0;
    }

}
