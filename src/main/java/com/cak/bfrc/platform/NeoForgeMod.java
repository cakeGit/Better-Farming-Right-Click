package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Consumer;

import static com.cak.bfrc.core.BFRC.LOGGER;

@Mod(BFRC.ID)
public class NeoForgeMod {
    
    private static IEventBus modEventBus;

    public NeoForgeMod(IEventBus modEventBus, ModContainer modContainer) {
        BFRC.CURRENT_PLATFORM = "neoforge";
        NeoForgeMod.modEventBus = modEventBus;
        
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeModConfig.configSpec);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, new ConfigScreenFactory());
        
        BFRC.CONFIG_ACCESSOR = NeoForgeModConfig.config;
        
        addModBusListener(NeoForgeMod::onServerStartup);
        addModBusListener(NeoForgeMod::onClientStartup);
    }
    
    public static void onServerStartup(FMLDedicatedServerSetupEvent event) {
        LOGGER.info("This is a client side mod! Disabling...");
    }
    
    public static void onClientStartup(FMLClientSetupEvent event) {
        LOGGER.info("Hello from client!");
        
        NeoForgeControls.init();
        
        BFRC.setup(new NeoForgeGameEventListener());
        
        NeoForgeModConfig.buildOptions();
    }
    
    public static void registerModBusListener(Object listener) {
        modEventBus.register(listener);
    }
    
    public static <T extends Event> void addModBusListener(Consumer<T> consumer) {
        modEventBus.addListener(consumer);
    }
    
}
