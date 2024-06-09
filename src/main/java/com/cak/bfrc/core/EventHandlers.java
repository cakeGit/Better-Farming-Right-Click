package com.cak.bfrc.core;

import com.cak.bfrc.platform.accessors.PlatformReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Field;

public class EventHandlers {
    
    public static void tryRightClickHarvest(Level level, ItemStack stack, BlockPos pos, Direction hitVecDirection) {
        if (!BFRC.CONFIG_ACCESSOR.rightClickHarvestEnabled()) return;
        
        if (!level.isClientSide) return;
        if (Minecraft.getInstance().gameMode == null) return;
        
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;
        if (!(blockItem.getBlock() instanceof CropBlock heldCropBlock)) return;
        
        BlockState clickedState = level.getBlockState(pos);
        
        if (!(clickedState.getBlock() instanceof CropBlock cropBlock)) return;
        if (heldCropBlock != cropBlock) return;
        if (!cropBlock.isMaxAge(clickedState)) return;
        
        Minecraft.getInstance().gameMode.startDestroyBlock(pos, hitVecDirection);
    }
    
    public static InteractionHand lastInteractionHand = null;
    public static InteractionHand lastUsedInteractionHand = InteractionHand.MAIN_HAND;
    
    public static void tickNoCooldownInteractions() {
        if (lastInteractionHand != null)
            lastUsedInteractionHand = lastInteractionHand;
        lastInteractionHand = null;
        
        if (!BFRC.CONFIG_ACCESSOR.noCooldownEnabled()) return;
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.gameMode == null || mc.player == null) return;
        if (!mc.level.isClientSide) return;
        
        if (!mc.options.keyUse.isDown()) return;
        
        ItemStack stack = mc.player.getItemInHand(lastUsedInteractionHand);
        Item item = stack.getItem();
        
        if (isNoCooldownItem(item)) {
            try {
                Field field = PlatformReflectionHelper.findField(Minecraft.class, "rightClickDelay");
                field.setAccessible(true);
                field.setInt(Minecraft.getInstance(), 0);
            } catch (Exception e) {
                BFRC.LOGGER.error("!!! Critical error in 'no cooldown' handling! PLS report to mod author !!!");
                e.printStackTrace();
            }
        }
        
    }
    
    private static boolean isNoCooldownItem(Item item) {
        return (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof CropBlock) || item instanceof HoeItem;
    }
    
}
