package com.cak.bfrc.core;

import com.cak.bfrc.core.config.ConfigAccessor;
import com.cak.bfrc.core.event.GameEventRegisterer;
import com.cak.bfrc.core.event.GameEvents;
import com.mojang.logging.LogUtils;
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
    
}
