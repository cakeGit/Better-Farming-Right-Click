package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.Controls;
import com.cak.bfrc.core.Lang;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

import static com.cak.bfrc.core.BFRC.LOGGER;

public class FabricControls {
    
    public static final KeyBinding TOGGLE_AND_OPTIONS = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key." + BFRC.ID + ".toggle_and_options",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_N,
        "key.categories." + BFRC.ID
    ));
    
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(FabricControls::onClientTick);
        LOGGER.info("Initialised platform controls");
    }
    
    public static void onClientTick(MinecraftClient event) {
        while (TOGGLE_AND_OPTIONS.wasPressed()) {
            if (Screen.hasShiftDown()) {
                
                Controls.onToggleRightClickFunctionsPressed();
            } else  {
                ConfigBuilder screen = ConfigBuilder.create()
                    .setParentScreen(event.currentScreen)
                    .setTitle(Lang.modTranslatable("title", "config"));
                
                screen
                
                event.setScreen(screen.build());
            }
        }
    }
    
    
    
}
