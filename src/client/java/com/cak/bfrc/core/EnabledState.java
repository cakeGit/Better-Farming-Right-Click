package com.cak.bfrc.core;

import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import java.awt.*;

public enum EnabledState {
    ENABLED(0, "on", Formatting.GREEN),
    DISABLED(1, "off", Formatting.RED);
    
    private final int index;
    private final String langName;
    private final Formatting color;
    
    EnabledState(int index, String langName, Formatting color) {
        this.index = index;
        this.langName = langName;
        this.color = color;
    }
    
    public static EnabledState byBool(Boolean currentlyEnabled) {
        return currentlyEnabled ? ENABLED : DISABLED;
    }
    
    public MutableText getNameComponent() {
        return Lang.modTranslatable("enabled_state." + langName, color, Formatting.BOLD);
    }
    
    public MutableText getStateComponent() {
        return Lang.literal("[", Formatting.WHITE, Formatting.BOLD)
            .append(getNameComponent())
            .append(Lang.literal("]", Formatting.WHITE, Formatting.BOLD));
    }
    
    public EnabledState next() {
        return EnabledState.values()[(this.index + 1) % EnabledState.values().length];
    }
    
}
