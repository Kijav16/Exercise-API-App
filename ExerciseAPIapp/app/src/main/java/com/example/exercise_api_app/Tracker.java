package com.example.exercise_api_app;

import android.content.Context;

public class Tracker {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;
    private double killsExerciseRemaining;
    private double deathsExerciseRemaining;
    private double hoursPlayedExerciseRemaining;

    private double killsMultiplier;
    private double deathsMultiplier;
    private double hoursPlayedMultiplier;
    private int iniDeaths;
    private int iniKills;
    private int iniHoursPlayed;

    private StatConnect apiConnect;
    private final Stats stats;

    /**
     * TODO:
     * DONE - Make sure to store stats from when the tracker is first initiated.
     * DONE - Only calculate number of remaining exercises from difference of (First stats from when the tracker is first initialized versus current stats)
     * SEMIDONE(Tracks remaining exercises instead)- Also keep track of how many exercises are already performed
     *
     */

    /**
     * Constructor for Tracker.
     *
     * @param username
     * @param context
     */
    public Tracker(String username, Context context) {
        this.username = username;
        apiConnect = new PS2Connect();
        //apiConnect = new TestAPIConnect();
        apiConnect.setup(username);
        stats = new Stats(context);
        //stats = new TestStats(context);
    }


    /**
     * Pulls playerdata from either API, or from local database if set to offline.
     */
    public void updateStats() {
        //fetch from API
        deaths = getDeaths();
        kills = getKills();
        hoursPlayed = getHoursPlayed();

        //Fetch initial data
        iniDeaths = stats.getDeath();
        iniKills = stats.getKills();
        iniHoursPlayed = stats.getHoursPlayed();

        //fetch Multipliers locally
        deathsMultiplier = stats.getDeathMultiplier();
        killsMultiplier = stats.getKillsMultiplier();
        hoursPlayedMultiplier = stats.getHoursPlayedMultiplier();

    }

    /**
     * Calculates the Number of exercises the user needs to take, based on the gathered playerdata,
     * compared to the local playerdata.
     * <p>
     * Remaining = (Data from API - (Initial dataset + exercises completed)) * Exercise multiplier
     */
    public void calculateExerciseCount() {
        updateStats();

        killsExerciseRemaining = (kills - iniKills) * killsMultiplier;
        deathsExerciseRemaining = (deaths - iniDeaths) * deathsMultiplier;
        hoursPlayedExerciseRemaining = (hoursPlayed - iniHoursPlayed) * hoursPlayedMultiplier;
        System.out.println("kills: " + kills);

    }

    /**
     * Records amount of exercises the user have done, by increasing the local Stats.
     * This means that the difference between local stats and global stats is decreased, and therefore
     * fewer Exercises left for the user.
     *
     * @param exercise
     * @param amount
     */
    public void doExercise(String exercise, int amount) {
        exercise = exercise.toLowerCase();
        if (exercise == "deaths" || exercise == "death") {
            setDeaths(getLocalDeaths() + amount);
        }
        if (exercise == "kills" || exercise == "kill") {
            setKills(getLocalKills() + amount);
        }
        if (exercise == "hoursplayed" || exercise == "hours played") {
            setHoursPlayed(getLocalHoursPlayed() + amount);
        }

        calculateExerciseCount();
    }

    public String getUsername() {
        return username;
    }

    //Gets data from API
    public int getDeaths() {
        return apiConnect.getDeaths();
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

    public double getKillsExerciseRemaining() {
        return killsExerciseRemaining;
    }

    public double getDeathsExerciseRemaining() {
        return deathsExerciseRemaining;
    }

    public double getHoursPlayedExerciseRemaining() {
        return hoursPlayedExerciseRemaining;
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