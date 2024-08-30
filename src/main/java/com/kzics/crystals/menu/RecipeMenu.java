package com.kzics.crystals.menu;

import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.items.CrystalRerollItem;
import com.kzics.crystals.items.EnergyBottleItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RecipeMenu {


    private final Inventory inventory;
    public RecipeMenu(){
        this.inventory = Bukkit.createInventory(null, 9, "Recipe Menu");
    }

    public void open(Player player){

        inventory.setItem(3, new CrystalRerollItem());
        inventory.setItem(5, new EnergyBottleItem());

        player.openInventory(inventory);
    }

}
