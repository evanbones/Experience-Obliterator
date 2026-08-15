package com.evandev.experience_obliterator.mixin;

import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @ModifyArg(
            index = 6,
            method = "giveExperienceLevels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
            )
    )
    private float experience_obliterator$modifyExperienceLevelVolume(float volume) {
        return ModConfig.get().disableXpLevelSound ? 0.0F : volume;
    }
}
