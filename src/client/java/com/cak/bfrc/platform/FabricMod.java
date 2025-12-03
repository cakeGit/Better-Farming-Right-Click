package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.platform.events.GameEvents;
import net.fabricmc.api.ClientModInitializer;

public class FabricMod implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		BFRC.CURRENT_PLATFORM = "fabric";
		
		FabricControls.init();
		GameEvents.registerEvents();
		
		BFRC.CONFIG_ACCESSOR = new FabricConfig();
		BFRC.setup();
	}
	
}