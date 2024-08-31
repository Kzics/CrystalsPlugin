package com.kzics.crystals.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipEvent extends ArmorEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    protected ArmorEquipEvent(final Player who, final ItemStack item,
                              final EquipmentSlot slot, final ArmorAction action) {
        super(who, item, slot, action);
    }

    public ArmorEquipEvent(final Player who,  final ItemStack item,
                            final EquipmentSlot slot) {
        this(who, item, slot, ArmorAction.CUSTOM);
    }

    public ArmorEquipEvent( final Player who,  final ItemStack item) {
        this(who, item, item.getType().getEquipmentSlot());
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}