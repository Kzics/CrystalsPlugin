package com.kzics.crystals.items;

import com.kzics.crystals.CrystalsPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrystalRerollItem extends ItemStack {

    public static NamespacedKey CRYSTAL_REROLL_KEY = new NamespacedKey(CrystalsPlugin.getInstance(), "crystal-reroll");

    public CrystalRerollItem(){
        super(Material.AMETHYST_SHARD);

        ItemMeta meta = getItemMeta();

        meta.getPersistentDataContainer().set(CRYSTAL_REROLL_KEY, org.bukkit.persistence.PersistentDataType.STRING, "crystal-reroll");
        
        meta.setDisplayName("§5Crystal Reroll");

        setItemMeta(meta);
    }
}
