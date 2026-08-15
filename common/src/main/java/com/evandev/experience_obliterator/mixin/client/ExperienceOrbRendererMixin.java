package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.client.renderer.entity.state.ExperienceOrbRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.ExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbRenderer.class)
public abstract class ExperienceOrbRendererMixin extends EntityRenderer<ExperienceOrb, ExperienceOrbRenderState> {

    @Unique
    private float experience_obliterator$originalShadowRadius;

    private ExperienceOrbRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void experience_obliterator$onMakeOrbRenderer(CallbackInfo callback) {
        this.experience_obliterator$originalShadowRadius = this.shadowRadius;
    }

    @Inject(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/ExperienceOrbRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At(
                    shift = At.Shift.AFTER,
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"
            )
    )
    private void experience_obliterator$setOrbInvisible(ExperienceOrbRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo callback) {
        if (ModConfig.get().disableOrbRendering) {
            poseStack.scale(0.0F, 0.0F, 0.0F);
            this.shadowRadius = 0.0F;
        } else {
            this.shadowRadius = this.experience_obliterator$originalShadowRadius;
        }
    }
}
