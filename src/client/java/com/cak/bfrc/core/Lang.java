package com.cak.bfrc.core;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Lang {
    
    public static MutableText translatable(String str, Formatting... formatting) {
        return Text.translatable(str).setStyle(Style.EMPTY.withFormatting(formatting));
    }
    
    public static MutableText modTranslatable(String str, Formatting... style) {
        return translatable(BFRC.ID + "." + str, style);
    }
    
    public static MutableText modTranslatable(String prefix, String str, Formatting... style) {
        return translatable(prefix + "." + BFRC.ID + "." + str, style);
    }
    
    public static MutableText literal(String str, Formatting... formatting) {
        return Text.literal(str).setStyle(Style.EMPTY.withFormatting(formatting));
    }
    
}
