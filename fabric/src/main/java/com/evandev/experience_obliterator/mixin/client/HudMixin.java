package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class HudMixin {

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$hideExperienceBar(GuiGraphics guiGraphics, int x, CallbackInfo callback) {
        if (ModConfig.get().hideExperienceBar) {
            callback.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$hideExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo callback) {
        if (ModConfig.get().hideExperienceBar) {
            callback.cancel();
        }
    }

    @ModifyConstant(method = "renderPlayerHealth", constant = @Constant(intValue = 39))
    private int experience_obliterator$closeExperienceBarGap(int constant) {
        if (!ModConfig.get().hideExperienceBar) {
            return constant;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        boolean isMounted = player != null && player.jumpableVehicle() != null;
        return isMounted ? constant : constant - 7;
    }
}
