package com.example.exercise_api_app;

public class Exercise {
    private String name;
    private String condition;

    //balance of users owed reps of this exercise
    private double balance;
    private double mulitplier = 1.2;


    public Exercise(String name, String condition) {
        this.name = name;
    }

    public void addRep() {
        balance = balance + mulitplier;
    }

    public void setMulitplier(double mulitplier) {
        this.mulitplier = mulitplier;
    }

    public String getCondition() {
        return condition;
    }
}
