package com.heesesmp.moveentity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntitySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityStorage {
    private Map<UUID, EntitySnapshot> storage;
    public EntityStorage() {
        this.storage = new HashMap<>();
    }
    public boolean paste(UUID uuid, Location location, int amount) {
        if(storage.containsKey(uuid)) {
            for(int i = 0; i < amount; i++) {
                storage.get(uuid).createEntity(location);
            }
            return true;
        }
        return false;
    }
    public void copy(UUID player, Entity entity) {
        storage.put(player, entity.createSnapshot());
    }
}
