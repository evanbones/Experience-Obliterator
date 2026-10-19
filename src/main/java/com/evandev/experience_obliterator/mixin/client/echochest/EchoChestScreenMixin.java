package com.evandev.experience_obliterator.mixin.client.echochest;

import com.evandev.experience_obliterator.config.ModConfig;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
//? if >=26.1 {
/*import fuzs.echochest.common.client.gui.screens.inventory.EchoChestScreen;
*///?} else {
import fuzs.echochest.client.gui.screens.inventory.EchoChestScreen;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded("echochest")
@Mixin(EchoChestScreen.class)
public class EchoChestScreenMixin {

    //? if >=26.1 {
    /*@Inject(method = "extractTooltip", at = @At(value = "INVOKE", target = "Lfuzs/echochest/common/world/inventory/EchoChestMenu;getExperience()F", remap = false), cancellable = true)
    *///?} else {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lfuzs/echochest/world/inventory/EchoChestMenu;getExperience()F", remap = false), cancellable = true)
    //?}
    private void experience_obliterator$hideExperienceTooltip(CallbackInfo ci) {
        if (ModConfig.get().disableEchoChestExperienceDisplay) {
            ci.cancel();
        }
    }
}
