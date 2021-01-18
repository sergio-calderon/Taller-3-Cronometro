package com.example.cronometro_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private int vuelta = 0;
    private List<String> my_times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_times = new ArrayList<String>();
        runTimer();
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds % 3600)/60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view){
        running = true;
    }
    public void onClickStop(View view){
        running = false;
    }
    public void onClickRestart(View view){
        reiniciarVariables();
        TextView TiempoVueltas = (TextView) findViewById(R.id.TiempoPorVuelta);
        TiempoVueltas.setText("Vueltas Dadas: ");
    }

    public void onClickLap(View view){
        TextView vueltas = (TextView) findViewById(R.id.Vueltas);
        TextView myTime = (TextView) findViewById(R.id.time_view);
        running = true;
        vuelta++;
        vueltas.setText(Integer.toString(vuelta));
        my_times.add(myTime.getText().toString());
        if(vuelta == 10){
            tenLaps();
        }
    }

    public void tenLaps(){
        String dato = "Tiempos por vuelta: \n";
        for(int i = 0; i < my_times.size() ; i++){
            dato += "Vuelta_" + (i+1) + ": " +my_times.get(i) + "\n";
        }
        TextView vueltas = (TextView) findViewById(R.id.TiempoPorVuelta);
        vueltas.setText(dato);
        reiniciarVariables();
    }

    public void reiniciarVariables(){
        TextView vueltas = (TextView) findViewById(R.id.Vueltas);

        running = false;
        vuelta = 0;
        seconds = 0;
        vueltas.setText(Integer.toString(vuelta));

    }
}