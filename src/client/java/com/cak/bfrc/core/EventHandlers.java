package com.cak.bfrc.core;

import com.cak.bfrc.platform.MinecraftClientAccessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
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

        BlockState clickedState = level.getBlockState(pos);
        if (clickedState.getBlock() == Blocks.NETHER_WART && clickedState.get(NetherWartBlock.AGE) >= 3) {
            if (stack.getItem() == Items.NETHER_WART) {
                mc.interactionManager.attackBlock(pos, hitVecDirection);
            }
        }
        if (clickedState.getBlock() instanceof CropBlock clickedCropBlock) {
            if (stack.getItem() instanceof BlockItem heldBlockItem) {
                if (heldBlockItem.getBlock() == clickedCropBlock && clickedCropBlock.isMature(clickedState)) {
                    mc.interactionManager.attackBlock(pos, hitVecDirection);
                }
            }
        }
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
        if (item instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof CropBlock || blockItem.getBlock() instanceof NetherWartBlock) return true;
        }
        return item instanceof HoeItem;
    }
}
