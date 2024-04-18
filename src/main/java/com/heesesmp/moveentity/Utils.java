package com.heesesmp.moveentity;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static NamespacedKey COPY_WAND = new NamespacedKey(MoveEntity.getInstance(), "wand");
    public static boolean isCopyStick(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(COPY_WAND, PersistentDataType.BOOLEAN);
    }
    public static ItemStack getCopyStick() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW+"MoveEntity Copy Stick");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"Left-Click on a mob to copy it to your clipboard");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(COPY_WAND, PersistentDataType.BOOLEAN, true);
        stick.setItemMeta(meta);
        return stick;
    }
}
