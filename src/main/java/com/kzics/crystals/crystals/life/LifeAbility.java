package com.kzics.crystals.crystals.life;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
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
        return "Removes 4 hearts automatically.";
    }

    @Override
    public String getLeftClickDescription() {
        return "Automatically heals 5 hearts.";
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

        // Ajouter un effet de particules en spirale autour du joueur cible
        Player finalClosestPlayer = closestPlayer;
        new BukkitRunnable() {
            double t = 0;
            @Override
            public void run() {
                if (t > Math.PI * 2) {
                    this.cancel();
                    return;
                }
                t += Math.PI / 16;
                double x = 0.5 * Math.cos(t);
                double y = 0.5 * Math.sin(t) + 1;
                double z = 0.5 * Math.sin(t);
                Location loc = finalClosestPlayer.getLocation().add(x, y, z);
                finalClosestPlayer.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 1, 0, 0, 0, 0);
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);
    }

    @Override
    public void onSneak(Player player) {

    }

    @Override
    public void onLeftClick(Player player) {
        player.setHealth(player.getHealth() + 5);

        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 30, 1, 1, 1, 0.1);

        new BukkitRunnable() {
            double phi = 0;
            @Override
            public void run() {
                phi += Math.PI / 10;
                for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 10) {
                    double x = 1.5 * Math.cos(theta) * Math.sin(phi);
                    double y = 1.5 * Math.cos(phi) + 1.5;
                    double z = 1.5 * Math.sin(theta) * Math.sin(phi);
                    Location loc = player.getLocation().add(x, y, z);
                    player.getWorld().spawnParticle(Particle.HEART, loc, 1, 0, 0, 0, 0);
                }
                if (phi > Math.PI) {
                    this.cancel();
                }
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);
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
        return "Life Ability";
    }

    @Override
    public String getDescription() {
        return "A rare crystal filled with the essence of vitality.";
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
        return 120;
    }

    @Override
    public long getLeftClickCooldown() {
        return 120;
    }

    @Override
    public void applyEffect(Player player) {
        // Implementation here
    }
}
