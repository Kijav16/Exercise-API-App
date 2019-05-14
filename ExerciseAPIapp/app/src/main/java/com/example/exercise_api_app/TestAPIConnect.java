package com.example.exercise_api_app;

import java.util.List;

public class TestAPIConnect implements StatConnect {

    @Override
    public int getHoursPlayed() {
        return 125;
    }

    @Override
    public int getDeaths() {
        return 50;
    }

    @Override
    public int get(String query) {
        return 0;
    }

    @Override
    public int getKills() {
        return 100;
    }

    @Override
    public void setup(String username) {

    }

    @Override
    public List<String> listPlayersStartsWith(String prefix) {
        return null;
    }
}
