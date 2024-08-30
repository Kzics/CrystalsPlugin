package com.kzics.crystals.data;

import java.util.HashMap;
import java.util.UUID;

public class EnergyManager {

    private final HashMap<UUID, Integer> playerEnergy;

    public EnergyManager(){
        this.playerEnergy = new HashMap<>();
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
}
