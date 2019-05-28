package com.example.exercise_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    Persistance persistance;
    private Tracker tracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateBothering();
        tracker = new Tracker("SADMAN", this);
        tracker.calculateExerciseCount(false); //Should be able to check if device is online. set to online for now.
        Context c = this;
        Stats stats = new Stats(c);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                stats.setByString("Test", 2);
                int test = stats.getByString("Test");
                System.out.println(test);
                runOnUiThread(()-> {
                    Toast text = Toast.makeText(c, String.valueOf(test),Toast.LENGTH_LONG);
                    text.show();
                });
            }
        },10);
    }

    protected void activateBothering() {

        PeriodicWorkRequest br = new PeriodicWorkRequest.Builder(Botherer.class, 10, TimeUnit.HOURS).build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("EBother", ExistingPeriodicWorkPolicy.REPLACE ,br);
        //Toast.makeText(this, "Bother activated!", Toast.LENGTH_LONG).show();
        // Disabled bothering.
        WorkManager.getInstance().cancelUniqueWork("EBother");
    }
}
