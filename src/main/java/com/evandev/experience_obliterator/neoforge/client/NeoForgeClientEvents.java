package com.evandev.experience_obliterator.neoforge.client;

//? if neoforge {
import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.client.ExperienceBarGap;
import net.minecraft.client.Minecraft;
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

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
        if (!event.getName().equals(VanillaGuiLayers.HOTBAR) || !ExperienceBarGap.shouldClose()) {
            return;
        }

        //? if >=26.2 {
        /*Hud gui = Minecraft.getInstance().gui.hud;
        *///?} else {
        Gui gui = Minecraft.getInstance().gui;
        //?}
        gui.leftHeight -= 7;
        gui.rightHeight -= 7;
    }
}
//?}
