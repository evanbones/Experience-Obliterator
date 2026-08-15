package com.evandev.experience_obliterator;

import com.evandev.experience_obliterator.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ExperienceObliteratorFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ExperienceObliteratorCommon.init();

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockState blockState = level.getBlockState(hitResult.getBlockPos());

            if (ModConfig.get().disableAnvil && blockState.is(BlockTags.ANVIL)) {
                return InteractionResult.FAIL;
            }

            if (ModConfig.get().disableEnchantTable && blockState.is(Blocks.ENCHANTING_TABLE)) {
                return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });
    }
}
