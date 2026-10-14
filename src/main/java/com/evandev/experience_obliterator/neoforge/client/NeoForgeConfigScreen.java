package com.evandev.experience_obliterator.neoforge.client;

//? if neoforge {
import com.evandev.experience_obliterator.client.ClientConfigScreen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class NeoForgeConfigScreen {
    public static void register(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (c, parent) -> ClientConfigScreen.create(parent));
    }
}
//?}
