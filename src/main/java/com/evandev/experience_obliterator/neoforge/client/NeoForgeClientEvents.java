package com.evandev.experience_obliterator.neoforge.client;

//? if neoforge {
import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
//? if >=26.2 {
/*import net.minecraft.client.gui.Hud;
*///?} else {
import net.minecraft.client.gui.Gui;
//?}
//? if >=26.1 {
/*import net.minecraft.resources.Identifier;
*///?} else {
import net.minecraft.resources.ResourceLocation;
//?}

// From 26.1 the bar and level are hidden by the common contextual bar mixins; NeoForge still lays out the health rows with leftHeight
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
        boolean hideExperienceBar = ModConfig.get().hideExperienceBar;
        boolean hideExperienceLevel = ModConfig.get().hideExperienceLevel;

        if (!hideExperienceBar && !hideExperienceLevel) {
            return;
        }

        //? if >=26.1 {
        /*Identifier layer = event.getName();
        *///?} else {
        ResourceLocation layer = event.getName();
        //?}

        if (hideExperienceBar && layer.equals(VanillaGuiLayers.HOTBAR)) {
            LocalPlayer player = Minecraft.getInstance().player;
            //? if >=26.2 {
            /*Hud gui = Minecraft.getInstance().gui.hud;
            *///?} else {
            Gui gui = Minecraft.getInstance().gui;
            //?}
            boolean isMounted = player != null && player.jumpableVehicle() != null;

            gui.leftHeight -= 7;
            gui.rightHeight -= 7;

            if (isMounted) {
                gui.leftHeight += 7;
                gui.rightHeight += 7;
            }
        }

        //? if <26.1 {
        if (hideExperienceBar && layer.equals(VanillaGuiLayers.EXPERIENCE_BAR)) {
            event.setCanceled(true);
        }

        if (hideExperienceLevel && layer.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            event.setCanceled(true);
        }
        //?}
    }
}
//?}
