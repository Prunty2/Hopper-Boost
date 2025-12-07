package com.hopperboost;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HopperBoost implements ModInitializer {
    public static final String MOD_ID = "hopper-boost";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Register our custom gamerule
        HopperSpeedGamerule.register();
        LOGGER.info("Hopper Boost mod initialized! Use /gamerule hopperSpeed to configure hopper transfer speed.");
    }
}
