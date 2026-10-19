package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.client.ExperienceBarGap;
import com.evandev.experience_obliterator.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

//? if >=26.1 {
/*import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
*///?}
//? if >=26.2 {
/*import net.minecraft.client.gui.Hud;
*///?} else if >=26.1 {
/*import net.minecraft.client.gui.Gui;
*///?} else if >=1.21 {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.injection.At;
//?} else {
/*import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.injection.At;
*///?}

//? if >=26.2 {
/*@Mixin(Hud.class)
 *///?} else {
@Mixin(Gui.class)
//?}
public abstract class HudMixin {

    //? if >=1.21 && <26.1 {
    @WrapOperation(method = "renderExperienceBar", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;getXpNeededForNextLevel()I"))
    private int experience_obliterator$hideExperienceBar(LocalPlayer player, Operation<Integer> original) {
        if (ModConfig.get().hideExperienceBar) {
            return 0;
        }
        return original.call(player);
    }

    @WrapOperation(method = "renderExperienceLevel", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;isExperienceBarVisible()Z"))
    private boolean experience_obliterator$hideExperienceLevel(Gui gui, Operation<Boolean> original) {
        if (ModConfig.get().hideExperienceLevel) {
            return false;
        }
        return original.call(gui);
    }

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
    //?}

    //? if <1.21 {
    /*// renderExperienceBar draws the background, then the fill, then the level text
    @WrapWithCondition(method = "renderExperienceBar", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V", ordinal = 0))
    private boolean experience_obliterator$hideExperienceBar(GuiGraphics guiGraphics, ResourceLocation atlasLocation,
                                                             int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        return !ModConfig.get().hideExperienceBar;
    }

    @WrapWithCondition(method = "renderExperienceBar", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V", ordinal = 1))
    private boolean experience_obliterator$hideExperienceBarFill(GuiGraphics guiGraphics, ResourceLocation atlasLocation,
                                                                 int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        return !ModConfig.get().hideExperienceBar && !ModConfig.get().hideExperienceBarFill;
    }

    @WrapOperation(method = "renderExperienceBar", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
    private int experience_obliterator$hideExperienceLevel(GuiGraphics guiGraphics, Font font, String text,
                                                           int x, int y, int color, boolean dropShadow,
                                                           Operation<Integer> original) {
        if (ModConfig.get().hideExperienceLevel) {
            return 0;
        }
        return original.call(guiGraphics, font, text, x, y, color, dropShadow);
    }
    *///?}

    //? if >=26.1 {
    /*@Inject(method = "willPrioritizeExperienceInfo", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$keepLocatorBar(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().hideExperienceBar) {
            cir.setReturnValue(false);
        }
    }
    *///?}

    // (Neo)Forge lays out the health rows with leftHeight instead, see NeoForgeClientEvents and ForgeClientEvents
    //? if >=26.1 {
    /*@ModifyConstant(method = "extractPlayerHealth", constant = @Constant(intValue = 39), require = 0)
     *///?} else {
    @ModifyConstant(method = "renderPlayerHealth", constant = @Constant(intValue = 39), require = 0)
            //?}
    private int experience_obliterator$closeExperienceBarGap(int constant) {
        return ExperienceBarGap.shouldClose() ? constant - 7 : constant;
    }
}
