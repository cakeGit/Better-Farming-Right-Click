package com.cak.bfrc.core.config;

public interface ConfigAccessor {
    
    boolean noCooldownEnabled();
    boolean rightClickHarvestEnabled();
    
    void triggerSave();
    
    boolean enabledOnStartup();
    
}
