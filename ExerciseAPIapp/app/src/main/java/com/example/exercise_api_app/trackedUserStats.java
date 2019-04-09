package com.example.exercise_api_app;

import java.util.ArrayList;

public class trackedUserStats {
    private String username;
    private int hoursPlayed;
    private int deaths;
    private int kills;
    private  StatConnect apiConnect;
    private Persistence persistence;

    ArrayList<Exercise> exerciseList;

    public trackedUserStats(String username) {
        this.username = username;
        exerciseList = new ArrayList<Exercise>();
        apiConnect = new TestAPIConnect();
        persistence = new Persistence();

    }

    //adds exercise with default multiplier parameters
    public void addExercise(String name, String condition) {
        addExercise(name, condition, 1.5);
    }

    public void addExercise(String name, String condition, double multiplier) {
        Exercise exercise = new Exercise(name, condition);
        exercise.setMulitplier(multiplier);

        exerciseList.add(exercise);
    }

    public void ExerciseCount(String condition){
        //combine mulitpliers and stats.
        for (Exercise exercise : exerciseList) {
            if (exercise.getCondition() == condition) {
                exercise.addRep();
            }
        }
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



}
