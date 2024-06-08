package com.cak.bfrc.platform;

import com.cak.bfrc.core.config.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

public class ConfigScreenFactory implements IConfigScreenFactory {
    
    @Override
    public @NotNull Screen createScreen(@NotNull Minecraft minecraft, @NotNull Screen modListScreen) {
        return new ConfigScreen(modListScreen, minecraft.options);
    }
    
}
