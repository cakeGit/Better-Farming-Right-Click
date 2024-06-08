package com.cak.bfrc.core;

import com.cak.bfrc.core.config.ConfigAccessor;
import com.cak.bfrc.core.event.GameEventRegisterer;
import com.cak.bfrc.core.event.GameEvents;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;

public class BFRC {
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static final String ID = "better_farming_right_click";
    public static String CURRENT_PLATFORM;
    
    public static EnabledState CURRENT_STATE = EnabledState.ENABLED;
    
    public static ConfigAccessor CONFIG_ACCESSOR;
    
    public static void setup(GameEventRegisterer eventRegisterer) {
        
        eventRegisterer.registerListener(new GameEvents());
        
    }
    
    public static void showEnabledState() {
        Minecraft.getInstance().player.displayClientMessage(
            Lang.translatable(BFRC.ID  + ".chat.toggle", ChatFormatting.GRAY, ChatFormatting.BOLD)
                .append(Lang.literal(" "))
                .append(BFRC.CURRENT_STATE.getStateComponent()),
            true
        );
    }
    
}
