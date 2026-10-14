package com.evandev.experience_obliterator.forge.client;

//? if forge {
/*import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The bar and level themselves are hidden by HudMixin; Forge positions the health/food rows via leftHeight/rightHeight instead of vanilla's constant
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (!ModConfig.get().hideExperienceBar || !event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
            return;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        boolean isMounted = player != null && player.jumpableVehicle() != null;

        if (!isMounted && Minecraft.getInstance().gui instanceof ForgeGui gui) {
            gui.leftHeight -= 7;
            gui.rightHeight -= 7;
        }
    }
}
*///?}
