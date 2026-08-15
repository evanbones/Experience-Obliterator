package com.evandev.experience_obliterator.mixin.client.emi;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.recipe.special.EmiGrindstoneDisenchantingBookRecipe;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = EmiGrindstoneDisenchantingBookRecipe.class, remap = false)
public abstract class EmiGrindstoneDisenchantingBookRecipeMixin {

    @WrapOperation(
            method = "addWidgets",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/emi/emi/api/widget/WidgetHolder;addText(Lnet/minecraft/util/FormattedCharSequence;IIIZ)Ldev/emi/emi/api/widget/TextWidget;"
            )
    )
    private TextWidget experience_obliterator$modifyGrindstoneExperienceText(WidgetHolder instance, FormattedCharSequence text, int x, int y, int color, boolean shadow, Operation<TextWidget> original) {
        if (ModConfig.get().disableEmiExperienceDisplay) {
            return instance.addText(FormattedCharSequence.EMPTY, x, y, color, shadow);
        }
        return original.call(instance, text, x, y, color, shadow);
    }
}
