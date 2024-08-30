package com.kzics.crystals;

import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CrystalsRunnable extends BukkitRunnable {
    private CrystalsManager crystalsManager;

    public CrystalsRunnable(CrystalsManager crystalsManager) {
        this.crystalsManager = crystalsManager;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CrystalType crystalType = hasCrystal(player);
            Ability ability = crystalsManager.getAbilities(crystalType);

            long leftCooldown = ability.getRemainingCooldown(player, false);
            long rightCooldown = ability.getRemainingCooldown(player, true);

            String message = "§eLeft Ability Cooldown: ";
            if (leftCooldown <= 0) {
                message += "§a✅";
            } else {
                message += "§c"+leftCooldown + "s";
            }

            message += " §f| §eRight Cooldown: ";
            if (rightCooldown <= 0) {
                message += "§a✅";
            } else {
                message += "§c"+rightCooldown + "s";
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
        }
    }

    public CrystalType hasCrystal(Player player){
        for(ItemStack item : player.getInventory().getContents()){
            if(item.getItemMeta() == null){
                continue;
            }
            if(item.getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)){
                return CrystalType.valueOf(item.getItemMeta().getPersistentDataContainer().get(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING));
            }
        }
        return null;
    }


}
