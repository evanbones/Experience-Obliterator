package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {

    @WrapOperation(
            method = "playSound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/sounds/SoundEngine$PlayResult;"
            )
    )
    private SoundEngine.PlayResult experience_obliterator$wrapPlay(SoundManager manager, SoundInstance instance, Operation<SoundEngine.PlayResult> original) {
        if (ModConfig.get().disableXpPickupSound && instance.getIdentifier().equals(SoundEvents.EXPERIENCE_ORB_PICKUP.location())) {
            return null;
        }
        return original.call(manager, instance);
    }

    @WrapOperation(
            method = "playSound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;playDelayed(Lnet/minecraft/client/resources/sounds/SoundInstance;I)V"
            )
    )
    private void experience_obliterator$wrapPlayDelayed(SoundManager manager, SoundInstance instance, int delay, Operation<Void> original) {
        if (ModConfig.get().disableXpPickupSound && instance.getIdentifier().equals(SoundEvents.EXPERIENCE_ORB_PICKUP.location())) {
            return;
        }
        original.call(manager, instance, delay);
    }
}
