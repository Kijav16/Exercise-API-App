package com.example.exercise_api_app;

import android.content.Context;

class TestStats extends Stats {
    private int deaths = 35;
    private int hoursPlayed = 46;
    private int kills = 35;
    private int deathMultiplier = 10;
    private int killsMultiplier = 2;
    private int hoursPlayedMultiplier = 5;

    public TestStats(Context context) {
        super(context);
    }


    @Override
    public int getDeath() {return deaths;}

    @Override
    public int getHoursPlayed() {return hoursPlayed;}

    @Override
    public int getKills() {return kills;}

    @Override
    public int getDeathMultiplier() {return deathMultiplier;}

    @Override
    public int getKillsMultiplier() {return killsMultiplier;}

    @Override
    public int getHoursPlayedMultiplier() {return hoursPlayedMultiplier;}

    @Override
    public void setDeath(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    @Override
    public void setKills(int kills) {
        this.kills = kills;
    }

    @Override
    public void setDeathMultiplier(int deathMultiplier) {
        this.deathMultiplier = deathMultiplier;
    }

    @Override
    public void setKillsMultiplier(int killsMultiplier) {
        this.killsMultiplier = killsMultiplier;
    }

    @Override
    public void setHoursPlayedMultiplier(int hoursPlayedMultiplier) {
        this.hoursPlayedMultiplier = hoursPlayedMultiplier;
    }
}