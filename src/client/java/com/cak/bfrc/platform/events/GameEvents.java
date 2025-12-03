package com.cak.bfrc.platform.events;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.EnabledState;
import com.cak.bfrc.core.EventHandlers;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class GameEvents {
    
    /**Not actually an event, manually triggered by mixin*/
    public static void onInteractionKeyMappingTriggered(Hand hand) {
        EventHandlers.lastInteractionHand = hand;
    }
    
    public static void registerEvents() {
        UseBlockCallback.EVENT.register(GameEvents::onRightClickBlock);
        ClientTickEvents.START_CLIENT_TICK.register(GameEvents::onClientTick);
    }
    
    private static void onClientTick(MinecraftClient minecraftClient) {
        if (BFRC.CURRENT_STATE == EnabledState.ENABLED)
            EventHandlers.tickNoCooldownInteractions();
    }
    private static ActionResult onRightClickBlock(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (BFRC.CURRENT_STATE == EnabledState.ENABLED)
            EventHandlers.tryRightClickHarvest(world, playerEntity.getStackInHand(hand), blockHitResult.getBlockPos(), blockHitResult.getSide());
        return ActionResult.PASS;
    }
    
}
