package com.kzics.crystals.commands.crystal.sub;

import com.kzics.crystals.CrystalsPlugin;
import com.kzics.crystals.commands.ICommand;
import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.enums.CrystalType;
import com.kzics.crystals.items.CrystalItem;
import com.kzics.crystals.obj.ColorsUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

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
            sender.sendMessage(ColorsUtil.translate.apply("&cUsage: /crystals give <player> <crystal>"));
            return;
        }
        if(!sender.isOp()) return;

        String playerName = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if(player == null){
            sender.sendMessage(ColorsUtil.translate.apply("&cPlayer not found."));
            return;
        }

        CrystalType crystalType = CrystalType.valueOf(args[2].toUpperCase());

        player.getInventory().addItem(new CrystalItem(crystalType));

        for (PotionEffectType effect : PotionEffectType.values()) {
            player.removePotionEffect(effect);
        }
        Ability ability = plugin.getCrystalsManager().getAbilities(crystalType);
        ability.applyEffect(player);
    }
}
