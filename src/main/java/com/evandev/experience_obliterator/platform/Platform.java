package com.evandev.experience_obliterator.platform;

import java.nio.file.Path;

//? if fabric {
/*import net.fabricmc.loader.api.FabricLoader;
*///?} else if neoforge {
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
//?} else {
/*import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
*///?}

public final class Platform {
    private Platform() {
    }

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    public static boolean isModLoaded(String modId) {
        //? if fabric {
        /*return FabricLoader.getInstance().isModLoaded(modId);
         *///?} else {
        return ModList.get().isLoaded(modId);
        //?}
    }

    /**
     * Gets the configuration directory for the current platform.
     *
     * @return The path to the config directory.
     */
    public static Path getConfigDirectory() {
        //? if fabric {
        /*return FabricLoader.getInstance().getConfigDir();
         *///?} else {
        return FMLPaths.CONFIGDIR.get();
        //?}
    }
}
