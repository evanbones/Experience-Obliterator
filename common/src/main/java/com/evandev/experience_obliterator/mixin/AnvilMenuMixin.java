package com.evandev.experience_obliterator.mixin;

import com.evandev.experience_obliterator.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AnvilMenu.class, priority = 1500)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {

    @Shadow
    @Final
    private DataSlot cost;

    @Shadow
    private String itemName;

    public AnvilMenuMixin(@Nullable MenuType<?> type, int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(type, containerId, inventory, access, null);
    }

    @ModifyExpressionValue(method = {"createResult", "createResultInternal"}, at = @At(value = "CONSTANT", args = "intValue=40"), require = 0)
    private int experience_obliterator$removeTooExpensiveLimit(int constant) {
        if (ModConfig.get().removeAnvilLimit) {
            return Integer.MAX_VALUE;
        }
        return constant;
    }

    @WrapOperation(method = {"createResult", "createResultInternal"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;set(I)V"))
    private void experience_obliterator$modifyIndependentAnvilCosts(DataSlot instance, int originalCost, Operation<Void> original) {
        ModConfig config = ModConfig.get();
        if (!config.noAnvilEnchantCost && !config.noAnvilRepairCost && !config.noAnvilRenameCost) {
            original.call(instance, originalCost);
            return;
        }

        ItemStack input1 = this.inputSlots.getItem(0);
        ItemStack input2 = this.inputSlots.getItem(1);

        if (input1.isEmpty()) {
            original.call(instance, originalCost);
            return;
        }

        int baseCost = input1.getOrDefault(DataComponents.REPAIR_COST, 0)
                + (input2.isEmpty() ? 0 : input2.getOrDefault(DataComponents.REPAIR_COST, 0));
        boolean isRenaming = this.itemName != null && !this.itemName.isEmpty() && !this.itemName.equals(input1.getHoverName().getString());
        int vanillaRenameCost = isRenaming ? 1 : 0;

        int vanillaActionCost = Math.max(0, originalCost - baseCost - vanillaRenameCost);

        boolean isMaterialRepair = input1.isDamageableItem() && input1.isValidRepairItem(input2);
        boolean isItemCombine = !input2.isEmpty() && input1.getItem() == input2.getItem();

        int finalCost = 0;

        if (isRenaming && !config.noAnvilRenameCost) {
            finalCost += vanillaRenameCost;
        }

        if (isMaterialRepair) {
            if (!config.noAnvilRepairCost) {
                finalCost += vanillaActionCost;
            }
        } else if (!input2.isEmpty()) {
            boolean didRepair = input1.isDamageableItem() && input1.getDamageValue() > 0 && isItemCombine;
            int repCost = 0;
            int enchCost = vanillaActionCost;

            if (didRepair && vanillaActionCost >= 2) {
                repCost = 2;
                enchCost = vanillaActionCost - 2;
            }

            if (!config.noAnvilRepairCost) finalCost += repCost;
            if (!config.noAnvilEnchantCost) finalCost += enchCost;
        } else {
            finalCost += vanillaActionCost;
        }

        if (finalCost > 0) {
            finalCost += baseCost;
        }

        original.call(instance, finalCost);
    }

    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void experience_obliterator$allowZeroCostPickup(Player player, boolean hasItem, CallbackInfoReturnable<Boolean> cir) {
        if (this.cost.get() <= 0 && hasItem) {
            cir.setReturnValue(true);
        }
    }
}
