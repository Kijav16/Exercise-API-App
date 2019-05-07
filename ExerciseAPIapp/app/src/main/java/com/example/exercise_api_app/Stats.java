package com.example.exercise_api_app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stats")
public class Stats {

    @NonNull
    @PrimaryKey
    private String userName;
    @ColumnInfo(name = "death")
    private int death;
    @ColumnInfo(name = "death_multiplier")
    private int deathMultiplier;
    @ColumnInfo(name = "hours_played")
    private int hoursPlayed;
    @ColumnInfo(name = "hours_played_multiplier")
    private int hoursPlayedMultiplier;
    @ColumnInfo(name = "kills")
    private int kills;
    @ColumnInfo(name = "kills_multiplier")
    private int killsMultiplier;

    public Stats() {

    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getDeathMultiplier() {
        return deathMultiplier;
    }

    public void setDeathMultiplier(int deathMultiplier) {
        this.deathMultiplier = deathMultiplier;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public int getHoursPlayedMultiplier() {
        return hoursPlayedMultiplier;
    }

    public void setHoursPlayedMultiplier(int hoursPlayedMultiplier) {
        this.hoursPlayedMultiplier = hoursPlayedMultiplier;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getKillsMultiplier() {
        return killsMultiplier;
    }

    public void setKillsMultiplier(int killsMultiplier) {
        this.killsMultiplier = killsMultiplier;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

