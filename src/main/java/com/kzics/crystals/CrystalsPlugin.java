package com.kzics.crystals;

import com.kzics.crystals.commands.crystal.CrystalCommand;
import com.kzics.crystals.commands.recipe.RecipesCommand;
import com.kzics.crystals.data.EnergyManager;
import com.kzics.crystals.listeners.PlayerListeners;
import com.kzics.crystals.recipes.CrystalRerollRecipe;
import com.kzics.crystals.recipes.EnergyBottleRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrystalsPlugin extends JavaPlugin {

    private static CrystalsPlugin instance;
    private CrystalsManager crystalsManager;
    private EnergyManager energyManager;
    private List<UUID> disabledCrystals;


    @Override
    public void onEnable() {
        instance = this;
        this.disabledCrystals = new ArrayList<>();
        this.energyManager = new EnergyManager();
        this.crystalsManager = new CrystalsManager();
        new CrystalsRunnable(crystalsManager).runTaskTimer(this, 0, 20);
        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
        getLogger().info("Crystals plugin enabled");

        CrystalRerollRecipe.register();
        EnergyBottleRecipe.register();

        getCommand("crystal").setExecutor(new CrystalCommand(this));
        getCommand("recipe").setExecutor(new RecipesCommand());
        getCommand("energy").setExecutor(new com.kzics.crystals.commands.energy.EnergyCommand(this));

        getCommand("crystal").setTabCompleter(new com.kzics.crystals.commands.crystal.CrystalCommand(this));
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

    public List<UUID> getDisabledCrystals() {
        return disabledCrystals;
    }
}
