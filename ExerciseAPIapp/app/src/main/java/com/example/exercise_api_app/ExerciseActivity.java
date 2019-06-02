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

public class ExerciseActivity extends AppCompatActivity {

    private String username;

    //View vairables
    private Button pushButt;
    private Button sitButt;
    private Button squadButt;
    private Button setButt;
    private Button outButt;
    Tracker tracker;

    private Stack<Integer> stack = new Stack<Integer>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context c = this;
        Stats stats = new Stats(c);

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
        setButt = findViewById(R.id.button4);
        outButt = findViewById(R.id.logoff);

        new Thread(()-> {
            tracker = new Tracker(username, this);
            runOnUiThread(()->pushButt.setText("You have " + tracker.getDeathsExerciseRemaining() + " push-ups remaining"));

        }).start();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tracker.calculateExerciseCount();
                updateButtonText();
            }
        },2000,2000);


        Button.OnClickListener listener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tracker.doExercise();

            }
        };
        setButt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, SettingActivity.class));
            }
        });

        outButt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ExerciseActivity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });
    }



    private void updateButtonText() {
        runOnUiThread(()->{
            pushButt.setText("You have " + String.valueOf(tracker.getDeathsExerciseRemaining()) + " push-ups remaining");
        });
    }

    private void goBack(){
    stack.pop();
    }
}
