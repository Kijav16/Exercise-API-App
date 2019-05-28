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
    private final Stats stats;

    /**
     * TODO:
     * - Make sure to store stats from when the tracker is first initiated.
     * - Only calculate number of remaining exercises from difference of (First stats from when the tracker is first initialized versus current stats)
     * - Also keep track of how many exercises are already performed
     */

    /**
     * Constructor for Tracker.
     * @param username
     * @param context
     */
    public Tracker(String username, Context context) {
        this.username = username;
        apiConnect = new PS2Connect();
        apiConnect.setup(username);
        stats = new Stats(context);
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
            deaths = stats.getDeath();
            kills = stats.getKills();
            hoursPlayed = stats.getHoursPlayed();
        }

    }

    /**
     * Calculates the Number of exercises the user needs to take, based on the gathered playerdata.
     * @param offline
     */
    public void calculateExerciseCount(Boolean offline) {
        updateStats(offline);

        killsExercise = kills * stats.getKillsMultiplier();
        deathsExercise = deaths * stats.getDeathMultiplier();
        hoursplayedExercise =  hoursPlayed * stats.getHoursPlayedMultiplier();
        System.out.println("deaths: " + deaths);
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
        stats.setHoursPlayed(hoursPlayed);
    }

    public void setDeaths(int deaths) {
        stats.setDeath(deaths);
    }

    public void setKills(int kills) {
        stats.setKills(kills);
    }



}
