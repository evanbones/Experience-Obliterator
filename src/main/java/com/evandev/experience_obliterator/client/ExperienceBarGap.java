package com.evandev.experience_obliterator.client;

import com.evandev.experience_obliterator.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import java.util.function.BooleanSupplier;

public class ExperienceBarGap {
    public static BooleanSupplier backportedLocatorBarVisible;

    public static boolean shouldClose() {
        if (!ModConfig.get().hideExperienceBar) {
            return false;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return true;
        }

        if (player.jumpableVehicle() != null) {
            return false;
        }

        //? if >=26.1 {
        /*return !player.connection.getWaypointManager().hasWaypoints();
        *///?} else {
        return backportedLocatorBarVisible == null || !backportedLocatorBarVisible.getAsBoolean();
        //?}
    }
}
