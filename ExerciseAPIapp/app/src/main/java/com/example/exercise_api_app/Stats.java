package com.example.exercise_api_app;

import android.content.Context;

import androidx.room.Entity;

@Entity(tableName = "stats")
public class Stats {
    Context c;

    public Stats(Context context) {
        c = context;
        Persistance.getPersistance(c.getApplicationContext());
    }

    public int getByString(String name){
        final int[] result = new int[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                result[0] = Persistance.getPersistance(c.getApplicationContext()).daoAccess().getInt(name);
            }
        }).start();
        return result[0];
    }

    public void setByString(String name, int data){
        IntMap stat = new IntMap();
        stat.setName(name);
        stat.setData(data);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Persistance.getPersistance(c.getApplicationContext()).daoAccess().setInt(stat);
            }
        }).start();
    }

    public int getDeath() {
        return getByString("Death");
    }

    public void setDeath(int death) {
        setByString("Death", death);
    }

    public int getDeathMultiplier() {
        return getByString("DeathMultiplier");
    }

    public void setDeathMultiplier(int deathMultiplier) {
        setByString("DeathMultiplier", deathMultiplier);
    }

    public int getHoursPlayed() {
        return getByString("HoursPlayed");
    }

    public void setHoursPlayed(int hoursPlayed) {
        setByString("HoursPlayed", hoursPlayed);
    }

    public int getHoursPlayedMultiplier() {
        return getByString("HoursPlayedMultiplier");
    }

    public void setHoursPlayedMultiplier(int hoursPlayedMultiplier) {
        setByString("HoursPlayedMultiplier", hoursPlayedMultiplier);
    }

    public int getKills() {
        return getByString("Kills");
    }

    public void setKills(int kills) {
        setByString("Kills", kills);
    }

    public int getKillsMultiplier() {
        return getByString("KillsMultiplier");
    }

    public void setKillsMultiplier(int killsMultiplier) {
        setByString("KillsMultiplier", killsMultiplier);
    }

    public String getUserName() {
        return Persistance.getPersistance(c).daoAccess().getString("Username");
    }

    public void setUserName(String userName) {
        StringMap sm = new StringMap();
        sm.setName("Username");
        sm.setData(userName);
        Persistance.getPersistance(c).daoAccess().setString(sm);
    }
}

