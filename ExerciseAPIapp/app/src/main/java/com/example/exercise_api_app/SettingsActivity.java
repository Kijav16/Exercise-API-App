package com.example.exercise_api_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SettingsActivity extends Activity {

    private EditText multiField;
    private Button comfirmButt;
    private Stats stats;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stats = new Stats(this);

        setContentView(R.layout.settings);

        multiField = (EditText) findViewById(R.id.Edittextt);
        comfirmButt = (Button) findViewById(R.id.Confirmbutt);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));


        comfirmButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(()->{
                    stats.setDeathMultiplier(Integer.valueOf(multiField.getText().toString()));
                }).start();

            }
        });


    }
}
