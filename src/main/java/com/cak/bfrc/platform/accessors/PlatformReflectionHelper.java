package com.cak.bfrc.platform.accessors;

import net.neoforged.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class PlatformReflectionHelper {
    
    public static <T> Field findField(Class<T> clazz, String fieldName) {
        return ObfuscationReflectionHelper.findField(clazz, fieldName);
    }
    
}
