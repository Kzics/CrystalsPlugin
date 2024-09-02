package com.kzics.crystals.items;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class CrystalRerollItem extends ItemStack {

    public static NamespacedKey CRYSTAL_REROLL_KEY = new NamespacedKey(CrystalsPlugin.getInstance(), "crystal-reroll");

    public CrystalRerollItem() {
        super(Material.AMETHYST_SHARD);

        ItemMeta meta = getItemMeta();
        meta.getPersistentDataContainer().set(CRYSTAL_REROLL_KEY, org.bukkit.persistence.PersistentDataType.STRING, "crystal-reroll");

        meta.setCustomModelData(1);
        // Nom avec gradient et contraste fort
        String displayName = ColorsUtil.gradientText("Crystal Reroll", "#FF00FF", "#0000FF");
        meta.setDisplayName(displayName);

        meta.setLore(Arrays.asList(
                "",
                ColorsUtil.gradientText("Use this item to reroll your crystal.", "#FF00FF", "#FF00CC")
        ));

        setItemMeta(meta);
    }
}
