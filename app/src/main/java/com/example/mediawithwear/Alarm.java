package com.example.mediawithwear;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Alarm extends Service {

    private Timer timer = null;
    private Handler handler= new Handler();

    public Alarm() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if (timer!=null)
        {
            timer.cancel();
        }
        else {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimeDisplayTimerTask(),0,5000);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Оповещение", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
