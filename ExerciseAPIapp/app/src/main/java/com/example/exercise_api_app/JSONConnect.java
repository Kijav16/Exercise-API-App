package com.example.exercise_api_app;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONConnect {
    private final String endpoint;

    public JSONConnect(String endpoint) {
        this.endpoint = endpoint;
    }

    public JSONObject establishConnectionAndQuery(String context, String urlParameters) {
        JSONObject result = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint + context + urlParameters).openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                Log.e(this.getClass().getName(),"Unexpected response code: " + connection.getResponseCode());
                if (connection.getResponseCode() - 200 >= 100) {
                    throw new Error("server error response (" + connection.getResponseCode() + ") to request: ...get/ps2/" + urlParameters);
                }
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            result = null;
            try {
                System.out.println(response.toString());
                result = new JSONObject(response.toString());
            } catch (JSONException e) {
                throw new Error("Json exception from response: "+response.toString());
            }
            connection.disconnect();
        } catch (IOException e) {
            Log.e(getClass().getName(),"Networking error");
            e.printStackTrace();
        }
        return result;
    }
}
