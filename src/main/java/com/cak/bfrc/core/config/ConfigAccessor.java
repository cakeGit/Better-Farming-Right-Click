package com.cak.bfrc.core.config;

import net.minecraft.client.OptionInstance;

public interface ConfigAccessor {
    
    boolean noCooldownEnabled();
    boolean rightClickHarvest();
    
    OptionInstance[] getOptions();
    
    void triggerSave();
    
}
