package com.cak.bfrc.platform;

import com.cak.bfrc.core.BFRC;
import com.cak.bfrc.platform.events.GameEvents;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class FabricMod implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		BFRC.CURRENT_PLATFORM = "fabric";
		
		FabricControls.init();
		GameEvents.registerEvents();
		// Only choose one of these!
		AutoConfig.register(FabricClothConfig.class, Toml4jConfigSerializer::new);
		FabricClothConfig.ACCESSOR_INSTANCE = AutoConfig.getConfigHolder(FabricClothConfig.class).getConfig();
		BFRC.CONFIG_ACCESSOR = new FabricClothConfig();
		
		
		BFRC.setup();
	}
	
}