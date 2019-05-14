package com.example.exercise_api_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stats.class, UserDefinedStats.class}, version = 1, exportSchema = false)
public abstract class Persistance_Impl extends RoomDatabase implements Runnable {

    private static Persistance_Impl INSTANCE;

    public abstract DaoAccess daoAccess();

    public static Persistance_Impl getPersistance(final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Persistance_Impl.class, "stats-database").build();
                }
            }
        }).start();
        return INSTANCE;
    }

    public static void  destroyInstance(){
        INSTANCE = null;
    }

}
