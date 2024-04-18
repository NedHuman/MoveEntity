package com.heesesmp.moveentity.command;

import com.heesesmp.moveentity.MoveEntity;
import com.heesesmp.moveentity.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class MoveEntityCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof  Player player) {
            if (sender.hasPermission("moveentity.use")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.YELLOW+"Usage: /mve copy | paste <amount>");
                }else {
                    if(args[0].equalsIgnoreCase("copy")) {
                        sender.sendMessage(ChatColor.YELLOW + "You have received a copy stick");
                        player.getInventory().addItem(Utils.getCopyStick());
                    }else if(args[0].equalsIgnoreCase("reload")) {
                        sender.sendMessage(ChatColor.YELLOW+"Config reloaded");
                        MoveEntity.getInstance().reloadConfig();
                    }else if(args[0].equalsIgnoreCase("paste")) {
                        if(args.length == 1) {
                            if(MoveEntity.getInstance().getEntityStorage().paste(player.getUniqueId(), player.getLocation(), 1)){
                                player.sendMessage(ChatColor.YELLOW+"Clipboard pasted");
                            }else{
                                player.sendMessage(ChatColor.RED + "No selection found");
                            }
                            return true;
                        }else{
                            try {
                                int amount = Integer.parseInt(args[1]);
                                if(amount < 1) {
                                    sender.sendMessage(ChatColor.RED+"Error while parsing number");
                                    return true;
                                }
                                if(amount > MoveEntity.getInstance().getConfig().getInt("paste-limit")) {
                                    sender.sendMessage(ChatColor.RED+"Number cannot be higher than paste limit ("+MoveEntity.getInstance().getConfig().getInt("paste-limit")+")");
                                    return true;
                                }
                                if(MoveEntity.getInstance().getEntityStorage().paste(player.getUniqueId(), player.getLocation(), amount)){
                                    player.sendMessage(ChatColor.YELLOW+"Clipboard pasted");
                                }else {
                                    player.sendMessage(ChatColor.RED + "No selection found");
                                }
                                return true;
                            }catch(NumberFormatException e) {
                                sender.sendMessage(ChatColor.RED+"Error while parsing number");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completion = new ArrayList<>();
        completion.add("copy");
        completion.add("paste");
        completion.add("reload");
        return completion;
    }
}
