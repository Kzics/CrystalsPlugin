package com.kzics.crystals.commands.recipe;

import com.kzics.crystals.commands.CommandBase;
import com.kzics.crystals.menu.RecipeMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RecipesCommand extends CommandBase {

    public RecipesCommand(){

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 0){
            sender.sendMessage("Usage: /recipes");
            return true;

        }

        if(!(sender instanceof Player player)) return false;
        new RecipeMenu().open(player);

        return true;
    }
}
