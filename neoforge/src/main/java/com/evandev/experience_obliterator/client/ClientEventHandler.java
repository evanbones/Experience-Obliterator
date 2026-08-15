package com.evandev.experience_obliterator.client;

import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
        boolean hideExperienceBar = ModConfig.get().hideExperienceBar;
        boolean hideExperienceLevel = ModConfig.get().hideExperienceLevel;

        if (!hideExperienceBar && !hideExperienceLevel) {
            return;
        }

        ResourceLocation layer = event.getName();

        if (hideExperienceBar && layer.equals(VanillaGuiLayers.HOTBAR)) {
            LocalPlayer player = Minecraft.getInstance().player;
            Gui gui = Minecraft.getInstance().gui;
            boolean isMounted = player != null && player.jumpableVehicle() != null;

            gui.leftHeight -= 7;
            gui.rightHeight -= 7;

            if (isMounted) {
                gui.leftHeight += 7;
                gui.rightHeight += 7;
            }
        }

        if (hideExperienceBar && layer.equals(VanillaGuiLayers.EXPERIENCE_BAR)) {
            event.setCanceled(true);
        }

        if (hideExperienceLevel && layer.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            event.setCanceled(true);
        }
    }
}
