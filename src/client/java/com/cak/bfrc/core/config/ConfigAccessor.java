package com.cak.bfrc.core.config;

public interface ConfigAccessor {
    
    boolean noCooldownEnabled();
    boolean rightClickHarvestEnabled();
    boolean enabledOnStartup();
    
    void triggerSave();
    
}
