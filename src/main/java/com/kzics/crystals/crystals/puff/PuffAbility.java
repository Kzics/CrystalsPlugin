package com.kzics.crystals.crystals.puff;

import com.kzics.crystals.crystals.Ability;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.util.Vector;

public class PuffAbility extends Ability {
    private long rightClickCooldown = 60;
    private long leftClickCooldown = 60;

    @Override
    public void onEat(PlayerItemConsumeEvent event) {
    }

    @Override
    public String getRightClickDescription() {
        return "Pushes any enemies within 10 block radius 30 blocks away ";
    }

    @Override
    public String getLeftClickDescription() {
        return "Allows you to dash 5 blocks forward into the direction you're looking and deals 2 hearts of damage to any player in the way regardless of protection ";
    }

    @Override
    public void onRightClick(Player player) {

        player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (entity instanceof Player) {
                Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(30));
            }
        });
    }

    @Override
    public void onLeftClick(Player player) {

        Vector direction = player.getLocation().getDirection().normalize();
        player.setVelocity(direction.multiply(5));
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (entity instanceof Player) {
                ((Player) entity).damage(4); // 2 hearts of damage
            }
        });
    }

    @Override
    public void onKill(Player player) {
        // Implementation here
    }

    @Override
    public void onDamaged(Player player) {
        // Implementation here
    }

    @Override
    public void onBreak(Player player) {
        // Implementation here
    }

    @Override
    public boolean onDamage(Player player) {
        return false;
    }

    @Override
    public long getCooldown() {
        return 0;
    }

    @Override
    public String getName() {
        return "PuffAbility";
    }

    @Override
    public String getDescription() {
        return "An ability that allows you to push enemies away and dash forward.";
    }

    @Override
    public void setCooldown(long cooldown) {
        // Implementation here
    }

    @Override
    public void playAbility(Player player) {
        // Implementation here
    }

    @Override
    public long getRightClickCooldown() {
        return rightClickCooldown;
    }

    @Override
    public long getLeftClickCooldown() {
        return leftClickCooldown;
    }


    @Override
    public void applyEffect(Player player) {
        // Implementation here
    }

    public void setRightClickCooldown(long cooldown) {
        this.rightClickCooldown = cooldown;
    }

    public void setLeftClickCooldown(long cooldown) {
        this.leftClickCooldown = cooldown;
    }
}
