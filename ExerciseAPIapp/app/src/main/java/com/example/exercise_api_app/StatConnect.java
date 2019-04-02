package com.example.exercise_api_app;

public interface StatConnect {
    int getHoursPlayed();

    int getDeaths();

    int get(String query);

    int getKills();

    void setup(String username);
}
