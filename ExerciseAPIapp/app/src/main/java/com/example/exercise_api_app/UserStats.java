package com.example.exercise_api_app;

import java.util.ArrayList;

public class UserStats {
    //class to keep track of exercises the user owes
    ArrayList exerciseList;

    public UserStats() {
        exerciseList = new ArrayList<Exercise>;
    }

    public void addExercise(String name) {

    }

    public void addExercise(String name, double killmultiplier, double deathMultiplier, double HoursPlayedMultiplier) {
       Exercise exercise = new Exercise(name);
       exercise.setKillsMulitplier(killmultiplier);
       exercise.setDeathsMultiplier(deathMultiplier);
       exercise.setHoursPlayedMultiplier(HoursPlayedMultiplier);

       exerciseList.add(exercise);
    }
}
