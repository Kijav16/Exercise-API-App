package com.example.exercise_api_app;

public class trackedUserStats {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;
    private  StatConnect apiConnect;
    private Persistence persistence;


    public trackedUserStats(String username) {
        this.username = username;
        apiConnect = new TestAPIConnect();
        persistence = new Persistence();

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
        this.hoursPlayed = hoursPlayed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void getExerciseCount(){
        //combine mulitpliers and stats.

    }

}
