package com.evandev.experience_obliterator.mixin.client.vanillabackport;

//? if <26.1 {
import com.blackgear.vanillabackport.client.api.modules.waypoints.LocatorBarRenderer;
import com.evandev.experience_obliterator.client.ExperienceBarGap;
import com.evandev.experience_obliterator.config.ModConfig;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@IfModLoaded("vanillabackport")
@Mixin(value = LocatorBarRenderer.class, remap = false)
public abstract class LocatorBarRendererMixin {

    @Shadow
    private boolean shouldSkipRendering() {
        throw new UnsupportedOperationException();
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void experience_obliterator$trackVisibility(CallbackInfo ci) {
        ExperienceBarGap.backportedLocatorBarVisible = () -> !this.shouldSkipRendering();
    }

    @Inject(method = "willPrioritizeExperienceInfo", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$keepLocatorBar(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().hideExperienceBar) {
            cir.setReturnValue(false);
        }
    }
}
//?}
