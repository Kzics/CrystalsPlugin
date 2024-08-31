package com.kzics.crystals.commands.crystal.sub;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.commands.ICommand;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCommand implements ICommand {

    private final CrystalsPlugin plugin;
    public GiveCommand(CrystalsPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getPermission() {
        return "crystals.give";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("Usage: /crystals give <player> <crystal>");
            return;
        }

        String playerName = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if(player == null){
            sender.sendMessage("Player not found.");
            return;
        }

        CrystalType crystalType = CrystalType.valueOf(args[2].toUpperCase());

        player.getInventory().addItem(new CrystalItem(crystalType));

        Ability ability = plugin.getCrystalsManager().getAbilities(crystalType);
        ability.applyEffect(player);
    }
}
