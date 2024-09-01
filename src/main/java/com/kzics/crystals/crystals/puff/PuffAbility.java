package com.kzics.crystals.crystals.puff;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class PuffAbility extends Ability {
    private long rightClickCooldown = 60;
    private long leftClickCooldown = 60;
    private long sneakCooldown = 10000; // 10 seconds in milliseconds
    private HashMap<UUID, Long> lastSneakTime = new HashMap<>();

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
    }

    @Override
    public String getRightClickDescription() {
        return "Pushes any enemies within 10 block radius 30 blocks away.";
    }

    @Override
    public String getLeftClickDescription() {
        return "Allows you to dash 5 blocks forward into the direction you're looking and deals 2 hearts of damage to any player in the way regardless of protection.";
    }

    @Override
    public void onRightClick(Player player) {
        player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (entity instanceof Player) {
                Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(30));

                // Ajouter des particules en spirale autour de l'ennemi repoussé
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
                        Location loc = entity.getLocation().add(x, y, z);
                        entity.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0, 0, 0, 0);
                    }
                }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);
            }
        });
    }

    @Override
    public void onSneak(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        if (lastSneakTime.containsKey(playerId) && currentTime - lastSneakTime.get(playerId) < sneakCooldown) {
            player.sendMessage("Ability is on cooldown!");
            return;
        }
        lastSneakTime.put(playerId, currentTime);

        Vector direction = player.getLocation().getDirection().normalize();
        direction.setY(1);
        player.setVelocity(direction.multiply(1.2));

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
                double y = 0.5 * Math.sin(t) - 1;
                double z = 0.5 * Math.sin(t);
                Location loc = player.getLocation().add(x, y, z);
                player.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0, 0, 0, 0);
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);
    }

    @Override
    public void onLeftClick(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        player.setVelocity(direction.multiply(5));
        player.getNearbyEntities(7, 7, 7).forEach(entity -> {
            if (entity instanceof Player) {
                ((Player) entity).damage(5);

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
                        Location loc = player.getLocation().add(x, y, z);
                        player.getWorld().spawnParticle(Particle.CRIT, loc, 1, 0, 0, 0, 0);
                    }
                }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);
            }
        });
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
        return "Puff Crystal";
    }

    @Override
    public String getDescription() {
        return "A unique crystal that harnesses the power of air, enabling quick movements and forceful pushes.";
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
