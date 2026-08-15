package com.evandev.experience_obliterator.mixin.echochest;

import com.evandev.experience_obliterator.config.ModConfig;
import fuzs.echochest.common.world.inventory.EchoChestMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EchoChestMenu.class, remap = false)
public class EchoChestMenuMixin {

    @Inject(method = "validBottleItem", at = @At("HEAD"), cancellable = true)
    private static void experience_obliterator$disableBottleSlot(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().disableEchoChestExperienceDisplay) {
            cir.setReturnValue(false);
        }
    }
}
