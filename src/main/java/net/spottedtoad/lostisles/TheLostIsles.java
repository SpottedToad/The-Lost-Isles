package net.spottedtoad.lostisles;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  TheLostIsles implements ModInitializer {
	public static final String MOD_ID = "lostisles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing The Lost Isles mod by SpottedToad");
	}
}
