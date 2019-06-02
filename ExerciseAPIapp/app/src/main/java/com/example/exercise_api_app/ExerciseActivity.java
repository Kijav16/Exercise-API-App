package com.example.exercise_api_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExerciseActivity extends AppCompatActivity {

    private String username;

    //View vairables
    private Button pushButt;
    private Button sitButt;
    private Button squadButt;
    private ImageButton setButt;
    private Button outButt;
    Tracker tracker;

    private Stack<Integer> stack = new Stack<Integer>();
    private Timer trackerTimer;
    private static final Executor worker = Executors.newFixedThreadPool(1);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showex);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        if (username == null || username.isEmpty()) {
            username = "problems with intent name";
        }


        //Assign the thingies from the variable
        pushButt = findViewById(R.id.button);
        sitButt = findViewById(R.id.button2);
        squadButt = findViewById(R.id.button3);
        setButt = findViewById(R.id.setbutt);
        outButt = findViewById(R.id.logoff);

        worker.execute(()->{
            System.out.println("Init..");
            tracker = new Tracker(username, this);
            updateButtonText();

        });

        trackerTimer = new Timer();
        trackerTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateButtonText();
            }
        }, 2000, 2000);


        pushButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.execute(()->{
                    tracker.doDeathExercise(1);
                    updateButtonText();
                });
            }
        });

        sitButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.execute(() -> {
                    tracker.doKillsExercise(1);
                    updateButtonText();
                });
            }
        });

        squadButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.execute(() -> {
                    tracker.doHoursPlayedExercise(1);
                    updateButtonText();
                });
            }
        });


        setButt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, SettingsActivity.class));
            }
        });

        outButt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackerTimer.cancel();
                worker.execute(()->{
                    tracker.logout();
                    runOnUiThread(() -> {
                        Intent intent1 = new Intent(ExerciseActivity.this, MainActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    });
                });
            }
        });
    }


    private void updateButtonText() {
        double deathsExerciseRemaining = tracker.getDeathsExerciseRemaining();
        double killsExerciseRemaining = tracker.getKillsExerciseRemaining();
        double hoursPlayedExerciseRemaining = tracker.getHoursPlayedExerciseRemaining();
        runOnUiThread(() -> {
            pushButt.setText("You have " + String.valueOf(deathsExerciseRemaining) + " push-ups remaining");

            sitButt.setText("You have " + String.valueOf(killsExerciseRemaining) + " sit-ups remaining");

            squadButt.setText("You have " + String.valueOf(hoursPlayedExerciseRemaining) + " push-ups remaining");
        });
    }
}
