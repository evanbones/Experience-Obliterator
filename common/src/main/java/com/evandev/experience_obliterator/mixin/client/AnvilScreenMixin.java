package com.evandev.experience_obliterator.mixin.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AnvilScreen.class, priority = 1500)
public class AnvilScreenMixin {

    @ModifyExpressionValue(method = "extractLabels", at = @At(value = "CONSTANT", args = "intValue=40"), require = 0)
    private int experience_obliterator$hideTooExpensiveText(int constant) {
        if (ModConfig.get().removeAnvilLimit) {
            return Integer.MAX_VALUE;
        }
        return constant;
    }
}
