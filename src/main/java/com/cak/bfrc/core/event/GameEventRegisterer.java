package com.cak.bfrc.core.event;

import net.neoforged.bus.api.Event;

import java.util.function.Consumer;

public interface GameEventRegisterer {
    void registerListener(Object listener);
    <T extends Event> void addListener(Consumer<T> consumer);
}
