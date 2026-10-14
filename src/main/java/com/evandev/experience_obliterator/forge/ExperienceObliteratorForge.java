package com.evandev.experience_obliterator.forge;

//? if forge {
/*import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.ExperienceObliteratorCommon;
import com.evandev.experience_obliterator.forge.client.ForgeConfigScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class ExperienceObliteratorForge {
    public ExperienceObliteratorForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        if (FMLEnvironment.dist.isClient()) {
            ForgeConfigScreen.register();
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ExperienceObliteratorCommon.init();
    }
}
*///?}
