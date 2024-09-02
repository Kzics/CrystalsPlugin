package com.kzics.crystals.menu;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CrystalSpinMenu {

    private final CrystalsPlugin instance;
    private final Inventory inventory;
    private final List<ItemStack> rodItems;

    public CrystalSpinMenu(CrystalsPlugin instance) {
        this.instance = instance;
        this.inventory = Bukkit.createInventory(null, 9, "Crystal Spin Menu");
        this.rodItems = new ArrayList<>();

        initializeRodItems();

        Collections.shuffle(rodItems, new Random(System.currentTimeMillis()));
    }

    private void initializeRodItems() {
        rodItems.add(new CrystalItem(CrystalType.ASTRAL));
        rodItems.add(new CrystalItem(CrystalType.SPEED));
        rodItems.add(new CrystalItem(CrystalType.STRENGTH));
        rodItems.add(new CrystalItem(CrystalType.LIFE));
        rodItems.add(new CrystalItem(CrystalType.WEALTH));
        rodItems.add(new CrystalItem(CrystalType.FIRE));
        rodItems.add(new CrystalItem(CrystalType.PUFF));
    }

    public void open(Player player) {
        // Fill the inventory with 9 items randomly selected from rodItems
        List<ItemStack> itemsToDisplay = new ArrayList<>();
        Random random = new Random();

        // Repeat items to ensure we have at least 9 items
        while (itemsToDisplay.size() < 9) {
            itemsToDisplay.add(rodItems.get(random.nextInt(rodItems.size())).clone());
        }

        Collections.shuffle(itemsToDisplay);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemsToDisplay.get(i));
        }

        player.openInventory(inventory);
        startSpinning(player);
    }

    private void startSpinning(Player player) {
        new BukkitRunnable() {
            int ticks = 0;
            int maxTicks = 40;

            @Override
            public void run() {
                if (ticks >= maxTicks) {
                    cancel();
                    ItemStack middleItem = inventory.getItem(4);

                    player.sendMessage(ColorsUtil.translate.apply("&aYou won a " + middleItem.getItemMeta().getDisplayName() + "!"));
                    for (ItemStack itemStack : player.getInventory().getContents()) {
                        if (itemStack != null && itemStack.getItemMeta() != null &&
                                itemStack.getItemMeta().getPersistentDataContainer().has(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING)) {

                            player.getInventory().remove(itemStack);

                            CrystalType crystalType = CrystalType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(CrystalItem.CRYSTAL_TYPE_KEY, PersistentDataType.STRING));
                            Ability ability = instance.getCrystalsManager().getAbilities(crystalType);
                            for (PotionEffectType effect : PotionEffectType.values()) {
                                player.removePotionEffect(effect);
                            }
                            ability.applyEffect(player);

                            Damageable middleMeta = (Damageable) middleItem.getItemMeta();
                            Damageable itemStackMeta = (Damageable) itemStack.getItemMeta();
                            middleMeta.setDamage(itemStackMeta.getDamage());

                            middleItem.setItemMeta((ItemMeta) middleMeta);
                            break;
                        }
                    }

                    player.getInventory().addItem(middleItem);
                    return;
                }

                ItemStack lastItem = inventory.getItem(inventory.getSize() - 1);
                for (int i = inventory.getSize() - 1; i > 0; i--) {
                    inventory.setItem(i, inventory.getItem(i - 1));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                }
                inventory.setItem(0, lastItem);

                ticks++;
            }
        }.runTaskTimer(instance, 0, 10); // Adjust delay for smoother/faster spin
    }
}
