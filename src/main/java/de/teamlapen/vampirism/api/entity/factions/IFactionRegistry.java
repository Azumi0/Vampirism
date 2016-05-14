package de.teamlapen.vampirism.api.entity.factions;

import com.google.common.base.Predicate;
import de.teamlapen.vampirism.api.entity.player.IFactionPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Faction registry.
 * Register all extended properties that extend {@link IFactionPlayer} here
 * Currently only used for managing IPlayerEventListeners.
 */
public interface IFactionRegistry {


    /**
     * @return All factions after post init
     */
    IFaction[] getFactions();

    /**
     * @return All playable factions after post init
     */
    IPlayableFaction[] getPlayableFactions();

    /**
     * Get a cached or create a predicate which selects entities from other factions.
     * For all non EntityLivingBase entities the predicate is always false
     * @param thisFaction   The friendly faction
     * @param player        If players should be selected
     * @param mob     If non players should be selected
     * @param neutralPlayer If neutral playsers should be selected
     * @param otherFaction  If this is not null, only entities of this faction are selected.
     * @return
     */
    Predicate<Entity> getPredicate(IFaction thisFaction, boolean player, boolean mob, boolean neutralPlayer, IFaction otherFaction);

    /**
     * Get a cached or create a predicate which selects all other faction entities
     * For all non EntityLivingBase entities the predicate is always false
     * @param thisFaction
     * @return
     */
    Predicate<Entity> getPredicate(IFaction thisFaction);

    /**
     * Create and register a non playable faction. Has to be called before post-init
     *
     * @param name            Faction name e.g. for level command
     * @param entityInterface Interface all entities implement
     * @param color           Color e.g. for level rendering
     * @param <T>             Interface all entities implement
     * @return The created faction
     */
    <T extends IFactionEntity> IFaction registerFaction(String name, Class<T> entityInterface, int color);

    /**
     * Create and register a playable faction. Has to be called before post-init
     * @param name Faction name e.g. for level command
     * @param entityInterface Interface all entities or (the given capability for players) implement
     * @param color Color e.g. for level rendering
     * @param playerCapability The capability which is attached to all players
     * @param key Unique key, preferable the one used for registering the capability
     * @param highestLevel The highest reachable player level
     * @param <T> nterface all entities or (the given capability for players)  implement
     * @return The created faction
     */
    <T extends IFactionPlayer> IPlayableFaction registerPlayableFaction(String name, Class<T> entityInterface, int color, ResourceLocation key, Capability<T> playerCapability, int highestLevel);
}