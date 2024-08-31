package com.kzics.crystals.crystals.speed;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedAbility extends Ability {
    @Override
    public void onEat(PlayerItemConsumeEvent event) {

    }

    @Override
    public void onRightClick(Player player) {

    }

    @Override
    public String getRightClickDescription() {
        return "";
    }

    @Override
    public String getLeftClickDescription() {
        return "Give the player speed III for 15 seconds.";
    }

    @Override
    public void onLeftClick(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*15, 2));
        Bukkit.getScheduler().runTaskLater(CrystalsPlugin.getInstance(), () -> {
            applyEffect(player);
        }, 20*15);
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
        return "Speed Crystal";
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
        return 0;
    }

    @Override
    public long getLeftClickCooldown() {
        return 45;
    }



    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000000, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100000000, 0));
    }
}
