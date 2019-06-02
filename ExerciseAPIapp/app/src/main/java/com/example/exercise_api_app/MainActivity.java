package com.example.exercise_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Adapter adapter;
    private Handler handler;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    StatConnect sc = new PS2Connect();
    AutoCompleteTextView autoCompleteTextView;
    Persistance persistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView =
                findViewById(R.id.auto_complete_edit_text);


        //Setting up the adapter for AutoSuggest
        adapter = new Adapter(this,
                android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setThreshold(4);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameSelected(autoCompleteTextView.getText().toString().toLowerCase());
            }
        });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if ((autoCompleteTextView.getText().length() > 3)) {

                        loadSuggestions(autoCompleteTextView.getText().toString());
                    }
                }
                return false;
            }
        });
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
                runOnUiThread(() -> {
                    Toast text = Toast.makeText(c, String.valueOf(test), Toast.LENGTH_LONG);
                    text.show();
                });
            }
        }, 60);
    }

    AsyncTask<String, Void, List<String>> loader;

    private void loadSuggestions(String prefix) {
        if (loader != null) {
            loader.cancel(true);

        }
        loader = new AsyncTask<String, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(String... strings) {
                return sc.listPlayersStartsWith(strings[0]);
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                super.onPostExecute(strings);
                Collections.sort(strings);
                adapter.setData(strings);
            }
        }.execute(prefix);
    }

    protected void activateBothering() {

        PeriodicWorkRequest br = new PeriodicWorkRequest.Builder(Botherer.class, 10, TimeUnit.HOURS).build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("EBother", ExistingPeriodicWorkPolicy.REPLACE, br);
        //Toast.makeText(this, "Bother activated!", Toast.LENGTH_LONG).show();
        // Disabled bothering.
        WorkManager.getInstance().cancelUniqueWork("EBother");
    }

    private void nameSelected(String username) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        Resources resources = getResources();
        String key = "username";
        intent.putExtra(key, username);
        startActivity(intent);

    }
}