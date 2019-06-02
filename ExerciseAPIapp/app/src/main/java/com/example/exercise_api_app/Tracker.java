package com.example.exercise_api_app;

import android.content.Context;

public class Tracker {

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
        apiConnect = new PS2Connect();
        //apiConnect = new TestAPIConnect();
        apiConnect.setup(username);
        stats = new Stats(context);
        if (stats.getUserName()== null || !stats.getUserName().equals(username)){
            stats.setUserName(username);
            reloadInitialStats();
        }
        //stats = new TestStats(context);
    }

    private void reloadInitialStats() {
        setInitialDeaths(getDeaths());
        setInitialKills(getKills());
        setInitialHoursPlayed(getHoursPlayed());
    }

    public double getKillsExerciseRemaining() {
        return (int)((getKills() - getInitialKills()) * getKillsMultiplier());
    }

    public double getDeathsExerciseRemaining() {
        return (getDeaths() - getInitialDeaths()) * getDeathMultiplier();
    }

    public double getHoursPlayedExerciseRemaining() {
        return (getHoursPlayed() - getInitialHoursPlayed()) * getHoursPlayedMultiplier();
    }

    public void doDeathExercise(int amount){
        setInitialDeaths(amount*getDeathMultiplier());
    }
    public void doKillsExercise(int amount){
        setInitialKills(amount*getKillsMultiplier());
    }

    public void doHoursPlayedExercise(int amount){
        setInitialHoursPlayed(amount*getHoursPlayedMultiplier());
    }

    public void setKillsMultiplier(int multiplier){
        stats.setKillsMultiplier(multiplier);
    }

    public void setDeathsMultiplier(int multiplier) {
        stats.setDeathMultiplier(multiplier);
    }

    public void setHoursPlayedMultiplier(int multiplier) {
        stats.setHoursPlayedMultiplier(multiplier);
    }

    public void logout() {
        stats.setUserName("");
    }

    private int getDeaths() {
        return apiConnect.getDeaths();
    }

    private int getKills() {
        return apiConnect.getKills();
    }

    private int getHoursPlayed() {
        return apiConnect.getHoursPlayed();
    }

    private int getKillsMultiplier(){
        return stats.getKillsMultiplier();
    }

    private int getDeathMultiplier(){
        return stats.getDeathMultiplier();
    }

    private int getHoursPlayedMultiplier(){
        return stats.getHoursPlayedMultiplier();
    }

    private int getInitialDeaths(){
        return stats.getByString("iniDeaths");
    }

    private void setInitialDeaths(int amount){
        stats.setByString("iniDeaths", amount);
    }
    private int getInitialKills(){
        return stats.getByString("iniKills");
    }

    private void setInitialKills(int amount){
        stats.setByString("iniKills", amount);
    }

    private int getInitialHoursPlayed(){
        return stats.getByString("iniHoursPlayed");
    }

    private void setInitialHoursPlayed(int amount){
        stats.setByString("iniHoursPlayed", amount);
    }
}