package com.kzics.crystals.crystals.speed;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedAbility extends Ability {
    @Override
    public void onEat(PlayerItemConsumeEvent event) {
    }

    @Override
    public void onRightClick(Player player) {
        for (Player target : player.getWorld().getPlayers()) {
            if (target != player && target.getLocation().distance(player.getLocation()) <= 5) {
                freezePlayer(target);
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = player.getLocation();
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 16) {
                    double x = 0.5 * Math.cos(t);
                    double y = 0.5 * Math.sin(t) + 1;
                    double z = 0.5 * Math.sin(t);
                    loc.add(x, y, z);
                    player.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 1, 0, 0, 0, 0);
                    loc.subtract(x, y, z);
                }
            }
        }.runTaskLater(CrystalsPlugin.getInstance(), 0);
    }

    @Override
    public String getRightClickDescription() {
        return "Freezes nearby players for 10 seconds";
    }

    private void freezePlayer(Player player) {
        // Prevent player damage and move
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) 1000000, 255, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (int) 100000, 255, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int) 100000, 255, true, false));
        player.sendMessage("§bYou have been frozen!");

        new BukkitRunnable() {
            @Override
            public void run() {
                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.WEAKNESS);
                player.sendMessage("§bYou are no longer frozen.");
            }
        }.runTaskLater(CrystalsPlugin.getInstance(), 10*20);
    }

    @Override
    public String getLeftClickDescription() {
        return "Give the player speed III for 15 seconds.";
    }

    @Override
    public void onSneak(Player player) {

    }

    @Override
    public void onLeftClick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 15, 2));
        Bukkit.getScheduler().runTaskLater(CrystalsPlugin.getInstance(), () -> {
            applyEffect(player);
        }, 20 * 15);

        // Ajouter des particules de vitesse autour du joueur
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
                player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, loc, 1, 0, 0, 0, 0);
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
        return "Speed Crystal";
    }

    @Override
    public String getDescription() {
        return "A crystal that grants you incredible speed and control over nearby players.";
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
        return 0;
    }

    @Override
    public long getLeftClickCooldown() {
        return 45;
    }

    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100000000, 0));
    }
}
