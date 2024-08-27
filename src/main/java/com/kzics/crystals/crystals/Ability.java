package com.kzics.crystals.crystals;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class Ability {

    private static final ConcurrentMap<UUID, ConcurrentMap<String, Long>> playerCooldowns = new ConcurrentHashMap<>();

    public boolean activate(Player player){
        if(!checkCooldown(player)) {
            player.sendMessage("Ability is on cooldown!");
            return false;
        }
        return true;
    }
    public abstract long getCooldown();
    public abstract String getName();
    public abstract String getDescription();
    public abstract void setCooldown(long cooldown);
    public abstract void playAbility(Player player);

    protected boolean checkCooldown(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String abilityName = getName();

        playerCooldowns.putIfAbsent(playerId, new ConcurrentHashMap<>());
        Map<String, Long> cooldowns = playerCooldowns.get(playerId);

        if (cooldowns.containsKey(abilityName) && cooldowns.get(abilityName) > currentTime) {
            return false;
        }

        cooldowns.put(abilityName, currentTime + getCooldown() * 1000L);
        return true;
    }

    public long getRemainingCooldown(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String abilityName = getName();

        if (playerCooldowns.containsKey(playerId) && playerCooldowns.get(playerId).containsKey(abilityName) && playerCooldowns.get(playerId).get(abilityName) > currentTime) {
            return (playerCooldowns.get(playerId).get(abilityName) - currentTime) / 1000L;
        }
        return 0L;
    }
}
