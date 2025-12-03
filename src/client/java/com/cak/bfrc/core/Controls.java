package com.cak.bfrc.core;

public class Controls {
    
    public static void onToggleRightClickFunctionsPressed() {
        BFRC.CURRENT_STATE = BFRC.CURRENT_STATE.next();
        BFRC.showEnabledState();
    }
    
}
