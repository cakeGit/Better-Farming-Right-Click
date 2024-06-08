package com.cak.bfrc.core;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

public enum EnabledState {
    ENABLED(0, "on", net.minecraft.ChatFormatting.GREEN),
    DISABLED(1, "off", net.minecraft.ChatFormatting.RED);
    
    private final int index;
    private final String langName;
    private final ChatFormatting color;
    
    EnabledState(int index, String langName, ChatFormatting color) {
        this.index = index;
        this.langName = langName;
        this.color = color;
    }
    
    public MutableComponent getNameComponent() {
        return Lang.modTranslatable("enabled_state." + langName, color, ChatFormatting.BOLD);
    }
    
    public MutableComponent getStateComponent() {
        return Lang.literal("[", ChatFormatting.WHITE, ChatFormatting.BOLD)
            .append(getNameComponent())
            .append(Lang.literal("]", ChatFormatting.WHITE, ChatFormatting.BOLD));
    }
    
    public EnabledState next() {
        return EnabledState.values()[(this.index + 1) % EnabledState.values().length];
    }
    
}
