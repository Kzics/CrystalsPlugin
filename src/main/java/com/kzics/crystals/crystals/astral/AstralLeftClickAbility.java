package com.kzics.crystals.crystals.astral;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AstralLeftClickAbility extends Ability {
    public static NamespacedKey arrow_key = new NamespacedKey(CrystalsPlugin.getInstance(), "astral_arrow");


    @Override
    public boolean activate(Player player) {
        if(super.activate(player)){
            this.playAbility(player);
            return true;
        }
        return false;
    }

    @Override
    public void playAbility(Player player) {
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

                arrow.getPersistentDataContainer().set(arrow_key, PersistentDataType.STRING, "a");

                arrowsThrown++;
            }
        }.runTaskTimer(CrystalsPlugin.getInstance(), 0, 10);
    }


    @Override
    public long getCooldown() {
        return 60;
    }

    @Override
    public String getName() {
        return "Astral Left Click";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setCooldown(long cooldown) {

    }
}
