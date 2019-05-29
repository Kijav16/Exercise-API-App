package com.example.exercise_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private Tracker tracker;
    Button testButton; //TEST
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * TEST
         */
        testButton = findViewById(R.id.TestBtn);
        testButton.setOnClickListener(v -> addLocalKill());

        activateBothering();
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
                tracker = new Tracker("ximias", c);
                tracker.calculateExerciseCount(false); //Should be able to check if device is online. set to online for now.
            }
        },10);


    }

    /**
     * TEST
     */
    public void addLocalKill(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                tracker.setKills(tracker.getKills() + 1);
                int currentKills = tracker.getLocalKills();
                testButton.post(new Runnable() {
                    public void run() {
                        testButton.setText(currentKills + "");
                    }
                });

            }
        }).start();
    }

    protected void activateBothering() {

        PeriodicWorkRequest br = new PeriodicWorkRequest.Builder(Botherer.class, 10, TimeUnit.HOURS).build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("EBother", ExistingPeriodicWorkPolicy.REPLACE ,br);
        //Toast.makeText(this, "Bother activated!", Toast.LENGTH_LONG).show();
        // Disabled bothering.
        WorkManager.getInstance().cancelUniqueWork("EBother");
    }
}
