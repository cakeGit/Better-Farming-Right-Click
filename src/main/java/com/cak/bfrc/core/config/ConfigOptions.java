package com.cak.bfrc.core.config;

import com.cak.bfrc.core.BFRC;
import net.minecraft.client.OptionInstance;

public class ConfigOptions {
    
    public static OptionInstance[] ALL_OPTIONS = new OptionInstance[0];
    
    public static OptionInstance<Boolean> noCooldownEnabledOption;
    public static OptionInstance<Boolean> rightClickHarvestEnabledOption;
    public static OptionInstance<Boolean> enableOnStartupOption;
    public static OptionInstance<Boolean> currentlyEnabledOption;
    
    /**Called after the config is loaded*/
    public static void buildOptions() {
        noCooldownEnabledOption =  OptionInstance.createBoolean("options.no_cooldown_enabled", BFRC.CONFIG_ACCESSOR.noCooldownEnabled());
        rightClickHarvestEnabledOption =  OptionInstance.createBoolean("options.right_click_harvest_enabled", BFRC.CONFIG_ACCESSOR.rightClickHarvestEnabled());
        enableOnStartupOption =  OptionInstance.createBoolean("options.enable_on_startup", BFRC.CONFIG_ACCESSOR.enabledOnStartup());
        currentlyEnabledOption =  OptionInstance.createBoolean("options.currently_enabled", BFRC.CONFIG_ACCESSOR.enabledOnStartup());
        
        ALL_OPTIONS = new OptionInstance[] {
            currentlyEnabledOption, enableOnStartupOption,
            noCooldownEnabledOption, rightClickHarvestEnabledOption,
        };
    }
    
    
}
