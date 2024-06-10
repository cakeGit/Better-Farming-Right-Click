package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.config.ConfigAccessor;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = BFRC.ID)
public class FabricClothConfig implements ConfigAccessor, ConfigData {
    
    public static ConfigAccessor ACCESSOR_INSTANCE;
    
    @Override
    public boolean noCooldownEnabled() {
        return true;
    }
    
    @Override
    public boolean rightClickHarvestEnabled() {
        return true;
    }
    
    @Override
    public void triggerSave() {
    
    }
    
    @Override
    public boolean enabledOnStartup() {
        return true;
    }
    
}
