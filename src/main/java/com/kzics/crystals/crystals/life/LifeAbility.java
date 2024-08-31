package com.kzics.crystals.crystals.life;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;

public class LifeAbility extends Ability {
    @Override
    public void onEat(PlayerItemConsumeEvent event){
        if(event.getItem().getType().equals(Material.GOLDEN_APPLE)){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,(20*60)* 2,1));
        }

    }

    @Override
    public String getRightClickDescription() {
        return "Removes 4 hearts automatically ";
    }

    @Override
    public String getLeftClickDescription() {
        return "Automatcially heals 5 hearts ";
    }

    @Override
    public void onRightClick(Player player) {

        List<Player> nearbyPlayers = player.getWorld().getPlayers();
        Player closestPlayer = null;
        double closestDistance = 5.0;

        for (Player target : nearbyPlayers) {
            if (target != player && player.getLocation().distance(target.getLocation()) <= closestDistance) {
                closestPlayer = target;
                closestDistance = player.getLocation().distance(target.getLocation());
            }
        }

        if (closestPlayer == null) {
            return;
        }

        double newHealth = closestPlayer.getHealth() - 8.0;
        closestPlayer.setHealth(Math.max(newHealth, 0));

        Location start = player.getLocation().add(0, 1, 0);
        Location end = closestPlayer.getLocation().add(0, 1, 0);

        Vector direction = end.toVector().subtract(start.toVector()).normalize();
        double distance = start.distance(end);
        int particleCount = (int) (distance * 10);

        for (int i = 0; i < particleCount; i++) {
            Location particleLocation = start.clone().add(direction.clone().multiply(i * (distance / particleCount)));
            player.getWorld().spawnParticle(Particle.HEART, particleLocation, 1, 0, 0, 0, 0);
        }
    }

    @Override
    public void onLeftClick(Player player) {

        player.setHealth(player.getHealth() + 5);

        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 30, 1, 1, 1, 0.1);
    }

    @Override
    public void onKill(Player player) {

    }

    @Override
    public void onDamaged(Player player) {

    }

    @Override
    public void onBreak(Player player) {

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
        return "Life Ability";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setCooldown(long cooldown) {

    }

    @Override
    public void playAbility(Player player) {

    }

    @Override
    public long getRightClickCooldown() {
        return 120;
    }

    @Override
    public long getLeftClickCooldown() {
        return 120;
    }

    @Override
    public void applyEffect(Player player) {

    }
}
