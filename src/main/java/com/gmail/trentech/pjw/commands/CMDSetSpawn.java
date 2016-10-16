package com.gmail.trentech.pjw.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.storage.WorldProperties;

import com.flowpowered.math.vector.Vector3i;
import com.gmail.trentech.pjw.utils.Help;

public class CMDSetSpawn implements CommandExecutor {

	public CMDSetSpawn() {
		new Help("world setspawn", "setspawn", "Sets the spawn point of specified world. If no arguments present sets spawn of current world to player location", false)
			.setPermission("pjw.cmd.world.setspawn")
			.setUsage("/world setspawn <world> <x,y,z>\n /w s <world> <x,y,z>")
			.setExample("/world setspawn\n /world setspawn MyWorld -153,75,300")
			.save();
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!args.hasAny("world")) {
			if(src instanceof Player) {
				WorldProperties properties = ((Player) src).getWorld().getProperties();
				Location<World> location = ((Player) src).getLocation();
				
				properties.setSpawnPosition(location.getBlockPosition());
				
				src.sendMessage(Text.of(TextColors.DARK_GREEN, "Set spawn of world ", properties.getWorldName(), " to x: ", properties.getSpawnPosition().getX(), ", y: ", properties.getSpawnPosition().getY(), ", z: ", properties.getSpawnPosition().getZ()));
				return CommandResult.success();
			} else {
				throw new CommandException(Text.of(TextColors.RED, "Must be a player"), false);
			}
		}
		WorldProperties properties = args.<WorldProperties> getOne("world").get();

		String[] coords = args.<String> getOne("value").get().split(",");

		if (!isValidLocation(coords)) {
			throw new CommandException(Text.of(TextColors.RED, coords.toString(), " is not valid"), true);
		}

		properties.setSpawnPosition(new Vector3i().add(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])));

		src.sendMessage(Text.of(TextColors.DARK_GREEN, "Set spawn of world ", properties.getWorldName(), " to x: ", properties.getSpawnPosition().getX(), ", y: ", properties.getSpawnPosition().getY(), ", z: ", properties.getSpawnPosition().getZ()));

		return CommandResult.success();
	}

	private boolean isValidLocation(String[] coords) {
		if (coords == null) {
			return false;
		}

		for (String coord : coords) {
			try {
				Integer.parseInt(coord);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}
