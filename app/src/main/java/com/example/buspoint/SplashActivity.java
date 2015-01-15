package com.example.buspoint;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.widget.ProgressBar;

/**
 * Created by josef on 10/01/2015.
 */
public class SplashActivity extends ActionBarActivity {

    public static final int segundos=8;
    public static final int milisegundos=segundos*1000;
    public static final int delay=2;
    private ProgressBar pbprogreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        pbprogreso= (ProgressBar) findViewById(R.id.pbprogreso);
        pbprogreso.setMax(maximo_progreso());
        empezaranimacion();
    }

    public void empezaranimacion()
    {
        new CountDownTimer(milisegundos,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                pbprogreso.setProgress(establecer_progreso(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent nuevofrom=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(nuevofrom);
                finish();
            }
        }.start();
    }

    public int establecer_progreso(long miliseconds)
    {
        return (int)((milisegundos-miliseconds)/1000);
    }

    public int maximo_progreso()
    {
        return segundos-delay;
    }

}
