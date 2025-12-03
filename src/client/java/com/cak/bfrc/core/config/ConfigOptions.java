package com.cak.bfrc.core.config;

import net.minecraft.client.option.SimpleOption;

public class ConfigOptions {
    
    public static SimpleOption[] ALL_OPTIONS = new SimpleOption[0];
    
    public static SimpleOption<Boolean> noCooldownEnabledOption;
    public static SimpleOption<Boolean> rightClickHarvestEnabledOption;
    //public static SimpleOption<Boolean> enableOnStartupOption;
    public static SimpleOption<Boolean> currentlyEnabledOption;
    
    /**Called after the config is loaded*/
    public static void buildOptions() {
        noCooldownEnabledOption =  SimpleOption.ofBoolean("options.no_cooldown_enabled", true);
        rightClickHarvestEnabledOption =  SimpleOption.ofBoolean("options.right_click_harvest_enabled", true);
        //enableOnStartupOption =  SimpleOption.ofBoolean("options.enable_on_startup", BFRC.CONFIG_ACCESSOR.enabledOnStartup());
        currentlyEnabledOption =  SimpleOption.ofBoolean("options.currently_enabled", true);
        
        ALL_OPTIONS = new SimpleOption[] {
            currentlyEnabledOption, /*enableOnStartupOption,*/
            noCooldownEnabledOption, rightClickHarvestEnabledOption,
        };
    }
    
    
}
