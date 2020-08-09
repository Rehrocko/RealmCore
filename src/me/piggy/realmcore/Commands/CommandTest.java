package me.piggy.realmcore.Commands;

import me.piggy.realmcore.Utility.RealmItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if(player.getItemInHand().getType().equals(Material.AIR)) {
            return true;
        }
        RealmItem realmItem = new RealmItem(player.getItemInHand());
        player.sendMessage(realmItem.getId() + " " + realmItem.getItem().getType());

        return true;
    }
}
