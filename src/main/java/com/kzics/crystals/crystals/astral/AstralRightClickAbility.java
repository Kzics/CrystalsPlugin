package com.kzics.crystals.crystals.astral;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AstralRightClickAbility extends Ability {

    @Override
    public boolean activate(Player player) {
        if(super.activate(player)){
            this.playAbility(player);

            return true;
        }
        return false;
    }

    @Override
    public long getCooldown() {
        return 45;
    }

    @Override
    public String getName() {
        return "Glowing Astral";
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
        Location playerLocation = player.getLocation();
        int radius = 150;

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.getWorld().equals(player.getWorld()) && target.getLocation().distance(playerLocation) <= radius) {
                target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0));
            }
        }
    }
}
