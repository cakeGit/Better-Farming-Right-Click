package com.cak.bfrc.core;

import net.minecraft.client.Minecraft;

public class Controls {
    
    public static void onToggleRightClickFunctionsPressed() {
        BFRC.CURRENT_STATE = BFRC.CURRENT_STATE.next();
        notifyPlayerOfEnabledState();
    }
    
    public static void notifyPlayerOfEnabledState() {
        if (Minecraft.getInstance().player == null) return;
        BFRC.showEnabledState();
    }
    
}
