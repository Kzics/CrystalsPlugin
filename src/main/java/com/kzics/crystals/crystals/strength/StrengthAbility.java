package com.kzics.crystals.crystals.strength;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class StrengthAbility extends Ability {

    private final HashMap<UUID, Integer> attackLevel = new HashMap<>();
    @Override
    public void onEat(PlayerItemConsumeEvent event) {

    }

    @Override
    public void onRightClick(Player player) {

        Player target = (Player) player.getNearbyEntities(5,5,5)
                .stream()
                .filter(entity -> entity instanceof Player)
                .findAny()
                .orElse(null);

        if(target != null) {
            for (PotionEffect potionEffect : target.getActivePotionEffects()) {
                target.removePotionEffect(potionEffect.getType());
            }
        }
    }

    @Override
    public void onLeftClick(Player player) {

    }

    @Override
    public void onKill(Player player) {
        player.setHealth(player.getHealth() + 4);

    }

    @Override
    public void onDamaged(Player player) {

    }

    @Override
    public boolean onDamage(Player player) {
        if(attackLevel.containsKey(player.getUniqueId())) {
            if(attackLevel.get(player.getUniqueId()) < 4) {
                attackLevel.put(player.getUniqueId(), attackLevel.get(player.getUniqueId()) + 1);
                return false;
            }else{
                return true;
            }
        } else {
            attackLevel.put(player.getUniqueId(), 1);
        }
        return false;
    }

    @Override
    public void onBreak(Player player) {

    }

    @Override
    public long getCooldown() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setCooldown(long cooldown) {

    }

    @Override
    public void playAbility(Player player) {

    }

    @Override
    public long getRightClickCooldown() {
        return 0;
    }

    @Override
    public long getLeftClickCooldown() {
        return 0;
    }

    @Override
    public String getRightClickDescription() {
        return "Removes all potion effects from the player in a 5 block radius.";
    }

    @Override
    public String getLeftClickDescription() {
        return "Gives strength to the player.";
    }

    @Override
    public void applyEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 1));
    }
}
