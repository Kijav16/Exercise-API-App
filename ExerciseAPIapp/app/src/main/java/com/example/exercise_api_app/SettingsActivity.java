package com.example.exercise_api_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SettingsActivity extends Activity {

    private EditText pushField;
    private EditText sitField;
    private EditText squadField;
    private Button comfirmpush;
    private Button comfirmsit;
    private Button comfirmsquad;
    private Stats stats;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stats = new Stats(this);

        setContentView(R.layout.settings);

        pushField = (EditText) findViewById(R.id.Edittextt);
        sitField = (EditText) findViewById(R.id.Edittextt2);
        squadField = (EditText) findViewById(R.id.Edittextt3);
        comfirmpush = (Button) findViewById(R.id.Confirmbutt);
        comfirmsit = (Button) findViewById(R.id.Confirmbutt2);
        comfirmsquad = (Button) findViewById(R.id.Comfirmbutt3);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));


        comfirmpush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(()->{
                    stats.setDeathMultiplier(Integer.valueOf(pushField.getText().toString()));
                }).start();

            }
        });
        comfirmsit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(()->{
                    stats.setKillsMultiplier(Integer.valueOf(pushField.getText().toString()));
                }).start();

            }
        });

        comfirmsquad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(()->{
                    stats.setHoursPlayedMultiplier(Integer.valueOf(pushField.getText().toString()));
                }).start();

            }
        });


    }
}
