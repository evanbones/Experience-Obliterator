package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public abstract class HudMixin {

    @WrapOperation(method = "renderExperienceBar", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIIIIIII)V"))
    private void experience_obliterator$hideExperienceBarFill(GuiGraphics guiGraphics, ResourceLocation sprite,
                                                                int textureWidth, int textureHeight,
                                                                int uPosition, int vPosition,
                                                                int x, int y, int uWidth, int vHeight,
                                                                Operation<Void> original) {
        if (ModConfig.get().hideExperienceBarFill) {
            return;
        }
        original.call(guiGraphics, sprite, textureWidth, textureHeight, uPosition, vPosition, x, y, uWidth, vHeight);
    }
}
