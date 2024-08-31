package com.kzics.crystals.menu;

import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CrystalRerollMenu {


    private final Inventory inventory;
    public CrystalRerollMenu(){
        this.inventory = Bukkit.createInventory(null, InventoryType.CRAFTING,"Crystal Reroll");
    }


    public void open(Player player){
        inventory.setItem(5, new CrystalItem(CrystalType.ASTRAL));

        inventory.setItem(2, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(4, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(6, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(7, new ItemStack(Material.NETHERITE_BLOCK));

        inventory.setItem(1, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(3, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(7, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(9, new ItemStack(Material.DIAMOND_BLOCK));

        player.openInventory(inventory);
    }
}
