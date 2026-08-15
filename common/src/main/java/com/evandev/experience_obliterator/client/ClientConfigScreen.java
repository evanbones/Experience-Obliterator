package com.evandev.experience_obliterator.client;

import com.evandev.experience_obliterator.config.ModConfig;
import com.evandev.experience_obliterator.platform.Services;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClientConfigScreen {

    public static Screen create(Screen parent) {
        ConfigCategory.Builder experienceCategoryBuilder = ConfigCategory.createBuilder()
                .name(Component.translatable("category.experience_obliterator.general"))
                .option(createBoolOption("disable_orb_spawn", true,
                        () -> ModConfig.get().disableOrbSpawn,
                        val -> ModConfig.get().disableOrbSpawn = val))
                .option(createBoolOption("disable_orb_rendering", true,
                        () -> ModConfig.get().disableOrbRendering,
                        val -> ModConfig.get().disableOrbRendering = val))
                .option(createBoolOption("immediate_experience_pickup", true,
                        () -> ModConfig.get().immediateExperiencePickup,
                        val -> ModConfig.get().immediateExperiencePickup = val))
                .option(createBoolOption("hide_experience_bar", true,
                        () -> ModConfig.get().hideExperienceBar,
                        val -> ModConfig.get().hideExperienceBar = val))
                .option(createBoolOption("hide_experience_level", true,
                        () -> ModConfig.get().hideExperienceLevel,
                        val -> ModConfig.get().hideExperienceLevel = val))
                .option(createBoolOption("hide_experience_bar_fill", true,
                        () -> ModConfig.get().hideExperienceBarFill,
                        val -> ModConfig.get().hideExperienceBarFill = val))
                .option(createBoolOption("disable_anvil", false,
                        () -> ModConfig.get().disableAnvil,
                        val -> ModConfig.get().disableAnvil = val))
                .option(createBoolOption("disable_enchant_table", false,
                        () -> ModConfig.get().disableEnchantTable,
                        val -> ModConfig.get().disableEnchantTable = val))
                .option(createBoolOption("disable_xp_pickup_sound", true,
                        () -> ModConfig.get().disableXpPickupSound,
                        val -> ModConfig.get().disableXpPickupSound = val))
                .option(createBoolOption("disable_xp_level_sound", true,
                        () -> ModConfig.get().disableXpLevelSound,
                        val -> ModConfig.get().disableXpLevelSound = val));

        if (Services.PLATFORM.isModLoaded("emi")) {
            experienceCategoryBuilder.option(createBoolOption("disable_emi_experience_display", true,
                    () -> ModConfig.get().disableEmiExperienceDisplay,
                    val -> ModConfig.get().disableEmiExperienceDisplay = val));
        }

        if (Services.PLATFORM.isModLoaded("echochest")) {
            experienceCategoryBuilder.option(createBoolOption("disable_echo_chest_experience_display", true,
                    () -> ModConfig.get().disableEchoChestExperienceDisplay,
                    val -> ModConfig.get().disableEchoChestExperienceDisplay = val));
        }

        ConfigCategory experienceCategory = experienceCategoryBuilder.build();

        ConfigCategory anvilCategory = ConfigCategory.createBuilder()
                .name(Component.translatable("category.experience_obliterator.anvil"))
                .option(createBoolOption("remove_anvil_limit", false,
                        () -> ModConfig.get().removeAnvilLimit,
                        val -> ModConfig.get().removeAnvilLimit = val))
                .option(createBoolOption("no_anvil_enchant_cost", false,
                        () -> ModConfig.get().noAnvilEnchantCost,
                        val -> ModConfig.get().noAnvilEnchantCost = val))
                .option(createBoolOption("no_anvil_repair_cost", false,
                        () -> ModConfig.get().noAnvilRepairCost,
                        val -> ModConfig.get().noAnvilRepairCost = val))
                .option(createBoolOption("no_anvil_rename_cost", false,
                        () -> ModConfig.get().noAnvilRenameCost,
                        val -> ModConfig.get().noAnvilRenameCost = val))
                .build();

        YetAnotherConfigLib.Builder builder = YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("config.experience_obliterator.title"))
                .category(experienceCategory)
                .category(anvilCategory)
                .save(ModConfig::save);

        return builder.build().generateScreen(parent);
    }

    private static Option<Boolean> createBoolOption(String name, boolean defaultValue,
                                                    Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(Component.translatable("config.experience_obliterator.option." + name))
                .description(describe(name))
                .binding(defaultValue, getter, setter)
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    private static OptionDescription describe(String name) {
        MutableComponent tooltip = Component.translatable("config.experience_obliterator.option." + name + ".tooltip");
        return OptionDescription.of(tooltip);
    }
}
