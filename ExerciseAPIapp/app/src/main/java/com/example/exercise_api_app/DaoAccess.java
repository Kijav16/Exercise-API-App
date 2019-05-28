package com.example.exercise_api_app;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DaoAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setInt(IntMap stat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setString(StringMap stat);

    @Query("SELECT data FROM intmap WHERE name = :statName")
    int getInt(String statName);

    @Query("SELECT data FROM stringmap WHERE name = :statName")
    String getString(String statName);


}
