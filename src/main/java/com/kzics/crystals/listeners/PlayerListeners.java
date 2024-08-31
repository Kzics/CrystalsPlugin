package com.kzics.crystals.listeners;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.crystals.astral.AstralAbility;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.items.CrystalRerollItem;
import com.kzics.crystals.items.EnergyBottleItem;
import com.kzics.crystals.menu.CrystalRerollMenu;
import com.kzics.crystals.menu.EnergyBottleMenu;
import com.kzics.crystals.obj.ColorsUtil;
import com.kzics.crystals.utils.ArmorEquipEvent;
import com.kzics.crystals.utils.ArmorUnequipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PlayerListeners implements Listener {

    private final CrystalsPlugin plugin;
    public PlayerListeners(CrystalsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        CrystalType type = hasCrystal(player);
        if (type == CrystalType.FIRE) {
            Material blockType = player.getLocation().getBlock().getType();

            if (blockType == Material.FIRE || blockType == Material.SOUL_FIRE || blockType == Material.MAGMA_BLOCK) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1, true, false, false));
            } else {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }else if(type == CrystalType.SPEED){
            Material blockType = player.getLocation().getBlock().getType();

            if(blockType == Material.SOUL_SAND){
                player.setWalkSpeed(0.1f);
            }
        }
    }

    @EventHandler
    public void onArmorUnequip(ArmorUnequipEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        EquipmentSlot slot = event.getArmorSlot();

        CrystalType type = hasCrystal(player);

        System.out.println("UNEQUIP");

        if (slot == EquipmentSlot.FEET && type == CrystalType.SPEED) {
            if (item != null && item.getType().name().contains("BOOTS")) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.removeEnchant(Enchantment.SOUL_SPEED);
                    item.setItemMeta(meta);
                }
            }
        }
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        EquipmentSlot slot = event.getArmorSlot();

        System.out.println("EQUIP");
        CrystalType type = hasCrystal(player);
        if (slot == EquipmentSlot.FEET && type == CrystalType.SPEED) {
            if (item != null && item.getType().name().contains("BOOTS")) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
                    item.setItemMeta(meta);
                }
            }
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();

        if(!plugin.getEnergyManager().hasEnergy(player.getUniqueId())){
            plugin.getEnergyManager().addEnergy(player.getUniqueId(), 5);
        }
        CrystalType type = hasCrystal(player);
        if(type != null){
            Ability ability = plugin.getCrystalsManager().getAbilities(type);
            ability.applyEffect(player);
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

        if(plugin.getDisabledCrystals().contains(player.getUniqueId())) {
            player.sendMessage(ColorsUtil.translate.apply("&cYou can't use crystals right now!"));
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
            if(item.getAmount() > 1){
                item.setAmount(item.getAmount() - 1);
            }else{
                player.getInventory().remove(item);
            }


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

        if(event.getView().getTitle().equalsIgnoreCase("Energy Bottle")
        || event.getView().getTitle().equalsIgnoreCase("Crystal Reroll")){
            event.setCancelled(true);
        }

        if(event.getView().getTitle().equalsIgnoreCase("Recipe Menu")){
            event.setCancelled(true);

            if(event.getCurrentItem() == null) return;

            if(event.getCurrentItem().getItemMeta() == null) return;
            final ItemStack item = event.getCurrentItem();

            if(item.getItemMeta().getPersistentDataContainer().has(CrystalRerollItem.CRYSTAL_REROLL_KEY, PersistentDataType.STRING)){
                new CrystalRerollMenu().open((Player) event.getWhoClicked());
            }else{
                new EnergyBottleMenu().open((Player) event.getWhoClicked());
            }
        }
    }


    @EventHandler
    public void onMine(BlockBreakEvent event){
        if(event.getBlock().getType().name().contains("ORE")){
            CrystalType type = hasCrystal(event.getPlayer());
            if(type == CrystalType.WEALTH){
                for (ItemStack drop : event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand())) {
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop.clone());
                }
            }else if(type == CrystalType.FIRE){
                event.setDropItems(false);
                for (ItemStack drop : event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand())) {
                    if(drop != null)
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(getSmeltedItem(drop.getType()), drop.getAmount()));
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.getKiller() != null) {
            Player killer = entity.getKiller();

            if (hasCrystal(killer) == CrystalType.WEALTH) {
                for (ItemStack drop : event.getDrops()) {
                    entity.getWorld().dropItemNaturally(entity.getLocation(), drop.clone());
                }
            }
        }
    }

    private Material getSmeltedItem(Material material) {
        switch (material) {
            case RAW_IRON:
                return Material.IRON_INGOT;
            case RAW_GOLD:
                return Material.GOLD_INGOT;
            case RAW_COPPER:
            case DEEPSLATE_COPPER_ORE:
                return Material.COPPER_INGOT;
            case ANCIENT_DEBRIS:
                return Material.NETHERITE_SCRAP;
            default:
                return null;
        }
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damagerEntity = event.getDamager();
        Entity victimEntity = event.getEntity();

        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && victimEntity instanceof Player) {
            if(hasCrystal((Player) victimEntity) == CrystalType.PUFF) {
                event.setCancelled(true);
            }
            return;
        }

        if (damagerEntity instanceof Player damager && victimEntity instanceof Player victim) {
            handlePlayerVsPlayerDamage(event, damager, victim);
        } else if (damagerEntity instanceof Player damager && victimEntity instanceof Monster) {
            handlePlayerVsMonsterDamage(event, damager);

        } else if (damagerEntity instanceof Arrow arrow && arrow.getShooter() instanceof Player shooter && victimEntity instanceof Player victim) {
            handleArrowDamage(event, arrow, shooter, victim);
        }
    }

    private void handlePlayerVsPlayerDamage(EntityDamageByEntityEvent event, Player damager, Player victim) {
        Ability ability = plugin.getCrystalsManager().getAbilities(hasCrystal(damager));
        if (ability == null) return;

        ability.onDamaged(victim);

        if (ability.onDamage(damager)) {
            event.setDamage(event.getDamage() * 2);
            damager.playSound(damager.getLocation(), Sound.BLOCK_ANVIL_BREAK, 5f, 5f);
        }

        if (victim.getHealth() - event.getFinalDamage() <= 0) {
            ability.onKill(damager);
            if(plugin.getEnergyManager().getEnergy(victim.getUniqueId()) > 0 ) {
                plugin.getEnergyManager().addEnergy(damager.getUniqueId(), 1);
                plugin.getEnergyManager().removeEnergy(victim.getUniqueId(), 1);
            }
        }
    }

    private void handlePlayerVsMonsterDamage(EntityDamageByEntityEvent event, Player damager) {
        CrystalType type = hasCrystal(damager);
        Ability ability = plugin.getCrystalsManager().getAbilities(type);
        if (ability == null) return;

        if (type == CrystalType.LIFE) {
            event.setDamage(event.getDamage() * 3);
        }
    }

    private void handleArrowDamage(EntityDamageByEntityEvent event, Arrow arrow, Player shooter, Player victim) {
        PersistentDataContainer dataContainer = arrow.getPersistentDataContainer();
        if (dataContainer.has(AstralAbility.key, PersistentDataType.STRING)) {
            plugin.getDisabledCrystals().add(victim.getUniqueId());

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                plugin.getDisabledCrystals().remove(shooter.getUniqueId());
            }, 20 * 10L);
        }

        Ability ability = plugin.getCrystalsManager().getAbilities(hasCrystal(shooter));
        if (ability == null) return;

        if (ability.onDamage(shooter)) {
            event.setDamage(event.getDamage() * 2);
            shooter.playSound(shooter.getLocation(), Sound.BLOCK_ANVIL_BREAK, 5f, 5f);
        }

        if (victim.getHealth() <= 0) {
            ability.onKill(shooter);
            plugin.getEnergyManager().addEnergy(shooter.getUniqueId(), 1);
            plugin.getEnergyManager().removeEnergy(victim.getUniqueId(), 1);
        }
    }
}
