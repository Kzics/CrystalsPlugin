package com.kzics.crystals.recipes;

import com.kzics.crystals.items.EnergyBottleItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class EnergyBottleRecipe extends ShapedRecipe {
    public EnergyBottleRecipe() {
        super(EnergyBottleItem.ENERGY_BOTTLE_KEY, new EnergyBottleItem());

        this.shape(
                "NDN",
                "DGD",
                "NDN");

        this.setIngredient('N', Material.NETHERITE_INGOT);
        this.setIngredient('D', Material.DIAMOND_BLOCK);
        this.setIngredient('G', Material.GOLD_BLOCK);

    }

    public static void register(){
        Bukkit.getServer().addRecipe(new EnergyBottleRecipe());
    }
}
