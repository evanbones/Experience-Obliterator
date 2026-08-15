package com.evandev.experience_obliterator.mixin.client.echochest;

import com.evandev.experience_obliterator.config.ModConfig;
import fuzs.echochest.common.client.gui.screens.inventory.EchoChestScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EchoChestScreen.class, remap = false)
public class EchoChestScreenMixin {

    @Inject(method = "extractTooltip", at = @At(value = "INVOKE", target = "Lfuzs/echochest/common/world/inventory/EchoChestMenu;getExperience()F"), cancellable = true)
    private void experience_obliterator$hideExperienceTooltip(CallbackInfo ci) {
        if (ModConfig.get().disableEchoChestExperienceDisplay) {
            ci.cancel();
        }
    }
}
