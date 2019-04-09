package com.example.exercise_api_app;

import java.util.List;

public interface StatConnect {
    int getHoursPlayed();

    int getDeaths();

    int get(String query);

    int getKills();

    void setup(String username);

    List<String> listPlayersStartsWith(String prefix);
}
