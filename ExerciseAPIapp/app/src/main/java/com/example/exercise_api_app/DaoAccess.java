package com.example.exercise_api_app;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setUserName(String userName);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setDeath(int death);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setDeathMultiplier(int deathMultiplier);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setHoursPlayed(int hoursPlayed);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setHoursPlayedMultiplier(int hoursPlayedMultiplier);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setKills(int kills);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setKillsMultiplier(int killsMultiplier);


    @Query("SELECT death FROM Stats WHERE userName = :userName")
    int getDeath(String userName);

    @Query("SELECT death_multiplier FROM Stats WHERE userName = :userName")
    int getDeathMultiplier(String userName);

    @Query("SELECT hours_played FROM Stats WHERE userName = :userName")
    int getHoursPlayed(String userName);

    @Query("SELECT hours_played_multiplier FROM Stats WHERE userName = :userName")
    int getHoursPlayedMultiplier(String userName);

    @Query("SELECT kills FROM Stats WHERE userName = :userName")
    int getkills(String userName);

    @Query("SELECT kills_multiplier FROM Stats WHERE userName = :userName")
    int getKillsMultiplier(String userName);

/*    @Update
    int updateStats(Stats stats);*/

}
