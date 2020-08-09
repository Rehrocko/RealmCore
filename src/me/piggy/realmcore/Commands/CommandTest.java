package me.piggy.realmcore.Commands;

import me.piggy.realmcore.Utility.Attribute;
import me.piggy.realmcore.Utility.RealmItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class CommandTest implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player.");
            return true;
        }
        Player player = (Player) sender;
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getType() == Material.AIR) {
            player.sendMessage("You cannot hold air.");
        }
        RealmItem realmItem = new RealmItem(itemStack);
        if (args.length == 0) {
            player.sendMessage("-- Beginning of List --");
            for (Map.Entry entry : realmItem.getAttributes().entrySet()) {
                player.sendMessage(entry.getKey() + " : " + entry.getValue());
            }
            player.sendMessage("-- End of List --");
        } else if(args.length == 3 && args[0].equalsIgnoreCase("add")) {
            realmItem.addAttribute(Attribute.valueOf(args[1].toUpperCase()), Integer.parseInt(args[2]));
        } else if(args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            realmItem.removeAttribute(Attribute.valueOf(args[1].toUpperCase()));
        }
        realmItem.updateLore();
        player.setItemInHand(realmItem.getItem());
        return true;
    }
}
