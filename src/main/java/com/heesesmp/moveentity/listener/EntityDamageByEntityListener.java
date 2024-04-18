package com.heesesmp.moveentity.listener;

import com.heesesmp.moveentity.MoveEntity;
import com.heesesmp.moveentity.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player player) {
            if(player.hasPermission("moveentity.use")) {
                if(Utils.isCopyStick(player.getInventory().getItemInMainHand())) {
                    event.setCancelled(true);
                    MoveEntity.getInstance().getEntityStorage().copy(player.getUniqueId(), event.getEntity());
                    player.sendMessage(ChatColor.YELLOW+"Entity copied, use /mve paste to paste it in a new location");
                }
            }
        }
    }
}
