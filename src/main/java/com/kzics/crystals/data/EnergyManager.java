package com.kzics.crystals.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.UUID;

public class EnergyManager {

    private final HashMap<UUID, Integer> playerEnergy;
    private final Gson gson;
    private final String filePath;

    public EnergyManager(String filePath){
        this.playerEnergy = new HashMap<>();
        this.gson = new Gson();
        this.filePath = filePath;
    }

    public HashMap<UUID, Integer> getPlayerEnergy() {
        return playerEnergy;
    }

    public void addEnergy(UUID player, int amount){
        if(playerEnergy.containsKey(player)){
            playerEnergy.put(player, playerEnergy.get(player) + amount);
        }else{
            playerEnergy.put(player, amount);
        }
    }

    public void removeEnergy(UUID player, int amount){
        if(playerEnergy.containsKey(player)){
            playerEnergy.put(player, playerEnergy.get(player) - amount);
        }
    }

    public int getEnergy(UUID player){
        if(playerEnergy.containsKey(player)){
            return playerEnergy.get(player);
        }
        return 0;
    }

    public boolean hasEnergy(UUID player){
        return playerEnergy.containsKey(player);
    }

    public void saveData() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(playerEnergy, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<HashMap<UUID, Integer>>() {}.getType();
            HashMap<UUID, Integer> data = gson.fromJson(reader, type);
            if (data != null) {
                playerEnergy.putAll(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
