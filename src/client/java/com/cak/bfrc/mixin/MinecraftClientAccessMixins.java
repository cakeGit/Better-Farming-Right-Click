package com.cak.bfrc.mixin;

import com.cak.bfrc.platform.MinecraftClientAccessors;
import com.cak.bfrc.platform.events.GameEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientAccessMixins implements MinecraftClientAccessors {
    
    @Shadow private int itemUseCooldown;
    
    @Override
    public void fabric_example_mod_1_20$removeRightClickCooldown() {
        itemUseCooldown = 0;
    }
    
    @Redirect(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"))
    public ItemStack interceptGetStackInHand(ClientPlayerEntity instance, Hand hand) {
        GameEvents.onInteractionKeyMappingTriggered(hand);
        return instance.getStackInHand(hand);
    }
    
    
}
