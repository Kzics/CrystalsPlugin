package com.kzics.crystals.recipes;

import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.items.CrystalRerollItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class CrystalRerollRecipe extends ShapedRecipe {
    public CrystalRerollRecipe() {
        super(CrystalRerollItem.CRYSTAL_REROLL_KEY, new CrystalRerollItem());

        this.shape(
                "DND",
                "NAN",
                "DND");
        List<ItemStack> crystalItems = new ArrayList<>();
        for (CrystalType type : CrystalType.values()) {
            crystalItems.add(new CrystalItem(type));
        }

        RecipeChoice.ExactChoice crystalChoice = new RecipeChoice.ExactChoice(crystalItems);
        this.setIngredient('A', crystalChoice);
        this.setIngredient('N', Material.NETHERITE_BLOCK);
        this.setIngredient('D', Material.DIAMOND_BLOCK);

    }

    public static void register(){
        Bukkit.getServer().addRecipe(new CrystalRerollRecipe());
    }
}
