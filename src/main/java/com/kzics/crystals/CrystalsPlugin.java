package com.kzics.crystals;

import org.bukkit.plugin.java.JavaPlugin;

public class CrystalsPlugin extends JavaPlugin {

    private static CrystalsPlugin instance;
    private CrystalsManager crystalsManager;

    @Override
    public void onEnable() {
        instance = this;
        this.crystalsManager = new CrystalsManager();
        getLogger().info("Crystals plugin enabled");
    }

    public static CrystalsPlugin getInstance() {
        return instance;
    }

    public CrystalsManager getCrystalsManager() {
        return crystalsManager;
    }
}
