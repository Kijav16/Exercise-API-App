package com.example.exercise_api_app;

import java.util.List;

public interface StatConnect {

    /**
     * May throw {@link UnsupportedOperationException}, if the api does not provide this information.
     * Used for getting the number of hours a player has played for.
     * @return preferably the number of lifetime hours the player has played for,
     * though this may vary based on API implementation.
     */
    int getHoursPlayed();

    /**
     * Used for getting the current amount of deaths a player has.
     * @return preferably, the number of total lifetime deaths a player has obtained.
     * This may vary, if the API does not support such an operation.
     */
    int getDeaths();

    /**
     * May throw {@link UnsupportedOperationException} if the API does not support this operation,
     * or if the stat name does not exist.
     * Gets a stat based on a string, for instance assists, if it is obtainable by the API.
     * @param query the stat name to get.
     * @return the value of the given stat.
     */
    int get(String query);

    /**
     * Used for getting the current amount of kills a player has.
     * @return preferably, the number of total lifetime kills a player has obtained.
     * This may vary, if the API does not support such an operation.
     */
    int getKills();

    /**
     * Log in with a specific username.
     * @param username the username to check stats for.
     */
    void setup(String username);

    /**
     * May throw {@link UnsupportedOperationException} if an API does not support name prefix search.
     * @param prefix the name prefix to search for.
     * @return a list of players with the given prefix in the name.
     */
    List<String> listPlayersStartsWith(String prefix);
}
