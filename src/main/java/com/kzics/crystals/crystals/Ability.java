package com.kzics.crystals.crystals;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class Ability {

    private static final ConcurrentMap<UUID, ConcurrentMap<String, Long>> rightClickCooldowns = new ConcurrentHashMap<>();
    private static final ConcurrentMap<UUID, ConcurrentMap<String, Long>> leftClickCooldowns = new ConcurrentHashMap<>();

    public boolean activateRightClick(Player player) {
        if (!checkCooldown(player, rightClickCooldowns)) {
            player.sendMessage("Right-click ability is on cooldown!");
            return false;
        }
        onRightClick(player);
        return true;
    }

    public boolean activateLeftClick(Player player) {
        if (!checkCooldown(player, leftClickCooldowns)) {
            player.sendMessage("Left-click ability is on cooldown!");
            return false;
        }
        onLeftClick(player);
        return true;
    }


    public abstract void onEat(PlayerItemConsumeEvent event);
    public abstract void onRightClick(Player player);
    public abstract void onLeftClick(Player player);
    public abstract void onKill(Player player);
    public abstract void onDamaged(Player player);
    public abstract void onBreak(Player player);
    public abstract boolean onDamage(Player player);
    public abstract void onSneak(Player player);

    public abstract long getCooldown();
    public abstract String getName();
    public abstract String getDescription();
    public abstract void setCooldown(long cooldown);
    public abstract void playAbility(Player player);
    public abstract long getRightClickCooldown();
    public abstract long getLeftClickCooldown();
    public abstract String getRightClickDescription();
    public abstract String getLeftClickDescription();


    protected boolean checkCooldown(Player player, Map<UUID, ConcurrentMap<String, Long>> cooldownMap) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String abilityName = getName();

        cooldownMap.putIfAbsent(playerId, new ConcurrentHashMap<>());
        Map<String, Long> playerCooldowns = cooldownMap.get(playerId);

        if (playerCooldowns.containsKey(abilityName) && playerCooldowns.get(abilityName) > currentTime) {
            return false;
        }

        long cooldown = cooldownMap == rightClickCooldowns ? getRightClickCooldown() : getLeftClickCooldown();
        playerCooldowns.put(abilityName, currentTime + cooldown * 1000L);
        return true;
    }

    public abstract void applyEffect(Player player);

    public long getRemainingCooldown(Player player, boolean isRightClick) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        String abilityName = getName();

        Map<UUID, ConcurrentMap<String, Long>> cooldownMap = isRightClick ? rightClickCooldowns : leftClickCooldowns;

        if (cooldownMap.containsKey(playerId) && cooldownMap.get(playerId).containsKey(abilityName) && cooldownMap.get(playerId).get(abilityName) > currentTime) {
            return (cooldownMap.get(playerId).get(abilityName) - currentTime) / 1000L;
        }
        return 0L;
    }
}
