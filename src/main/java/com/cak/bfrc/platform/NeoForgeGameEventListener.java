package com.cak.bfrc.platform;

import com.cak.bfrc.core.event.GameEventRegisterer;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;

import java.util.function.Consumer;

public class NeoForgeGameEventListener implements GameEventRegisterer {
    
    @Override
    public void registerListener(Object listener) {
        NeoForge.EVENT_BUS.register(listener);
    }
    
    @Override
    public <T extends Event > void addListener(Consumer<T> consumer) {
        NeoForge.EVENT_BUS.addListener(consumer);
    }

}
