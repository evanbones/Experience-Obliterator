package com.evandev.experience_obliterator.mixin;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @WrapOperation(
            method = "addEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/entity/PersistentEntitySectionManager;addNewEntity(Lnet/minecraft/world/level/entity/EntityAccess;)Z"
            )
    )
    private <T extends EntityAccess> boolean experience_obliterator$shouldAddExperienceEntity(PersistentEntitySectionManager<T> manager, T entity, Operation<Boolean> operation) {
        if (ModConfig.get().disableOrbSpawn && entity instanceof ExperienceOrb) {
            return false;
        }
        return operation.call(manager, entity);
    }

    @WrapWithCondition(
            method = "addEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"
            )
    )
    private boolean experience_obliterator$shouldWarnConsoleOnMissingEntity(Logger logger, String message, Object args, Entity entity) {
        return !ModConfig.get().immediateExperiencePickup || !(entity instanceof ExperienceOrb);
    }
}
