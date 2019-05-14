package com.example.exercise_api_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PS2Connect implements StatConnect{

    private static final String CONTEXT_ROOT = "/s:XouPs2/get/ps2/";

    private static JSONConnect connection = new JSONConnect("http://census.daybreakgames.com");
    private HashMap<String, Integer> statMap = new HashMap<>(65);
    private String usrename;
    private String userID;
    private long lastUpdate = 0;

    /**
     * Returns the first player whose name starts with namePrefix parameter.
     * Priority goes to in alphabetical order. Inserting ximia will select ximia over ximiaa over ximias
     *
     * @param namePrefix the player name to select from.
     * @return the JSONObject with player data.
     * @throws IOException
     */
    public JSONObject findPlayerByName(String namePrefix) {
        try {
            JSONObject players = connection.establishConnectionAndQuery(CONTEXT_ROOT +"character_name/","?name.first_lower=^" + namePrefix.toLowerCase() + "&c:limit=10");
            return connection.establishConnectionAndQuery(CONTEXT_ROOT +"character","/?character_id=" + players.getJSONArray("character_name_list").getJSONObject(0).getString("character_id")).getJSONArray("character_list").getJSONObject(0);
        } catch (JSONException e) {
            Log.e(this.getClass().getName()+" FindPlayerByName", "Missing jsonArray from response.");
            throw new Error("Bad json");
        }
    }

    public JSONObject getCharacterStatsById() throws JSONException {
        return connection.establishConnectionAndQuery(CONTEXT_ROOT +"character/","?character_id="+userID+"&c:resolve=stat_by_faction").getJSONArray("character_list").getJSONObject(0);
    }

    /**
     * Lists 10 players with the same prefix as the parameter.
     *
     * @param prefix the prefix of the player names.
     * @return up to 10 player names and ids with the prefix in their lower name
     */
    public List<String> listPlayersStartsWith(String prefix) {
        JSONObject players = connection.establishConnectionAndQuery(CONTEXT_ROOT +"character_name/","?name.first_lower=^" + prefix.toLowerCase() + "&c:limit=10");
        if (players.has("character_name_list")) {
            try {

                JSONArray playerList = players.getJSONArray("character_name_list");
                LinkedList<String> result = new LinkedList<>();
                for (int i = 0; i < playerList.length(); i++) {
                    result.add(playerList.getString(i));
                }
                return result;
            } catch (JSONException e) {
                Log.wtf(this.getClass().getName(), "listPlayersStartsWith: JsonArray is scrödinger");
                throw new Error("WTF");
            }
        } else {
            throw new Error("PlanetSide server responded with: " + players);
        }
    }

    private void updateStatsMap() {
        if (lastUpdate +1000 > System.currentTimeMillis()) return;
        lastUpdate = System.currentTimeMillis();
        HashMap<String, Integer> result = new HashMap<>();
        try {
            JSONObject character = getCharacterStatsById();
            result.put("minutes_played", Integer.valueOf(character.getJSONObject("times").getString("minutes_played")));
            JSONArray statArray = character.getJSONObject("stats").getJSONArray("stat_by_faction");
            for (int i = 0; i < statArray.length(); i++) {
                JSONObject stat = statArray.getJSONObject(i);
                if (result.containsKey(stat.getString("stat_name"))){
                    // Might need to be Integer.parseInt(stat.getString("value_forever"));
                    result.put(stat.getString("stat_name"), result.get(stat.getString("stat_name") + stat.getInt("value_forever")));
                }else{
                    result.put(stat.getString("stat_name"),stat.optInt("value_forever"));
                }
            }
            statMap = result;
        } catch (JSONException e) {
            Log.e(getClass().getName() + " UpdateStatMap: ","Malformed Json");
            e.printStackTrace();
        }
    }

    @Override
    public int getHoursPlayed() {
        return statMap.get("minutes_played")/60;
    }

    @Override
    public int getDeaths() {
        updateStatsMap();
        return statMap.get("killed_by");
    }

    @Override
    public int get(String query) {
        return statMap.get(query);
    }

    @Override
    public int getKills() {
        return statMap.get("kills");
    }

    @Override
    public void setup(String username) {
        this.usrename = username;
        this.userID = findPlayerByName(username).optString("character_id");
    }
}
