package com.cak.bfrc.core.config;

import com.cak.bfrc.core.BFRC;
import net.minecraft.client.option.SimpleOption;

public class ConfigOptions {
    
    public static SimpleOption[] ALL_OPTIONS = new SimpleOption[0];
    
    public static SimpleOption<Boolean> noCooldownEnabledOption;
    public static SimpleOption<Boolean> rightClickHarvestEnabledOption;
    public static SimpleOption<Boolean> enableOnStartupOption;
    public static SimpleOption<Boolean> currentlyEnabledOption;
    
    /**Called after the config is loaded*/
    public static void buildOptions() {
        noCooldownEnabledOption =  SimpleOption.ofBoolean("options.no_cooldown_enabled", BFRC.CONFIG_ACCESSOR.noCooldownEnabled());
        rightClickHarvestEnabledOption =  SimpleOption.ofBoolean("options.right_click_harvest_enabled", BFRC.CONFIG_ACCESSOR.rightClickHarvestEnabled());
        enableOnStartupOption =  SimpleOption.ofBoolean("options.enable_on_startup", BFRC.CONFIG_ACCESSOR.enabledOnStartup());
        currentlyEnabledOption =  SimpleOption.ofBoolean("options.currently_enabled", BFRC.CONFIG_ACCESSOR.enabledOnStartup());
        
        ALL_OPTIONS = new SimpleOption[] {
            currentlyEnabledOption, enableOnStartupOption,
            noCooldownEnabledOption, rightClickHarvestEnabledOption,
        };
    }
    
    
}
