package com.cak.bfrc.core.config;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.Lang;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.SimpleOptionsSubScreen;

public class ConfigScreen extends SimpleOptionsSubScreen {
    
    public ConfigScreen(Screen pLastScreen, Options pOptions) {
        super(pLastScreen, pOptions, Lang.modTranslatable("gui", "config_screen.title"), ConfigOptions.ALL_OPTIONS);
    }
    
    @Override
    public void removed() {
        BFRC.CONFIG_ACCESSOR.triggerSave();
    }
    
}
