package dev.dassen.desolatedungeons.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;

public class Augment {
    public static final Codec<Augment> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            AugmentFunction.CODEC.fieldOf("augment_function").forGetter((augment -> augment.augmentFunction))
        )
        .apply(instance, Augment::new)
    );

    public String name;
    public final AugmentFunction augmentFunction;

    public Augment(AugmentFunction augmentFunction) {
        this.augmentFunction = augmentFunction;
    }

    public static MutableText getName(RegistryEntry.Reference<Augment> augment) {
        return Text.translatable(getTranslationKey(augment));
    }

    public static MutableText getDescription(RegistryEntry.Reference<Augment> augment) {
        return Text.translatable(getTranslationKeyWithSuffix(augment, "description"));
    }

    public static Text toHoverableText(RegistryEntry.Reference<Augment> augment) {
        MutableText name = Text.empty().append(getName(augment));
        MutableText description = Text.empty().append(getDescription(augment));
        MutableText identifier = Text.empty().append(augment.getIdAsString());
        MutableText bracketedName = Texts.bracketed(name);
        MutableText hoveredText = Text.empty()
            .append(name)
            .append("\n\n")
            .append(description.formatted(Formatting.DARK_GRAY))
            .append("\n\n")
            .append(identifier.formatted(Formatting.DARK_GRAY));

        bracketedName.styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoveredText)));

        return bracketedName;
    }

    private static String getTranslationKey(RegistryEntry.Reference<Augment> augment) {
        return augment.registryKey().getValue().toTranslationKey("desolate_dungeons.augment");
    }

    private static String getTranslationKeyWithSuffix(RegistryEntry.Reference<Augment> augment, String suffix) {
        return augment.registryKey().getValue().toTranslationKey("desolate_dungeons.augment", suffix);
    }
}
