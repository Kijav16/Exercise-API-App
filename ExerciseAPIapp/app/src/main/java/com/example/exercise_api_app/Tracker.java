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
        apiConnect.setup(username);
        stats = new Stats(context);
        if (stats.getUserName()== null || !stats.getUserName().equals(username)){
            stats.setUserName(username);
            reloadInitialStats();
        }
    }

    /**
     * Reloads the initial stats, effectively setting them to 0.
     */
    private void reloadInitialStats() {
        setInitialDeaths(getDeaths());
        setInitialKills(getKills());
        setInitialHoursPlayed(getHoursPlayed());
    }

    /**
     * Getter for the kills exercises remaining.
     */
    public double getKillsExerciseRemaining() {
        if (getKillsMultiplier()==0){
            setInitialKills(getKills());
            return 0;
        }
        return (getKills() - getInitialKills()) * getKillsMultiplier();
    }

    /**
     * Getter for the remaining death exercises.
     */
    public double getDeathsExerciseRemaining() {
        if (getDeathMultiplier()==0){
            setInitialDeaths(getDeaths());
            return 0;
        }
        return (getDeaths() - getInitialDeaths()) * getDeathMultiplier();
    }

    /**
     * Getter for the remaining hours played exercises.
     */
    public double getHoursPlayedExerciseRemaining() {
        if (getHoursPlayedMultiplier()==0){
            setInitialHoursPlayed(getHoursPlayed());
            return 0;
        }
        return (getHoursPlayed() - getInitialHoursPlayed()) * getHoursPlayedMultiplier();
    }

    /**
     * Reduces the amount of exercises to be done by the amount specified.
     */
    public void doDeathExercise(int amount){
        setInitialDeaths(amount*getDeathMultiplier());
    }

    /**
     * Reduces the amount of exercises to be done by the amount specified.
     */
    public void doKillsExercise(int amount){
        setInitialKills(amount*getKillsMultiplier());
    }

    /**
     * Reduces the amount of exercises to be done by the amount specified.
     */
    public void doHoursPlayedExercise(int amount){
        setInitialHoursPlayed(amount*getHoursPlayedMultiplier());
    }

    /**
     * Setter for the kills multiplier.
     * Setting the multiplier to 0 will disable tracking for the stat.
     */
    public void setKillsMultiplier(int multiplier){
        stats.setKillsMultiplier(multiplier);
    }
    /**
     * Setter for the deaths multiplier.
     * Setting the multiplier to 0 will disable tracking for the stat.
     */
    public void setDeathsMultiplier(int multiplier) {
        stats.setDeathMultiplier(multiplier);
    }

    /**
     * Setter for the hours played multiplier.
     * Setting the multiplier to 0 will disable tracking for the stat.
     */
    public void setHoursPlayedMultiplier(int multiplier) {
        stats.setHoursPlayedMultiplier(multiplier);
    }

    /**
     * Performs a logout, resetting the user.
     */
    public void logout() {
        stats.setUserName("");
    }

    /**
     * Reset all multipliers to default values.
     */
    public void resetMultipliers(){
        setKillsMultiplier(1);
        setDeathsMultiplier(1);
        setHoursPlayedMultiplier(1);
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