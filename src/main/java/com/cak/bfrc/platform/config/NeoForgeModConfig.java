package com.cak.bfrc.platform.config;


import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.EnabledState;
import com.cak.bfrc.core.config.ConfigAccessor;
import com.cak.bfrc.core.config.ConfigOptions;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class NeoForgeModConfig implements ConfigAccessor {
    
    final ModConfigSpec.ConfigValue<Boolean> noCooldownEnabled;
    
    final ModConfigSpec.ConfigValue<Boolean> rightClickHarvestEnabled;
    
    final ModConfigSpec.ConfigValue<Boolean> enabledOnStartup;
    
    public NeoForgeModConfig(ModConfigSpec.Builder builder) {
        noCooldownEnabled = builder.comment("Whether the no cooldown tool should be enabled (this is what allows the quick usage of seeds and hoes)")
            .define("no_cooldown_enabled", true);
        rightClickHarvestEnabled = builder.comment("Whether the right click harvest tool should be enabled")
            .define("right_click_harvest", true);
        enabledOnStartup = builder.comment("Whether the mod should be enabled on startup")
            .define("startup_enabled", true);
    }
    
    //Define a field to keep the config for later
    public static NeoForgeModConfig config;
    
    public static ModConfigSpec configSpec;
    
    //Configure the config once the class is loaded
    static {
        Pair<NeoForgeModConfig, ModConfigSpec> pair =
            new ModConfigSpec.Builder()
                .configure(NeoForgeModConfig::new);
        
        //Store pair values
        config = pair.getLeft();
        configSpec = pair.getRight();
    }
    
    @Override
    public void triggerSave() {
        noCooldownEnabled.set(ConfigOptions.noCooldownEnabledOption.get());
        rightClickHarvestEnabled.set(ConfigOptions.rightClickHarvestEnabledOption.get());
        enabledOnStartup.set(ConfigOptions.enableOnStartupOption.get());
        
        EnabledState newState = EnabledState.byBool(ConfigOptions.enableOnStartupOption.get());
        if (newState != BFRC.CURRENT_STATE) {
            BFRC.CURRENT_STATE = newState;
            BFRC.showEnabledState();
        }
    }
    
    @Override
    public boolean noCooldownEnabled() {
        return noCooldownEnabled.get();
    }
    
    @Override
    public boolean rightClickHarvestEnabled() {
        return rightClickHarvestEnabled.get();
    }
    
    @Override
    public boolean enabledOnStartup() {
        return enabledOnStartup.get();
    }
    
}
