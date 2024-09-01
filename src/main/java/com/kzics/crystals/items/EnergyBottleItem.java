package com.kzics.crystals.items;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import java.util.List;

public class EnergyBottleItem extends ItemStack {

    public static NamespacedKey ENERGY_BOTTLE_KEY = new NamespacedKey(CrystalsPlugin.getInstance(), "energy-bottle");

    public EnergyBottleItem() {
        super(Material.LARGE_AMETHYST_BUD);

        ItemMeta meta = this.getItemMeta();

        // Nom avec gradient et contraste fort
        meta.setDisplayName(ColorsUtil.gradientText("Energy Bottle", "#4dff36", "#4dff36"));

        // Ajout d'un espace entre le nom et le lore
        meta.setLore(List.of(
                "",
                ColorsUtil.gradientText("Use this to earn 1 energy point.", "#32CD32", "#00FF00")
        ));

        meta.getPersistentDataContainer().set(ENERGY_BOTTLE_KEY, PersistentDataType.STRING, "energy-bottle");
        this.setItemMeta(meta);
    }
}
