package com.kzics.crystals.crystals.astral;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AstralAbility extends Ability {

    public static NamespacedKey key = new NamespacedKey(CrystalsPlugin.getInstance(), "astral-arrow");
    @Override
    public void onEat(PlayerItemConsumeEvent event) {

    }


    @Override
    public String getRightClickDescription() {
        return "Shoots 5 Arrows towards the direction the player is facing if a player gets hit by 1 arrow they take 3 hearts and their crystal is disabled for 10s ";
    }

    @Override
    public String getLeftClickDescription() {
        return "Will give any player in 150 block radius Glowing effect for 30s ";
    }

    @Override
    public void onRightClick(Player player) {


        new BukkitRunnable() {
            private int arrowsThrown = 0;

            @Override
            public void run() {
                if (arrowsThrown >= 5) {
                    cancel();
                    return;
                }

                Vector direction = player.getLocation().getDirection();
                Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), direction, 1.0f, 0.0f);
                arrow.setShooter(player);

                arrow.getPersistentDataContainer().set(key, PersistentDataType.STRING, getName());

                arrow.setDamage(6);

                arrowsThrown++;
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 10);

    }

    @Override
    public void onLeftClick(Player player) {

        Location playerLocation = player.getLocation();
        int radius = 150;

        for (Player target : Bukkit.getOnlinePlayers()) {
            if(target.getUniqueId().equals(player.getUniqueId())) continue;

            if (target.getWorld().equals(player.getWorld()) && target.getLocation().distance(playerLocation) <= radius) {
                target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0)); // 600 ticks = 30 secondes
            }
        }

    }

    @Override
    public void onKill(Player player) {
        player.setHealth(Math.min(player.getHealth() + 5, player.getMaxHealth()));

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
        return "";
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
        return 60;
    }

    @Override
    public long getLeftClickCooldown() {
        return 45;
    }


    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000,0));
    }
}
