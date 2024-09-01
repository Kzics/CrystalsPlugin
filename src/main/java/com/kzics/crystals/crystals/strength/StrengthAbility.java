package com.kzics.crystals.crystals.strength;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class StrengthAbility extends Ability {

    private final HashMap<UUID, Integer> attackLevel = new HashMap<>();

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
        // Implementation here
    }

    @Override
    public void onSneak(Player player) {

    }

    @Override
    public void onRightClick(Player player) {
        Player target = (Player) player.getNearbyEntities(5, 5, 5)
                .stream()
                .filter(entity -> entity instanceof Player)
                .findAny()
                .orElse(null);

        if (target != null) {
            for (PotionEffect potionEffect : target.getActivePotionEffects()) {
                target.removePotionEffect(potionEffect.getType());
            }

            // Ajouter des particules de dissipation autour du joueur cible
            Location targetLocation = target.getLocation();
            target.getWorld().spawnParticle(Particle.SPELL_WITCH, targetLocation, 50, 1, 1, 1, 0.1);
            target.getWorld().spawnParticle(Particle.SMOKE_NORMAL, targetLocation, 30, 1, 1, 1, 0.1);
        }
    }

    @Override
    public void onLeftClick(Player player) {
        // Implementation here
    }

    @Override
    public void onKill(Player player) {
        player.setHealth(player.getHealth() + 4);

        // Ajouter des particules de guérison autour du joueur
        Location playerLocation = player.getLocation();
        player.getWorld().spawnParticle(Particle.HEART, playerLocation, 20, 1, 1, 1, 0.1);
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, playerLocation, 20, 1, 1, 1, 0.1);
    }

    @Override
    public void onDamaged(Player player) {
        // Implementation here
    }

    @Override
    public boolean onDamage(Player player) {
        if (attackLevel.containsKey(player.getUniqueId())) {
            if (attackLevel.get(player.getUniqueId()) < 4) {
                attackLevel.put(player.getUniqueId(), attackLevel.get(player.getUniqueId()) + 1);
                return false;
            } else {
                return true;
            }
        } else {
            attackLevel.put(player.getUniqueId(), 1);
        }
        return false;
    }

    @Override
    public void onBreak(Player player) {
        // Implementation here
    }

    @Override
    public long getCooldown() {
        return 0;
    }

    @Override
    public String getName() {
        return "Strength Crystal";
    }

    @Override
    public String getDescription() {
        return "A crystal ";
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
        return 60;
    }

    @Override
    public long getLeftClickCooldown() {
        return 0;
    }

    @Override
    public String getRightClickDescription() {
        return "Removes all potion effects from the player in a 5 block radius.";
    }

    @Override
    public String getLeftClickDescription() {
        return "None";
    }

    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000000, 1));
    }
}
