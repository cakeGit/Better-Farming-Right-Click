package com.cak.bfrc.core.config;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.Lang;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.option.GameOptions;

public class ConfigScreen extends SimpleOptionsScreen {
    
    public ConfigScreen(Screen pLastScreen, GameOptions pOptions) {
        super(pLastScreen, pOptions, Lang.modTranslatable("gui", "config_screen.title"), ConfigOptions.ALL_OPTIONS);
    }
    
    @Override
    public void removed() {
        BFRC.CONFIG_ACCESSOR.triggerSave();
    }
    
}
