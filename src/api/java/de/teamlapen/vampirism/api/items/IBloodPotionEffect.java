package de.teamlapen.vampirism.api.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Interface for a Potion wrapper. Currently is not designed to be manually created. Will be created when registering something in {@link IBloodPotionRegistry}
 */
public interface IBloodPotionEffect {

    /**
     * @return If both effects can be added to the same blood potion
     */
    boolean canCoexist(@Nonnull IBloodPotionEffect other);

    /**
     * @return The id this effect has been registed with
     */
    ResourceLocation getId();

    /**
     * @return The localized name
     */
    ITextComponent getLocName(CompoundNBT properties);

    /**
     * Randomly selects duration and amplifier etc
     *
     * @return A nbt tag containing all properties which can be used to store the effect with the item
     */
    CompoundNBT getRandomProperties(Random rng);

    boolean isBad();

    /**
     * Called when this effect is activated
     *
     * @param propertyNbt  The nbt tag created in {@link IBloodPotionEffect#getRandomProperties(Random)}
     * @param durationMult The duration should be multiplied with this value
     */
    void onActivated(LivingEntity hunter, CompoundNBT propertyNbt, float durationMult);


}
