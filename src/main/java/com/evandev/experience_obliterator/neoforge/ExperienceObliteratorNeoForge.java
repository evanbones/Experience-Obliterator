package com.evandev.experience_obliterator.neoforge;

//? if neoforge {
import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.ExperienceObliteratorCommon;
import com.evandev.experience_obliterator.neoforge.client.NeoForgeConfigScreen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class ExperienceObliteratorNeoForge {
    public ExperienceObliteratorNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        //? if >=26.1 {
        /*if (FMLEnvironment.getDist().isClient()) {
        *///?} else {
        if (FMLEnvironment.dist.isClient()) {
        //?}
            NeoForgeConfigScreen.register(modContainer);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ExperienceObliteratorCommon.init();
    }
}
//?}
