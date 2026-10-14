package com.evandev.experience_obliterator.mixin.client;

//? if >=26.1 {
/*import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//? if >=26.3 {
/^import com.mojang.renderpearl.api.pipeline.RenderPipeline;
^///?} else {
import com.mojang.blaze3d.pipeline.RenderPipeline;
//?}
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? if >=26.2 {
/^import net.minecraft.client.gui.contextualbar.ExperienceBar;
^///?} else {
import net.minecraft.client.gui.contextualbar.ExperienceBarRenderer;
//?}
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.2 {
/^@Mixin(ExperienceBar.class)
^///?} else {
@Mixin(ExperienceBarRenderer.class)
//?}
public abstract class ExperienceBarMixin {

    @Inject(method = "extractBackground", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$hideExperienceBar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (ModConfig.get().hideExperienceBar) {
            ci.cancel();
        }
    }

    //? if >=26.3 {
    /^@WrapOperation(method = "extractBackground", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"))
    ^///?} else {
    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"))
    //?}
    private void experience_obliterator$hideExperienceBarFill(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier location,
                                                              int spriteWidth, int spriteHeight,
                                                              int textureX, int textureY,
                                                              int x, int y, int width, int height,
                                                              Operation<Void> original) {
        if (ModConfig.get().hideExperienceBarFill) {
            return;
        }
        original.call(graphics, renderPipeline, location, spriteWidth, spriteHeight, textureX, textureY, x, y, width, height);
    }
}
*///?}
