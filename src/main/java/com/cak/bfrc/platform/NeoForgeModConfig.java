package com.cak.bfrc.platform;


import com.cak.bfrc.core.config.ConfigAccessor;
import net.minecraft.client.OptionInstance;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class NeoForgeModConfig implements ConfigAccessor {
    
    static ModConfigSpec.ConfigValue<Boolean> noCooldownEnabled;
    static ModConfigSpec.ConfigValue<Boolean> rightClickHarvest;
    
    static Map<ModConfigSpec.ConfigValue, OptionInstance> optionInstances;
    
    public NeoForgeModConfig(ModConfigSpec.Builder builder) {
        noCooldownEnabled = builder.comment("Whether the no cooldown tool should be enabled (this is what allows the quick usage of seeds and hoes)")
            .define("no_cooldown_enabled", true);
        rightClickHarvest = builder.comment("Whether the right click harvest tool should be enabled")
            .define("right_click_harvest", true);
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
    public OptionInstance[] getOptions() {
        return optionInstances.values().toArray(new OptionInstance[] {});
    }
    
    @Override
    public void save() {
    
    }
    
    public static void buildOptions() {
        optionInstances = Map.of(
            noCooldownEnabled, OptionInstance.createBoolean("options.no_cooldown_enabled", noCooldownEnabled.get()),
            rightClickHarvest, OptionInstance.createBoolean("options.right_click_harvest", rightClickHarvest.get())
        );
    }
    
    @Override
    public boolean noCooldownEnabled() {
        return noCooldownEnabled.get();
    }
    
    @Override
    public boolean rightClickHarvest() {
        return rightClickHarvest.get();
    }
    
}
