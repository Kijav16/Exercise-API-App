package com.example.exercise_api_app;

public class Exercise {
    private String name;

    //balance of users owed reps of this exercise
    private double balance;

    private double killsMulitplier = 1.2;
    private double deathsMultiplier = 1.5;
    private double hoursPlayedMultiplier = 0.2;

    public Exercise(String name) {
        this.name = name;
    }

    public void addKillRep() {
        balance = balance + killsMulitplier;
    }

    public void addDeathRep() {
        balance = balance + deathsMultiplier;
    }

    public void addHoursPlayedRep() {
        balance = balance + hoursPlayedMultiplier;
    }
    public void setKillsMulitplier(double killsMulitplier) {
        this.killsMulitplier = killsMulitplier;
    }

    public void setDeathsMultiplier(double deathsMultiplier) {
        this.deathsMultiplier = deathsMultiplier;
    }

    public void setHoursPlayedMultiplier(double hoursPlayedMultiplier) {
        this.hoursPlayedMultiplier = hoursPlayedMultiplier;
    }


}
