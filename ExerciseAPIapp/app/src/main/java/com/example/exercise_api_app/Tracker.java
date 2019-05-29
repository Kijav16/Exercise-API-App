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
    private double killsMultiplier;
    private double deathsMultiplier;
    private double hoursPlayedMultiplier;

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
            //fetch  from API
            deaths = getDeaths();
            kills = getKills();
            hoursPlayed = getHoursPlayed();


            //Store locally
            setHoursPlayed(hoursPlayed);
            setDeaths(deaths);
            setKills(kills);
        } else {
            //Fetch locally
            deaths = stats.getDeath();
            kills = stats.getKills();
            hoursPlayed = stats.getHoursPlayed();
        }
        //fetch Multipliers locally
        deathsMultiplier = stats.getDeathMultiplier();
        killsMultiplier = stats.getKillsMultiplier();
        hoursPlayedMultiplier = stats.getHoursPlayedMultiplier();

    }

    /**
     * Calculates the Number of exercises the user needs to take, based on the gathered playerdata.
     * @param isOffline
     */
    public void calculateExerciseCount(Boolean isOffline) {
        updateStats(isOffline);

        killsExercise = kills * killsMultiplier;
        deathsExercise = deaths * deathsMultiplier;
        hoursplayedExercise =  hoursPlayed * hoursPlayedMultiplier;
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


    //Gets Data from the database
    public int getLocalKills() {
        return stats.getKills();
    }

    public int getLocalDeaths() {
        return stats.getDeath();
    }

    public int getLocalHoursPlayed() {
        return stats.getHoursPlayed();
    }

    public double getKillsExercise() {
        return killsExercise;
    }

    public double getDeathsExercise() {
        return deathsExercise;
    }

    public double getHoursplayedExercise() {
        return hoursplayedExercise;
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
