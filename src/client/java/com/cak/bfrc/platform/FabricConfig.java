package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.EnabledState;
import com.cak.bfrc.core.config.ConfigAccessor;
import com.cak.bfrc.core.config.ConfigOptions;

public class FabricConfig implements ConfigAccessor {
    
    @Override
    public boolean noCooldownEnabled() {
        return ConfigOptions.noCooldownEnabledOption.getValue();
    }
    
    @Override
    public boolean rightClickHarvestEnabled() {
        return ConfigOptions.rightClickHarvestEnabledOption.getValue();
    }
    
    @Override
    public boolean enabledOnStartup() {
        return true;
    }
    
    @Override
    public void triggerSave() {
        BFRC.CURRENT_STATE = EnabledState.byBool(ConfigOptions.currentlyEnabledOption.getValue());
    }
    
}
