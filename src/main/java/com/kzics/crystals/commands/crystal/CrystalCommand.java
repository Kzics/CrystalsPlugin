package com.kzics.crystals.commands.crystal;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.commands.CommandBase;
import com.kzics.crystals.commands.crystal.sub.GiveCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class CrystalCommand extends CommandBase implements TabCompleter {


    public CrystalCommand(CrystalsPlugin plugin){
        registerSubCommand("give",new GiveCommand(plugin));
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 1){
            return null;
        }

        return List.of("give");
    }
}
