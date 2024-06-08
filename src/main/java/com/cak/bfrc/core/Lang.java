package com.cak.bfrc.core;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Lang {
    
    public static MutableComponent translatable(String str, ChatFormatting... style) {
        return Component.translatable(str).withStyle(style);
    }
    
    public static MutableComponent modTranslatable(String str, ChatFormatting... style) {
        return translatable(BFRC.ID + "." + str, style);
    }
    
    public static MutableComponent modTranslatable(String prefix, String str, ChatFormatting... style) {
        return translatable(prefix + "." + BFRC.ID + "." + str, style);
    }
    
    public static MutableComponent literal(String str, ChatFormatting... style) {
        return Component.literal(str).withStyle(style);
    }
    
}
