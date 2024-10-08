package com.kzics.crystals.menu;



import com.kzics.crystals.items.EnergyBottleItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnergyBottleMenu {

    private final Inventory inventory;
    public EnergyBottleMenu(){
        this.inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, "Energy Bottle");
    }

    public void open(Player player){
        inventory.setItem(0, new EnergyBottleItem());

        inventory.setItem(1, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(3, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(7, new ItemStack(Material.NETHERITE_BLOCK));
        inventory.setItem(9, new ItemStack(Material.NETHERITE_BLOCK));

        inventory.setItem(2, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(4, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(6, new ItemStack(Material.DIAMOND_BLOCK));
        inventory.setItem(8, new ItemStack(Material.DIAMOND_BLOCK));

        inventory.setItem(5, new ItemStack(Material.GOLD_BLOCK));

        player.openInventory(inventory);

    }
}
