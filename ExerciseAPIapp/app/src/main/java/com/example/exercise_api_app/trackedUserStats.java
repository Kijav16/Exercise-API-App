package com.example.exercise_api_app;

public class trackedUserStats {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;

    public trackedUserStats(String username) {
        this.username = username;
    }

    public trackedUserStats(String username, int hoursPlayed) {
        this.username = username;
        this.hoursPlayed = hoursPlayed;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getUsername() {
        return username;
    }

    public int getKills() {
        return kills;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
