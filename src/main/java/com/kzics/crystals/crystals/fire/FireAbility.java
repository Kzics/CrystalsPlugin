package com.kzics.crystals.crystals.fire;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Location;
import org.bukkit.World;

public class FireAbility extends Ability {
    private long rightClickCooldown = 60; // 1 minute
    private long leftClickCooldown = 60; // 1 minute

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
        // Implementation here
    }

    @Override
    public String getRightClickDescription() {
        return "Launches a fireball to the way the player is facing that does 5 Hearts of Damage and creates an Explosion with fire.";
    }

    @Override
    public String getLeftClickDescription() {
        return "Heals you 3 hearts and spawns a campfire that automatically heals you 1 hearts/second.";
    }

    @Override
    public void onRightClick(Player player) {
        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setIsIncendiary(true);
        fireball.setYield(2); // Explosion size

        fireball.setMetadata("damage", new FixedMetadataValue(CrystalsPlugin.getInstance(), 10));

        // Ajouter des particules autour de la boule de feu
        new BukkitRunnable() {
            @Override
            public void run() {
                if (fireball.isDead() || fireball.isOnGround()) {
                    this.cancel();
                    return;
                }
                fireball.getWorld().spawnParticle(Particle.FLAME, fireball.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                fireball.getWorld().spawnParticle(Particle.SMOKE_LARGE, fireball.getLocation(), 10, 0.2, 0.2, 0.2, 0.05);
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 1);

        setRightClickCooldown(System.currentTimeMillis() / 1000);
    }

    @Override
    public void onLeftClick(Player player) {
        player.setHealth(Math.min(player.getHealth() + 6, player.getMaxHealth()));

        Location location = player.getLocation();
        World world = player.getWorld();
        world.getBlockAt(location).setType(Material.CAMPFIRE);

        new BukkitRunnable() {
            int seconds = 0;

            @Override
            public void run() {
                if (seconds < 45) {
                    player.setHealth(Math.min(player.getHealth() + 2, player.getMaxHealth()));
                    world.spawnParticle(Particle.VILLAGER_HAPPY, location.clone().add(0.5, 1, 0.5), 10, 0.5, 0.5, 0.5, 0); // Green particles

                    // Ajouter un cercle de particules autour du joueur
                    double radius = 1.5;
                    for (int i = 0; i < 360; i += 10) {
                        double angle = Math.toRadians(i);
                        double x = radius * Math.cos(angle);
                        double z = radius * Math.sin(angle);
                        world.spawnParticle(Particle.HEART, location.clone().add(x, 1, z), 1, 0, 0, 0, 0);
                    }

                    seconds++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 20);

        setLeftClickCooldown(System.currentTimeMillis() / 1000);
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
        return "Fire Crystal";
    }

    @Override
    public String getDescription() {
        return "Release the power of the flames with the fire crystal.";
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
    public void onSneak(Player player) {

    }

    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000000, 2));
    }

    public void setRightClickCooldown(long cooldown) {
        this.rightClickCooldown = cooldown;
    }

    public void setLeftClickCooldown(long cooldown) {
        this.leftClickCooldown = cooldown;
    }
}
