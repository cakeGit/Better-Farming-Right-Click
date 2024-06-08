package com.cak.bfrc.core;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;

public class Controls {
    
    public static void onToggleRightClickFunctionsPressed() {
        BFRC.CURRENT_STATE = BFRC.CURRENT_STATE.next();
        notifyPlayerOfEnabledState();
    }
    
    public static void notifyPlayerOfEnabledState() {
        if (Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.displayClientMessage(
            Lang.translatable(BFRC.ID  + ".chat.toggle", ChatFormatting.GRAY, ChatFormatting.BOLD)
                .append(Lang.literal(" "))
                .append(BFRC.CURRENT_STATE.getStateComponent()),
            true
        );
    }
}
