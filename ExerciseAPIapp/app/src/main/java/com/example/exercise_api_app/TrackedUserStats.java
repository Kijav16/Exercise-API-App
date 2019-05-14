package com.example.exercise_api_app;

import android.content.Context;

public class TrackedUserStats {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;
    private double killsExercise;
    private double deathsExercise;
    private double hoursplayedExercise;
    private  StatConnect apiConnect;
    private DaoAccess persistence;


    public TrackedUserStats(String username, Context context) {
        this.username = username;
        apiConnect = new TestAPIConnect();
        persistence = (DaoAccess) Persistance.getPersistance(context);
    }


    public void updateStats() {
        deaths = getDeaths();
        kills = getKills();
        hoursPlayed = getHoursPlayed();

        setHoursPlayed(hoursPlayed);
        setDeaths(deaths);
        setKills(kills);
    }

    public void calculateExerciseCount() {
        updateStats();
        killsExercise = kills * persistence.getKillsMultiplier(username);
        deathsExercise = deaths * persistence.getDeathMultiplier(username);
        hoursplayedExercise =  hoursPlayed * persistence.getHoursPlayedMultiplier(username);
    }



    public int getDeaths() {
        return apiConnect.getDeaths();
    }

    public String getUsername() {
        return username;
    }

    public int getKills() {
        return apiConnect.getKills();
    }

    public int getHoursPlayed() {
        return apiConnect.getHoursPlayed();
    }



    public void setHoursPlayed(int hoursPlayed) {
        persistence.setHoursPlayed(hoursPlayed);
    }

    public void setDeaths(int deaths) {
        persistence.setDeath(deaths);
    }

    public void setKills(int kills) {
        persistence.setKills(kills);
    }



}
