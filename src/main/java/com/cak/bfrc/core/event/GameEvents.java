package com.cak.bfrc.core.event;

import com.cak.bfrc.core.BFRC;
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
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.lang.reflect.Field;

public class GameEvents {
    
    public static void tryRightClickHarvest(Level level, ItemStack stack, BlockPos pos, Direction hitVecDirection) {
        if (!BFRC.CONFIG_ACCESSOR.rightClickHarvest()) return;
        
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
    
    public static boolean isMainHandBlockInteractionActive = true;
    public static boolean interactPressedLastTick = false;
    
    private void tickNoCooldownInteractions() {
        
        if (!BFRC.CONFIG_ACCESSOR.noCooldownEnabled()) return;
        
        boolean previousInteractPressed = interactPressedLastTick;
        interactPressedLastTick = Minecraft.getInstance().options.keyUse.isDown();
        if (!previousInteractPressed) return;
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.gameMode == null) return;
        if (!mc.level.isClientSide) return;
        
        if (!Minecraft.getInstance().options.keyUse.isDown()) return;
        
        InteractionHand hand = isMainHandBlockInteractionActive ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        
        ItemStack stack = mc.player.getItemInHand(hand);
        Item item = stack.getItem();
        
        
        if ((item instanceof BlockItem blockItem && blockItem.getBlock() instanceof CropBlock) ||
            item instanceof HoeItem) {
            try {
                Field field = ObfuscationReflectionHelper.findField(Minecraft.class, "rightClickDelay");
                field.setAccessible(true);
                field.setInt(Minecraft.getInstance(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        tickNoCooldownInteractions();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        
        tryRightClickHarvest(event.getLevel(), event.getItemStack(), event.getPos(), event.getHitVec().getDirection());
        
    }
    
}
