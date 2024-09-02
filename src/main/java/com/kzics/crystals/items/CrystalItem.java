package com.kzics.crystals.items;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.obj.ColorsUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class CrystalItem extends ItemStack {
    public static NamespacedKey CRYSTAL_TYPE_KEY = new NamespacedKey(CrystalsPlugin.getInstance(), "crystal-type");

    private final CrystalType type;

    public CrystalItem(CrystalType type) {
        super(Material.PRISMARINE_CRYSTALS);

        this.type = type;

        Ability ability = CrystalsPlugin.getInstance().getCrystalsManager().getAbilities(type);

        ItemMeta meta = getItemMeta();

        // Display name with gradient
        String displayName = ColorsUtil.gradientText(ability.getName(), "#FFCC00", "#FF9900");
        meta.setDisplayName(displayName);

        // Lore with a simplified color scheme
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + ability.getDescription());
        lore.add(ChatColor.of("#66CC66") + "Abilities:");
        lore.add(ChatColor.of("#66CC66") + "Right Click: " + ChatColor.YELLOW + ability.getRightClickDescription());
        lore.add(ChatColor.of("#66CC66") + "Left Click: " + ChatColor.YELLOW + ability.getLeftClickDescription());
        lore.add("");
        lore.add(ChatColor.of("#66CC66") + "Cooldowns:");
        lore.add(ChatColor.of("#66CC66") + "Right Click Cooldown: " + ChatColor.YELLOW + ability.getRightClickCooldown() + "s");
        lore.add(ChatColor.of("#66CC66") + "Left Click Cooldown: " + ChatColor.YELLOW + ability.getLeftClickCooldown() + "s");

        meta.setLore(lore);
        meta.getPersistentDataContainer().set(CRYSTAL_TYPE_KEY, org.bukkit.persistence.PersistentDataType.STRING, type.name());
        setItemMeta(meta);
    }
}
