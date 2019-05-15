package de.teamlapen.vampirism.command.test;

import com.mojang.brigadier.builder.ArgumentBuilder;

import de.teamlapen.lib.lib.util.BasicCommand;
import de.teamlapen.vampirism.api.entity.hunter.IHunter;
import de.teamlapen.vampirism.api.entity.vampire.IVampire;
import de.teamlapen.vampirism.tileentity.TileTotem;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.List;

/**
 * 
 * @authors Cheaterpaul, Maxanier
 */
public class MakeVillagerAgressiveCommand extends BasicCommand {

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("makeVillagerAgressive")
                .requires(context -> context.hasPermissionLevel(PERMISSION_LEVEL_ADMIN))
                .executes(context -> {
                    return makeVillagerAgressive(context.getSource().asPlayer());
                });
    }

    private static int makeVillagerAgressive(EntityPlayerMP asPlayer) {
        List<EntityVillager> l = asPlayer.getEntityWorld().getEntitiesWithinAABB(EntityVillager.class, asPlayer.getBoundingBox().grow(3, 2, 3));
        for (EntityVillager v : l) {
            if (v instanceof IHunter || v instanceof IVampire) continue;
            TileTotem.makeAggressive(v, null);

        }
        return 0;
    }
}