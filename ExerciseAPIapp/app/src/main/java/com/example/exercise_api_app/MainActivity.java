package com.example.exercise_api_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    Persistance persistance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateBothering();
        persistance = Persistance.getPersistance(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DaoAccess dao = persistance.daoAccess();
                dao.setDeathMultiplier(2);
            }
        },200);
    }

    protected void activateBothering() {

        PeriodicWorkRequest br = new PeriodicWorkRequest.Builder(Botherer.class, 10, TimeUnit.HOURS).build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("EBother", ExistingPeriodicWorkPolicy.REPLACE ,br);
        Toast.makeText(this, "Bother activated!", Toast.LENGTH_LONG).show();
        // Disabled bothering.
        WorkManager.getInstance().cancelUniqueWork("EBother");
    }
}
