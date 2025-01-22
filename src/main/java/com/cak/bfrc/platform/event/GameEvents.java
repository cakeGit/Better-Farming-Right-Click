package com.cak.bfrc.platform.event;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.EnabledState;
import com.cak.bfrc.core.EventHandlers;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GameEvents {
    
    @SubscribeEvent
    public void onInteractionKeyMappingTriggered(InputEvent.InteractionKeyMappingTriggered events) {
        if (events.isUseItem())
            EventHandlers.lastInteractionHand = events.getHand();
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        if (BFRC.CURRENT_STATE == EnabledState.ENABLED)
            EventHandlers.tickNoCooldownInteractions();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (BFRC.CURRENT_STATE == EnabledState.ENABLED)
            EventHandlers.tryRightClickHarvest(event.getLevel(), event.getItemStack(), event.getPos(), event.getHitVec().getDirection());
    }
    
}
