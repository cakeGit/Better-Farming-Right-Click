package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.core.Controls;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

import static com.cak.bfrc.core.BFRC.LOGGER;

public class NeoForgeControls {
    
    public static final Lazy<KeyMapping> TOGGLE_RIGHT_CLICK_FUNCTIONS = Lazy.of(() -> new KeyMapping(
        "key." + BFRC.ID + ".toggle_right_click_functions",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_N,
        "key.categories." + BFRC.ID
    ));
    
    public static void init() {
        NeoForge.EVENT_BUS.addListener(NeoForgeControls::onClientTick);
        NeoForgeMod.addModBusListener(NeoForgeControls::onRegisterBindings);
        LOGGER.info("Initialised platform controls");
    }
    
    public static void onClientTick(ClientTickEvent.Post event) {
        while (TOGGLE_RIGHT_CLICK_FUNCTIONS.get().consumeClick()) {
            Controls.onToggleRightClickFunctionsPressed();
        }
    }
    
    public static void onRegisterBindings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_RIGHT_CLICK_FUNCTIONS.get());
    }
    
}
