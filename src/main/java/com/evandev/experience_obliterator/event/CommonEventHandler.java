package com.evandev.experience_obliterator.event;

import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockState blockState = event.getLevel().getBlockState(event.getPos());

        if (ModConfig.get().disableAnvil && blockState.is(BlockTags.ANVIL)) {
            event.setCanceled(true);
        }

        if (ModConfig.get().disableEnchantTable && blockState.is(Blocks.ENCHANTING_TABLE)) {
            event.setCanceled(true);
        }
    }
}
