package com.cak.bfrc.core;

import com.cak.bfrc.core.config.ConfigAccessor;
import com.cak.bfrc.core.config.ConfigOptions;
import com.mojang.logging.LogUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;

public class BFRC {
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static final String ID = "better_farming_right_click";
    public static String CURRENT_PLATFORM;
    
    public static EnabledState CURRENT_STATE = EnabledState.ENABLED;
    
    public static ConfigAccessor CONFIG_ACCESSOR;
    
    public static void setup() {
        
        CURRENT_STATE = EnabledState.byBool(CONFIG_ACCESSOR.enabledOnStartup());
        ConfigOptions.buildOptions();
        
    }
    
    public static void showEnabledState() {
        if (MinecraftClient.getInstance().player == null) return;
        MinecraftClient.getInstance().player.sendMessage(
            Lang.translatable(BFRC.ID + ".chat.toggle", Formatting.GRAY, Formatting.BOLD)
                .append(Lang.literal(" "))
                .append(BFRC.CURRENT_STATE.getStateComponent()),
            false
        );
    }
    
}
