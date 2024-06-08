package com.cak.bfrc.core.mixins;

import com.cak.bfrc.core.event.GameEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public abstract class InteractionResultScraper {
    
    @Redirect(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;useItemOn(Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;"))
    public InteractionResult useItemOnRedirect(MultiPlayerGameMode gamemode, LocalPlayer player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult result = gamemode.useItemOn(player, hand, hitResult);
        if (hand == InteractionHand.MAIN_HAND) {
            GameEvents.isMainHandBlockInteractionActive = result != InteractionResult.PASS;
            System.out.println(GameEvents.isMainHandBlockInteractionActive);
        }
        return result;
    }

}
