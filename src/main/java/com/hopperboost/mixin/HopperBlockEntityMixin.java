package com.hopperboost.mixin;

import com.hopperboost.HopperSpeedGamerule;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public abstract class HopperBlockEntityMixin {
    
    @Shadow
    private int transferCooldown;
    
    /**
     * Modifies the hopper cooldown based on the hopperSpeed gamerule.
     * 
     * Vanilla hopper cooldown is 8 ticks.
     * With hopperSpeed = 2.0, cooldown becomes 4 ticks (double speed).
     * With hopperSpeed = 3.0, cooldown becomes ~2.67 ticks (triple speed).
     * With hopperSpeed = 0.5, cooldown becomes 16 ticks (half speed).
     * 
     * This mixin injects at the end of setTransferCooldown to modify the cooldown
     * value that was just set.
     */
    @Inject(method = "setTransferCooldown", at = @At("TAIL"))
    private void modifyTransferCooldown(int cooldownParam, CallbackInfo ci) {
        // Only modify positive cooldowns (when hopper is actually cooling down)
        // Cooldown of -1 or 0 has special meaning, don't modify those
        if (cooldownParam <= 0) {
            return;
        }
        
        HopperBlockEntity self = (HopperBlockEntity) (Object) this;
        World world = self.getWorld();
        
        if (world != null && world instanceof ServerWorld serverWorld) {
            double speedMultiplier = HopperSpeedGamerule.getSpeedMultiplier(serverWorld.getGameRules());
            
            // Only modify if the speed multiplier is different from default
            if (speedMultiplier != 1.0 && speedMultiplier > 0) {
                // Calculate new cooldown: higher speed = lower cooldown
                // Vanilla cooldown is typically 8, we divide by the multiplier
                int newCooldown = (int) Math.max(1, Math.round(cooldownParam / speedMultiplier));
                this.transferCooldown = newCooldown;
            }
        }
    }
}
