package com.gmail.trentech.pjw.commands;

import java.io.IOException;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.storage.WorldProperties;

import com.gmail.trentech.pjw.Main;
import com.gmail.trentech.pjw.io.SpongeData;
import com.gmail.trentech.pjw.io.WorldData;
import com.gmail.trentech.pjw.utils.ConfigManager;
import com.gmail.trentech.pjw.utils.Help;

public class CMDLoad implements CommandExecutor {

	public CMDLoad(){
		String alias = new ConfigManager().getConfig().getNode("Options", "Command-Alias", "world").getString();
		
		Help help = new Help("load", " Loads sepcified world. If world is a non Sponge created world you will need to specify a dimension type to import");
		help.setSyntax(" /world load <world> [type]\n /" + alias + " l <world> [type]");
		help.setExample(" /world load NewWorld\n /world load BukkitWorld overworld");
		CMDHelp.getList().add(help);
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(!args.hasAny("name")) {
			src.sendMessage(invalidArg());
			return CommandResult.empty();
		}

		String worldName = args.<String>getOne("name").get();
		
		if(Main.getGame().getServer().getWorld(worldName).isPresent()){
			src.sendMessage(Text.of(TextColors.DARK_RED, worldName, " is already loaded"));
			return CommandResult.empty();
		}

		SpongeData spongeData = new SpongeData(worldName);
		WorldProperties properties;
		if(!spongeData.exists()){
			src.sendMessage(Text.of(TextColors.YELLOW, "Foreign world detected"));
			if(!args.hasAny("type")){
				src.sendMessage(Text.of(TextColors.DARK_RED, "Must specify dimension type when importing worlds"));
				return CommandResult.empty();
			}
			String type = args.<String>getOne("type").get();
			
			if(!Main.getGame().getRegistry().getType(DimensionType.class, type).isPresent()){
				src.sendMessage(Text.of(TextColors.DARK_RED, "Invalid dimension type"));
				return CommandResult.empty();
			}
//			DimensionType dimensionType = Main.getGame().getRegistry().getType(DimensionType.class, type).get();
//
//			WorldCreationSettings settings = WorldCreationSettings.builder().name(worldName).dimension(dimensionType).enabled(true).keepsSpawnLoaded(true).loadsOnStartup(true).build();
//
//			Optional<WorldProperties> optProperties = Main.getGame().getServer().createWorldProperties(settings);
//
//	        if (!optProperties.isPresent()) {
//				src.sendMessage(Text.of(TextColors.DARK_RED, "something went wrong"));
//				return CommandResult.empty();
//	        }
//	        properties = optProperties.get();
			
			try {
				src.sendMessage(Text.of(TextColors.DARK_RED, "[WARNING]", TextColors.YELLOW, " Converting world to Sponge. This could break something"));
				spongeData.createNewConfig(type.toLowerCase());
				src.sendMessage(Text.of(TextColors.DARK_GREEN, "World will not load until next restart"));
				return CommandResult.success();
			} catch (IOException e) {
				src.sendMessage(Text.of(TextColors.DARK_RED, "Failed to convert world"));
				e.printStackTrace();
				return CommandResult.empty();
			}
		}else{
			if(!spongeData.isFreeDimId()){
				src.sendMessage(Text.of(TextColors.DARK_RED, "[WARNING]", TextColors.YELLOW, " World contains dimension id conflict. Attempting to repair."));
				try {
					spongeData.setDimId();
				} catch (IOException e) {
					src.sendMessage(Text.of(TextColors.DARK_RED, "Something went wrong"));
					e.printStackTrace();
				}
			}

			properties = Main.getGame().getServer().getWorldProperties(worldName).get();
		}

		WorldData worldData = new WorldData(worldName);
		
		if(!worldData.exists()){
			src.sendMessage(Text.of(TextColors.DARK_RED, worldName, " is not a valid world"));
			return CommandResult.empty();
		}
		
		if(!worldData.isCorrectLevelName()){
			src.sendMessage(Text.of(TextColors.DARK_RED, "[WARNING]", TextColors.YELLOW, " Level name mismatch. Attempting to repair."));
			try {
				worldData.setLevelName();
			} catch (IOException e) {
				src.sendMessage(Text.of(TextColors.DARK_RED, "Something went wrong"));
				e.printStackTrace();
			}
		}
		
		Optional<World> load = Main.getGame().getServer().loadWorld(properties);
		
		if(!load.isPresent()){
			src.sendMessage(Text.of(TextColors.DARK_RED, "Could not load ", worldName));
			return CommandResult.empty();
		}
		
		load.get().setKeepSpawnLoaded(true);
		
		src.sendMessage(Text.of(TextColors.DARK_GREEN, worldName, " loaded successfully"));
		return CommandResult.success();
	}
	
	private Text invalidArg(){
		Text t1 = Text.of(TextColors.YELLOW, "/world load ");
		Text t2 = Text.builder().color(TextColors.YELLOW).onHover(TextActions.showText(Text.of("Enter world or @w for current world"))).append(Text.of("<world> ")).build();
		Text t3 = Text.builder().color(TextColors.YELLOW).onHover(TextActions.showText(Text.of("Required for importing worlds"))).append(Text.of("[type]")).build();
		return Text.of(t1,t2,t3);
	}
}
