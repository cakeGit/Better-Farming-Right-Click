package com.cak.bfrc.platform.event;

import com.cak.bfrc.core.EventHandlers;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GameEvents {
    
    @SubscribeEvent
    public void onInteractionKeyMappingTriggered(InputEvent.InteractionKeyMappingTriggered events) {
        EventHandlers.lastUsedInteractionHand = events.getHand();
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        EventHandlers.tickNoCooldownInteractions();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EventHandlers.tryRightClickHarvest(event.getLevel(), event.getItemStack(), event.getPos(), event.getHitVec().getDirection());
    }
    
}
