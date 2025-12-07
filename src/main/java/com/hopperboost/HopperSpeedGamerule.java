package com.hopperboost;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.GameRules;

public class HopperSpeedGamerule {
    // The gamerule key for hopper speed multiplier
    // Default is 1.0 (vanilla speed), 2.0 = double speed, 3.0 = triple speed, etc.
    public static GameRules.Key<DoubleRule> HOPPER_SPEED;

    public static void register() {
        HOPPER_SPEED = GameRuleRegistry.register(
            "hopperSpeed",
            GameRules.Category.MISC,
            GameRuleFactory.createDoubleRule(1.0, 0.1, 100.0) // default 1.0, min 0.1, max 100.0
        );
    }

    /**
     * Gets the hopper speed multiplier from the world's gamerules.
     * A value of 1.0 means vanilla speed (8 game ticks cooldown).
     * A value of 2.0 means double speed (4 game ticks cooldown).
     * A value of 0.5 means half speed (16 game ticks cooldown).
     * 
     * @param gameRules The world's gamerules
     * @return The speed multiplier
     */
    public static double getSpeedMultiplier(GameRules gameRules) {
        if (HOPPER_SPEED == null) {
            return 1.0;
        }
        return gameRules.get(HOPPER_SPEED).get();
    }
}
