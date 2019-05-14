package com.example.exercise_api_app;

import android.content.Context;

public class Tracker {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;
    private double killsExercise;
    private double deathsExercise;
    private double hoursplayedExercise;
    private  StatConnect apiConnect;
    private DaoAccess persistence;

    /**
     * Constructor for Tracker.
     * @param username
     * @param context
     */
    public Tracker(String username, Context context) {
        this.username = username;
        apiConnect = new TestAPIConnect();
        persistence = (DaoAccess) Persistance.getPersistance(context);
    }


    /**
     *  Pulls playerdata from either API, or from local database if set to offline.
      * @param offline
     */
    public void updateStats(boolean offline) {
        if (!offline) {
            deaths = getDeaths();
            kills = getKills();
            hoursPlayed = getHoursPlayed();

            setHoursPlayed(hoursPlayed);
            setDeaths(deaths);
            setKills(kills);
        } else {
            deaths = persistence.getDeath(username);
            kills = persistence.getkills(username);
            hoursPlayed = persistence.getHoursPlayed(username);
        }

    }

    /**
     * Calculates the Number of exercises the user needs to take, based on the gathered playerdata.
     * @param offline
     */
    public void calculateExerciseCount(Boolean offline) {
        updateStats(offline);

        killsExercise = kills * persistence.getKillsMultiplier(username);
        deathsExercise = deaths * persistence.getDeathMultiplier(username);
        hoursplayedExercise =  hoursPlayed * persistence.getHoursPlayedMultiplier(username);
    }


    //Gets data from API
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


    //Sets Data in the Database
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
