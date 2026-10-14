package com.evandev.experience_obliterator.mixin.client.echochest;

import com.evandev.experience_obliterator.config.ModConfig;
//? if >=26.1 {
/*import fuzs.echochest.common.client.gui.screens.inventory.EchoChestScreen;
*///?} else {
import fuzs.echochest.client.gui.screens.inventory.EchoChestScreen;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EchoChestScreen.class)
public class EchoChestScreenMixin {

    //? if >=26.1 {
    /*@Inject(method = "extractTooltip", at = @At(value = "INVOKE", target = "Lfuzs/echochest/common/world/inventory/EchoChestMenu;getExperience()F", remap = false), cancellable = true)
    *///?} else {
    // render overrides a vanilla method, so only the call into Echo Chest's own menu skips remapping
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lfuzs/echochest/world/inventory/EchoChestMenu;getExperience()F", remap = false), cancellable = true)
    //?}
    private void experience_obliterator$hideExperienceTooltip(CallbackInfo ci) {
        if (ModConfig.get().disableEchoChestExperienceDisplay) {
            ci.cancel();
        }
    }
}
