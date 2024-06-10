package com.cak.bfrc.core;

import com.cak.bfrc.platform.MinecraftClientAccessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EventHandlers {
    
    public static void tryRightClickHarvest(World level, ItemStack stack, BlockPos pos, Direction hitVecDirection) {
        if (!BFRC.CONFIG_ACCESSOR.rightClickHarvestEnabled()) return;
        
        MinecraftClient mc = MinecraftClient.getInstance();
        
        if (!level.isClient) return;
        if (mc.interactionManager == null) return;
        
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;
        if (!(blockItem.getBlock() instanceof CropBlock heldCropBlock)) return;
        
        BlockState clickedState = level.getBlockState(pos);
        
        if (!(clickedState.getBlock() instanceof CropBlock cropBlock)) return;
        if (heldCropBlock != cropBlock) return;
        if (!cropBlock.isMature(clickedState)) return;
        
        mc.interactionManager.attackBlock(pos, hitVecDirection);
    }
    
    public static Hand lastInteractionHand = null;
    public static Hand lastUsedInteractionHand = Hand.MAIN_HAND;
    
    public static boolean noCooldownPeriodActive = false;
    public static Hand noCooldownPeriodHand = Hand.MAIN_HAND;
    
    public static void tickNoCooldownInteractions() {
        if (lastInteractionHand != null)
            lastUsedInteractionHand = lastInteractionHand;
        lastInteractionHand = null;
        
        if (!BFRC.CONFIG_ACCESSOR.noCooldownEnabled()) return;
        
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;
        if (!mc.world.isClient) return;
        
        if (!mc.options.useKey.isPressed()) {
            lastUsedInteractionHand = Hand.MAIN_HAND;
            noCooldownPeriodHand = Hand.MAIN_HAND;
            noCooldownPeriodActive = false;
            return;
        }
        
        Hand hand = noCooldownPeriodActive ? noCooldownPeriodHand : lastUsedInteractionHand;
        ItemStack stack = mc.player.getStackInHand(hand);
        Item item = stack.getItem();
        
        if (isNoCooldownItem(item)) {
            noCooldownPeriodActive = true;
            noCooldownPeriodHand = hand;
            
            ((MinecraftClientAccessors) MinecraftClient.getInstance()).fabric_example_mod_1_20$removeRightClickCooldown();
        }
        
    }
    
    private static boolean isNoCooldownItem(Item item) {
        return (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof CropBlock) || item instanceof HoeItem;
    }
    
}
