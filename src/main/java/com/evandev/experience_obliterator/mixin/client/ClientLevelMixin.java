package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
//? if >=26.1 {
/*import net.minecraft.client.sounds.SoundEngine;
*///?}
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {

    //? if >=26.1 {
    /*@WrapOperation(
            method = "playSound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/sounds/SoundEngine$PlayResult;"
            )
    )
    private SoundEngine.PlayResult experience_obliterator$wrapPlay(SoundManager manager, SoundInstance sound, Operation<SoundEngine.PlayResult> original) {
        if (experience_obliterator$isMutedPickupSound(sound)) {
            return null;
        }
        return original.call(manager, sound);
    }
    *///?} else {
    @WrapOperation(
            method = "playSound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
            )
    )
    private void experience_obliterator$wrapPlay(SoundManager manager, SoundInstance sound, Operation<Void> original) {
        if (experience_obliterator$isMutedPickupSound(sound)) {
            return;
        }
        original.call(manager, sound);
    }
    //?}

    @WrapOperation(
            method = "playSound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sounds/SoundManager;playDelayed(Lnet/minecraft/client/resources/sounds/SoundInstance;I)V"
            )
    )
    private void experience_obliterator$wrapPlayDelayed(SoundManager manager, SoundInstance sound, int delay, Operation<Void> original) {
        if (experience_obliterator$isMutedPickupSound(sound)) {
            return;
        }
        original.call(manager, sound, delay);
    }

    @Unique
    private static boolean experience_obliterator$isMutedPickupSound(SoundInstance sound) {
        //? if >=26.1 {
        /*return ModConfig.get().disableXpPickupSound && sound.getIdentifier().equals(SoundEvents.EXPERIENCE_ORB_PICKUP.location());
        *///?} else {
        return ModConfig.get().disableXpPickupSound && sound.getLocation().equals(SoundEvents.EXPERIENCE_ORB_PICKUP.getLocation());
        //?}
    }
}
