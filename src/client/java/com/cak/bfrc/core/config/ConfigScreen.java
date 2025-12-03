package com.cak.bfrc.core.config;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.Lang;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.GameOptions;

public class ConfigScreen extends GameOptionsScreen {

    public ConfigScreen(Screen pLastScreen, GameOptions pOptions) {
        super(pLastScreen, pOptions, Lang.modTranslatable("gui", "config_screen.title"));
    }

    @Override
    protected void addOptions() {
        this.body.addAll(ConfigOptions.ALL_OPTIONS);

    }

    @Override
    public void removed() {
        BFRC.CONFIG_ACCESSOR.triggerSave();
    }
}
