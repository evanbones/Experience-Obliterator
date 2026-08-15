package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @ModifyConstant(method = {"extractPlayerHealth", "extractRenderState"}, constant = @Constant(intValue = 39), require = 0)
    private int experience_obliterator$closeExperienceBarGap(int constant) {
        if (!ModConfig.get().hideExperienceBar) {
            return constant;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        boolean isMounted = player != null && player.jumpableVehicle() != null;
        return isMounted ? constant : constant - 7;
    }
}
