package com.kzics.crystals.commands.energy;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.commands.CommandBase;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnergyCommand extends CommandBase {


    private final CrystalsPlugin plugin;
    public EnergyCommand(CrystalsPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)){
            return false;
        }

        int energyAmount = plugin.getEnergyManager().getEnergy(player.getUniqueId());

        sender.sendMessage(ColorsUtil.translate.apply("&aYou have " + energyAmount + " energy"));

        return false;
    }
}
