package com.example.exercise_api_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stats.class}, version = 1, exportSchema = false)
public abstract class Persistance extends RoomDatabase implements Runnable {

    private static Persistance INSTANCE;

    public abstract DaoAccess daoAccess();

    public static Persistance getPersistance(final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Persistance.class, "stats-database").build();
                }
            }
        }).start();
        return INSTANCE;
    }

    public static void  destroyInstance(){
        INSTANCE = null;
    }

}
