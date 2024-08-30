package com.kzics.crystals.listeners;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.items.CrystalRerollItem;
import com.kzics.crystals.items.EnergyBottleItem;
import org.bukkit.Sound;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class PlayerListeners implements Listener {

    private final CrystalsPlugin plugin;
    public PlayerListeners(CrystalsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();

        if(!plugin.getEnergyManager().hasEnergy(player.getUniqueId())){
            plugin.getEnergyManager().addEnergy(player.getUniqueId(), 5);
        }

        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.ASTRAL));
        player.getInventory().addItem(new CrystalItem(CrystalType.LIFE));
        player.getInventory().addItem(new CrystalItem(CrystalType.STRENGTH));
        player.getInventory().addItem(new CrystalItem(CrystalType.PUFF));
        player.getInventory().addItem(new CrystalItem(CrystalType.SPEED));
        player.getInventory().addItem(new CrystalItem(CrystalType.WEALTH));
        player.getInventory().addItem(new CrystalItem(CrystalType.FIRE));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();

        if(event.getItemDrop().getItemStack().getItemMeta() == null){
            return;
        }
        if(event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)){
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for(ItemStack item : drops){
            if(item.getItemMeta() == null){
                continue;
            }
            if(item.getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)){
                event.getDrops().remove(item);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        final Player player = event.getPlayer();
        final ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getItemMeta() == null){
            return;
        }

        if(item.getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)){
            CrystalType type = CrystalType.valueOf(item.getItemMeta().getPersistentDataContainer().get(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING));
            Ability ability = CrystalsPlugin.getInstance().getCrystalsManager().getAbilities(type);

            if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                ability.activateRightClick(player);
            }else{
                ability.activateLeftClick(player);
            }
        } else if(item.getItemMeta().getPersistentDataContainer().has(EnergyBottleItem.ENERGY_BOTTLE_KEY, PersistentDataType.STRING)){
            plugin.getEnergyManager().addEnergy(player.getUniqueId(), 1);
            player.getInventory().remove(item);

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5f, 5f);
            player.sendMessage("§a+1 Energy");
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        Ability ability = CrystalsPlugin.getInstance().getCrystalsManager().getAbilities(hasCrystal(event.getPlayer()));

        if(ability == null) return;

        ability.onEat(event);
    }

    public CrystalType hasCrystal(Player player){
        for(ItemStack item : player.getInventory().getContents()){
            if(item.getItemMeta() == null){
                continue;
            }
            if(item.getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)){
                return CrystalType.valueOf(item.getItemMeta().getPersistentDataContainer().get(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING));
            }
        }
        return null;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        final Inventory inventory = event.getClickedInventory();

        if(event.getView().getTitle().equalsIgnoreCase("Recipe Menu")){
            event.setCancelled(true);

            if(event.getCurrentItem() == null) return;

            if(event.getCurrentItem().getItemMeta() == null) return;
            final ItemStack item = event.getCurrentItem();


            if(item.getItemMeta().getPersistentDataContainer().has(CrystalRerollItem.CRYSTAL_REROLL_KEY, PersistentDataType.STRING)){
                

            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player damager && event.getEntity() instanceof Player victim){
            Ability ability = CrystalsPlugin.getInstance().getCrystalsManager().getAbilities(hasCrystal(damager));
            if(ability == null) return;

            ability.onDamaged(victim);

            if(ability.onDamage(damager)){
                event.setDamage(event.getDamage() *2);
                damager.playSound(damager.getLocation(), Sound.BLOCK_ANVIL_BREAK,5f,5f);
            }
            if(victim.getHealth() <= 0){
                ability.onKill(damager);

                plugin.getEnergyManager().addEnergy(damager.getUniqueId(), 1);
                plugin.getEnergyManager().removeEnergy(victim.getUniqueId(), 1);
            }

        }else if(event.getDamager() instanceof Player damager && event.getEntity() instanceof Monster){

            CrystalType type = hasCrystal(damager);

            Ability ability = CrystalsPlugin.getInstance().getCrystalsManager().getAbilities(type);
            if(ability == null) return;

            if(type == CrystalType.LIFE) event.setDamage(event.getDamage() *3);
        }
    }

}
