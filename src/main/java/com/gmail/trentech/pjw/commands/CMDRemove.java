package com.gmail.trentech.pjw.commands;

import java.util.concurrent.ExecutionException;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.storage.WorldProperties;

import com.gmail.trentech.pjw.utils.Zip;

public class CMDRemove implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		WorldProperties properties = args.<WorldProperties> getOne("world").get();

		if (Sponge.getServer().getWorld(properties.getWorldName()).isPresent()) {
			throw new CommandException(Text.of(TextColors.RED, properties.getWorldName(), " must be unloaded before you can delete"), false);
		}

		new Zip(properties.getWorldName()).save();

		try {
			if (Sponge.getServer().deleteWorld(properties).get()) {

				src.sendMessage(Text.of(TextColors.DARK_GREEN, properties.getWorldName(), " deleted successfully"));

				return CommandResult.success();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		src.sendMessage(Text.of(TextColors.RED, "Could not delete ", properties.getWorldName()));

		return CommandResult.empty();
	}

}