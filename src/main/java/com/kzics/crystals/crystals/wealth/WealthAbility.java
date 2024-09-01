package com.kzics.crystals.crystals.wealth;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WealthAbility extends Ability {
    private long rightClickCooldown = 30; // 30 seconds in milliseconds
    private long leftClickCooldown = 30; // 30 seconds in milliseconds

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
        // Implementation here
    }

    @Override
    public void onRightClick(Player player) {
        player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (entity instanceof Player) {
                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 3)); // Slowness 4 for 15 seconds

                // Ajouter des particules de richesse autour des ennemis ralentis
                Location targetLocation = entity.getLocation();
                entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, targetLocation, 50, 1, 1, 1, 0.1);
                entity.getWorld().spawnParticle(Particle.TOTEM, targetLocation, 30, 1, 1, 1, 0.1);
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

        // Ajouter des particules de force autour du joueur
        Location playerLocation = player.getLocation();
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, playerLocation, 50, 1, 1, 1, 0.1);
        player.getWorld().spawnParticle(Particle.CRIT_MAGIC, playerLocation, 30, 1, 1, 1, 0.1);
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
        return "Wealth Crystal";
    }

    @Override
    public String getDescription() {
        return "A crystal that slows nearby enemies and boosts the player's luck.";
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

    @Override
    public void onSneak(Player player) {

    }

    public void setRightClickCooldown(long cooldown) {
        this.rightClickCooldown = cooldown;
    }

    public void setLeftClickCooldown(long cooldown) {
        this.leftClickCooldown = cooldown;
    }
}
