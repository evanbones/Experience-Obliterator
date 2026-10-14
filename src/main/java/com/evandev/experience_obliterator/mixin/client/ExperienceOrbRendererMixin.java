package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
//? if >=26.1 {
/*import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.ExperienceOrbRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
*///?} else {
import net.minecraft.client.renderer.MultiBufferSource;
//?}
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.world.entity.ExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbRenderer.class)
//? if >=26.1 {
/*public abstract class ExperienceOrbRendererMixin extends EntityRenderer<ExperienceOrb, ExperienceOrbRenderState> {
*///?} else {
public abstract class ExperienceOrbRendererMixin extends EntityRenderer<ExperienceOrb> {
//?}

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
            //? if >=26.1 {
            /*method = "submit(Lnet/minecraft/client/renderer/entity/state/ExperienceOrbRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            *///?} else {
            method = "render(Lnet/minecraft/world/entity/ExperienceOrb;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            //?}
            at = @At(
                    shift = At.Shift.AFTER,
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"
            )
    )
    //? if >=26.1 {
    /*private void experience_obliterator$setOrbInvisible(ExperienceOrbRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo callback) {
    *///?} else {
    private void experience_obliterator$setOrbInvisible(ExperienceOrb entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo callback) {
    //?}
        if (ModConfig.get().disableOrbRendering) {
            poseStack.scale(0.0F, 0.0F, 0.0F);
            this.shadowRadius = 0.0F;
        } else {
            this.shadowRadius = this.experience_obliterator$originalShadowRadius;
        }
    }
}
