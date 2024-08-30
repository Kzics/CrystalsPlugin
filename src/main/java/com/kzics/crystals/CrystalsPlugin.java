package com.kzics.crystals;

import com.kzics.crystals.commands.crystal.CrystalCommand;
import com.kzics.crystals.data.EnergyManager;
import com.kzics.crystals.listeners.PlayerListeners;
import com.kzics.crystals.recipes.CrystalRerollRecipe;
import com.kzics.crystals.recipes.EnergyBottleRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class CrystalsPlugin extends JavaPlugin {

    private static CrystalsPlugin instance;
    private CrystalsManager crystalsManager;
    private EnergyManager energyManager;


    @Override
    public void onEnable() {
        instance = this;
        this.energyManager = new EnergyManager();
        this.crystalsManager = new CrystalsManager();
        new CrystalsRunnable(crystalsManager).runTaskTimer(this, 0, 20);
        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
        getLogger().info("Crystals plugin enabled");

        CrystalRerollRecipe.register();
        EnergyBottleRecipe.register();

        getCommand("crystal").setExecutor(new CrystalCommand());
        getCommand("energy").setExecutor(new com.kzics.crystals.commands.energy.EnergyCommand(this));

        getCommand("crystal").setTabCompleter(new com.kzics.crystals.commands.crystal.CrystalCommand());
    }

    public static CrystalsPlugin getInstance() {
        return instance;
    }

    public CrystalsManager getCrystalsManager() {
        return crystalsManager;
    }


    public EnergyManager getEnergyManager() {
        return energyManager;
    }
}
