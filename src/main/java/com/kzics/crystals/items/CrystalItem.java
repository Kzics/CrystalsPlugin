package com.kzics.crystals.items;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

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


        meta.setDisplayName(ColorsUtil.translate.apply("&6" + ability.getName()));

        List<String> lore = new ArrayList<>();
        lore.add(ColorsUtil.translate.apply("&7" + ability.getDescription()));
        lore.add("");
        lore.add(ColorsUtil.translate.apply("&eAbilities:"));
        lore.add(ColorsUtil.translate.apply("&aRight Click: &f" + ability.getRightClickDescription()));
        lore.add(ColorsUtil.translate.apply("&aLeft Click: &f" + ability.getLeftClickDescription()));
        lore.add("");
        lore.add(ColorsUtil.translate.apply("&eCooldowns:"));
        lore.add(ColorsUtil.translate.apply("&aRight Click Cooldown: &f" + ability.getRightClickCooldown() + "s"));
        lore.add(ColorsUtil.translate.apply("&aLeft Click Cooldown: &f" + ability.getLeftClickCooldown() + "s"));

        meta.setLore(lore);
        meta.getPersistentDataContainer().set(CRYSTAL_TYPE_KEY, org.bukkit.persistence.PersistentDataType.STRING, type.name());
        setItemMeta(meta);
    }
}
