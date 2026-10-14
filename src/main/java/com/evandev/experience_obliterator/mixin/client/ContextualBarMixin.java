package com.evandev.experience_obliterator.mixin.client;

//? if >=26.1 {
/*import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? if >=26.2 {
/^import net.minecraft.client.gui.contextualbar.ContextualBar;
^///?} else {
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.2 {
/^@Mixin(ContextualBar.class)
^///?} else {
@Mixin(ContextualBarRenderer.class)
//?}
public interface ContextualBarMixin {

    @Inject(method = "extractExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void experience_obliterator$hideExperienceLevel(GuiGraphicsExtractor graphics, Font font, int experienceLevel, CallbackInfo ci) {
        if (ModConfig.get().hideExperienceLevel) {
            ci.cancel();
        }
    }
}
*///?}
