package com.evandev.experience_obliterator.forge.client;

//? if forge {
/*import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.client.ExperienceBarGap;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (!event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id()) || !ExperienceBarGap.shouldClose()) {
            return;
        }

        if (Minecraft.getInstance().gui instanceof ForgeGui gui) {
            gui.leftHeight -= 7;
            gui.rightHeight -= 7;
        }
    }
}
*///?}
