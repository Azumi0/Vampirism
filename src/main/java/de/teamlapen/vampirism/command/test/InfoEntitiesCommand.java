package de.teamlapen.vampirism.command.test;

import com.mojang.brigadier.builder.ArgumentBuilder;
import de.teamlapen.lib.lib.util.BasicCommand;
import de.teamlapen.vampirism.api.VReference;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

/**
 * @author Cheaterpaul, Maxanier
 */
public class InfoEntitiesCommand extends BasicCommand {
    public static final int maxSpawns = 50;

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("info-entities")
                .requires(context -> context.hasPermissionLevel(PERMISSION_LEVEL_ADMIN))
                .executes(context -> {
                    return infoEntities(context.getSource(), context.getSource().asPlayer());
                });
    }

    private static int infoEntities(CommandSource commandSource, ServerPlayerEntity asPlayer) {
        Object2IntMap<EntityClassification> object2intmap = asPlayer.func_71121_q().countEntities();
        commandSource.sendFeedback(new StringTextComponent(String.format("Creature: %d (%d), Monster: %s (%s), Hunter: %s (%s), Vampire: %s (%s)", object2intmap.getOrDefault(EntityClassification.CREATURE, 0), EntityClassification.CREATURE.getMaxNumberOfCreature(), object2intmap.getOrDefault(EntityClassification.MONSTER, 0), EntityClassification.MONSTER.getMaxNumberOfCreature(), object2intmap.getOrDefault(VReference.HUNTER_CREATURE_TYPE, 0), VReference.HUNTER_CREATURE_TYPE.getMaxNumberOfCreature(), object2intmap.getOrDefault(VReference.VAMPIRE_CREATURE_TYPE, 0), VReference.VAMPIRE_CREATURE_TYPE.getMaxNumberOfCreature())), true);
        return 0;
    }
}
