package com.evandev.experience_obliterator.client;

import com.evandev.experience_obliterator.config.ModConfig;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Supplier;

import dev.isxander.yacl3.api.ConfigCategory;

public class ClientConfigScreen {

    public static Screen create(Screen parent) {
        ConfigCategory experienceCategory = ConfigCategory.createBuilder()
                .name(Component.translatable("category.experience_obliterator.general"))
                .option(createBoolOption("disable_orb_spawn", false,
                        () -> ModConfig.get().disableOrbSpawn,
                        val -> ModConfig.get().disableOrbSpawn = val))
                .option(createBoolOption("disable_orb_rendering", false,
                        () -> ModConfig.get().disableOrbRendering,
                        val -> ModConfig.get().disableOrbRendering = val))
                .option(createBoolOption("immediate_experience_pickup", false,
                        () -> ModConfig.get().immediateExperiencePickup,
                        val -> ModConfig.get().immediateExperiencePickup = val))
                .option(createBoolOption("hide_experience_bar", false,
                        () -> ModConfig.get().hideExperienceBar,
                        val -> ModConfig.get().hideExperienceBar = val))
                .option(createBoolOption("disable_anvil", false,
                        () -> ModConfig.get().disableAnvil,
                        val -> ModConfig.get().disableAnvil = val))
                .option(createBoolOption("disable_enchant_table", false,
                        () -> ModConfig.get().disableEnchantTable,
                        val -> ModConfig.get().disableEnchantTable = val))
                .option(createBoolOption("disable_xp_pickup_sound", false,
                        () -> ModConfig.get().disableXpPickupSound,
                        val -> ModConfig.get().disableXpPickupSound = val))
                .option(createBoolOption("disable_xp_level_sound", false,
                        () -> ModConfig.get().disableXpLevelSound,
                        val -> ModConfig.get().disableXpLevelSound = val))
                .option(createBoolOption("disable_emi_experience_display", false,
                        () -> ModConfig.get().disableEmiExperienceDisplay,
                        val -> ModConfig.get().disableEmiExperienceDisplay = val))
                .build();

        YetAnotherConfigLib.Builder builder = YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("config.experience_obliterator.title"))
                .category(experienceCategory)
                .save(ModConfig::save);

        return builder.build().generateScreen(parent);
    }

    private static Option<Boolean> createBoolOption(String name, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return createBoolOption(name, defaultValue, true, getter, setter);
    }

    private static Option<Boolean> createBoolOption(String name, boolean defaultValue, boolean available,
                                                    Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(Component.translatable("config.experience_obliterator.option." + name))
                .description(describe(name, available))
                .available(available)
                .binding(defaultValue, getter, setter)
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    private static Option<Float> createFloatOption(String name, float defaultValue, float min, float max, float step, Supplier<Float> getter, Consumer<Float> setter) {
        return createFloatOption(name, defaultValue, min, max, step, true, getter, setter);
    }

    private static Option<Float> createFloatOption(String name, float defaultValue, float min, float max, float step,
                                                   boolean available, Supplier<Float> getter, Consumer<Float> setter) {
        return Option.<Float>createBuilder()
                .name(Component.translatable("config.experience_obliterator.option." + name))
                .description(describe(name, available))
                .available(available)
                .binding(defaultValue, getter, setter)
                .controller(opt -> FloatSliderControllerBuilder.create(opt).range(min, max).step(step))
                .build();
    }

    private static OptionDescription describe(String name, boolean available) {
        MutableComponent tooltip = Component.translatable("config.experience_obliterator.option." + name + ".tooltip");
        if (available) {
            return OptionDescription.of(tooltip);
        }
        return OptionDescription.of(tooltip
                .append(CommonComponents.NEW_LINE)
                .append(CommonComponents.NEW_LINE)
                .append(Component.translatable("config.experience_obliterator.requires_create").withStyle(ChatFormatting.GRAY)));
    }

    private static Option<Integer> createIntOption(String name, int defaultValue, int min, int max, int step, Supplier<Integer> getter, Consumer<Integer> setter) {
        return Option.<Integer>createBuilder()
                .name(Component.translatable("config.experience_obliterator.option." + name))
                .description(describe(name, true))
                .binding(defaultValue, getter, setter)
                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(min, max).step(step))
                .build();
    }
}
