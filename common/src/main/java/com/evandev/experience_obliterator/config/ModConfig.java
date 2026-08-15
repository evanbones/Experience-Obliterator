package com.evandev.experience_obliterator.config;

import com.evandev.experience_obliterator.Constants;
import com.evandev.experience_obliterator.platform.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = Services.PLATFORM.getConfigDirectory().resolve(Constants.MOD_ID + ".json").toFile();
    private static ModConfig INSTANCE;

    @SerializedName("disable_orb_spawn")
    public boolean disableOrbSpawn = true;

    @SerializedName("disable_orb_rendering")
    public boolean disableOrbRendering = true;

    @SerializedName("immediate_experience_pickup")
    public boolean immediateExperiencePickup = true;

    @SerializedName("hide_experience_bar")
    public boolean hideExperienceBar = true;

    @SerializedName("hide_experience_level")
    public boolean hideExperienceLevel = true;

    @SerializedName("hide_experience_bar_fill")
    public boolean hideExperienceBarFill = true;

    @SerializedName("disable_anvil")
    public boolean disableAnvil = false;

    @SerializedName("remove_anvil_limit")
    public boolean removeAnvilLimit = false;

    @SerializedName("no_anvil_enchant_cost")
    public boolean noAnvilEnchantCost = false;

    @SerializedName("no_anvil_repair_cost")
    public boolean noAnvilRepairCost = false;

    @SerializedName("no_anvil_rename_cost")
    public boolean noAnvilRenameCost = false;

    @SerializedName("disable_enchant_table")
    public boolean disableEnchantTable = false;

    @SerializedName("disable_xp_pickup_sound")
    public boolean disableXpPickupSound = true;

    @SerializedName("disable_xp_level_sound")
    public boolean disableXpLevelSound = true;

    @SerializedName("disable_emi_experience_display")
    public boolean disableEmiExperienceDisplay = true;

    @SerializedName("disable_echo_chest_experience_display")
    public boolean disableEchoChestExperienceDisplay = true;

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
