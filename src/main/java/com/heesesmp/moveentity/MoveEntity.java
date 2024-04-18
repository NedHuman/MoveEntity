package com.heesesmp.moveentity;

import com.heesesmp.moveentity.command.MoveEntityCommand;
import com.heesesmp.moveentity.listener.EntityDamageByEntityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoveEntity extends JavaPlugin {
    private static MoveEntity instance;
    private EntityStorage entityStorage;

    @Override
    public void onEnable() {
        instance = this;
        entityStorage = new EntityStorage();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getCommand("moveentity").setExecutor(new MoveEntityCommand());
        getCommand("mve").setExecutor(new MoveEntityCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static MoveEntity getInstance() {
        return instance;
    }
    public EntityStorage getEntityStorage() {
        return entityStorage;
    }
}
