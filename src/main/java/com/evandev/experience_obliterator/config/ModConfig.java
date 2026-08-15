package com.evandev.experience_obliterator.config;

import com.evandev.experience_obliterator.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + ".json").toFile();
    private static ModConfig INSTANCE;

    @SerializedName("disable_orb_spawn")
    public boolean disableOrbSpawn = true;

    @SerializedName("disable_orb_rendering")
    public boolean disableOrbRendering = true;

    @SerializedName("immediate_experience_pickup")
    public boolean immediateExperiencePickup = true;

    @SerializedName("hide_experience_bar")
    public boolean hideExperienceBar = true;

    @SerializedName("disable_anvil")
    public boolean disableAnvil = false;

    @SerializedName("disable_enchant_table")
    public boolean disableEnchantTable = false;

    @SerializedName("disable_xp_pickup_sound")
    public boolean disableXpPickupSound = true;

    @SerializedName("disable_xp_level_sound")
    public boolean disableXpLevelSound = true;

    @SerializedName("disable_emi_experience_display")
    public boolean disableEmiExperienceDisplay = true;

    public static ModConfig get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
                if (INSTANCE == null) {
                    INSTANCE = new ModConfig();
                }
            } catch (Exception e) {
                Constants.LOG.error("Failed to load " + Constants.MOD_ID + ".json", e);
                INSTANCE = new ModConfig();
                save();
            }
        } else {
            INSTANCE = new ModConfig();
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            Constants.LOG.error("Failed to save " + Constants.MOD_ID + ".json", e);
        }
    }
}
