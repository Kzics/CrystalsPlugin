package com.kzics.crystals.crystals.wealth;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WealthAbility extends Ability {
    private long rightClickCooldown = 30000; // 30 seconds in milliseconds
    private long leftClickCooldown = 30000; // 30 seconds in milliseconds

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
        // Implementation here
    }

    @Override
    public void onRightClick(Player player) {
        player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (entity instanceof Player) {
                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 3)); // Slowness 4 for 15 seconds
            }
        });
    }

    @Override
    public String getLeftClickDescription() {
        return "Gives the player strength.";
    }

    @Override
    public String getRightClickDescription() {
        return "Slows down nearby enemies.";
    }

    @Override
    public void onLeftClick(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 900, 1)); // Strength 2 for 45 seconds

    }

    @Override
    public void onKill(Player player) {
        // Implementation here
    }

    @Override
    public void onDamaged(Player player) {
        // Implementation here
    }

    @Override
    public void onBreak(Player player) {
        // Implementation here
    }

    @Override
    public boolean onDamage(Player player) {
        return false;
    }

    @Override
    public long getCooldown() {
        return 0;
    }

    @Override
    public String getName() {
        return "WealthAbility";
    }

    @Override
    public String getDescription() {
        return "An ability that gives slowness to enemies and strength to the player.";
    }

    @Override
    public void setCooldown(long cooldown) {
        // Implementation here
    }

    @Override
    public void playAbility(Player player) {
        // Implementation here
    }

    @Override
    public long getRightClickCooldown() {
        return rightClickCooldown;
    }

    @Override
    public long getLeftClickCooldown() {
        return leftClickCooldown;
    }

    @Override
    public void applyEffect(Player player) {
        // Implementation here
    }

    public void setRightClickCooldown(long cooldown) {
        this.rightClickCooldown = cooldown;
    }

    public void setLeftClickCooldown(long cooldown) {
        this.leftClickCooldown = cooldown;
    }
}
