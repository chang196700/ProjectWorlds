package com.gmail.trentech.pjw.utils;

import org.spongepowered.api.Sponge;

import com.gmail.trentech.helpme.help.Argument;
import com.gmail.trentech.helpme.help.Help;
import com.gmail.trentech.helpme.help.Usage;

public class CommandHelp {

	public static void init() {
		if (Sponge.getPluginManager().isLoaded("helpme")) {
			Usage usageCopy = new Usage(Argument.of("<oldWorld>", "Specifies the name of world you want to copy"))
					.addArgument(Argument.of("<newWorld>", "Enter the name of the new world"));
			
			Help worldCopy = new Help("world copy", "copy", "Allows you to make a new world from an existing world")
					.setPermission("pjw.cmd.world.copy")
					.setUsage(usageCopy)
					.addExample("/world copy srcWorld newWorld");
			
			Usage usageCreate = new Usage(Argument.of("<world>", "Specifies the name of the world"))
					.addArgument(Argument.of("[-d <dimensionType>]", "Sets the DimensionType. Vanilla types are minecraft:overworld, minecraft:nether and minecraft:the_end"))
					.addArgument(Argument.of("[-g <generatorType>]", "Sets the GeneratorType. Vanilla types are OVERWORLD, NETHER, THE_END, LARGE_BIOMES, FLAT and AMPLIFIED"))
					.addArgument(Argument.of("[-m <modifer>]", "Sets the WorldGeneratorModifer. Sponge modifers included by Sponge are sponge:void and sponge:skylands"))
					.addArgument(Argument.of("[-s <seed>]", "Sets the seed. If not specified this will default to using a random seed."));
			
			Help worldCreate = new Help("world create", "create", "Allows you to creating new worlds with a combination of features. This does not automatically load newly created worlds.")
					.setPermission("pjw.cmd.world.create")
					.setUsage(usageCreate)
					.addExample("/world create NewWorld -d minecraft:overworld -g overworld")
					.addExample("/world create NewWorld -d minecraft:nether -m sponge:skylands")
					.addExample("/world create NewWorld -s -12309830198412353456");
			
			Usage usageRemove = new Usage(Argument.of("<world>", "Specifies the targetted world"));
			
			Help worldRemove = new Help("world remove", "remove", "Delete worlds you no longer need. Worlds must be unloaded before you can delete them")
					.setPermission("pjw.cmd.world.remove")
					.setUsage(usageRemove)
					.addExample("/world remove OldWorld");
			
			Usage usageDifficulty = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[difficulty]", "Specifies the new difficulty level. Must be one of the following: PEACEFUL, EASY, NORMAL, HARD"));
			
			Help worldDifficulty = new Help("world difficulty", "difficulty", "Set the difficulty level for each world")
					.setPermission("pjw.cmd.world.difficulty")
					.setUsage(usageDifficulty)
					.addExample("/world difficulty MyWorld HARD")
					.addExample("/world difficulty MyWorld");
			
			Usage usageEnable = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[true|false]"));
			
			Help worldEnable = new Help("world enable", "enable", "Enable and disable worlds from loading")
					.setPermission("pjw.cmd.world.enable")
					.setUsage(usageEnable)
					.addExample("/world enable MyWorld false")
					.addExample("/world enable MyWorld true");
			
			Usage usageFill = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("<diameter>", "Set the diameter of the world border or enter 'stop' to cancel already running process. The specified diameter applies to the x and z axis. The world border extends over the entire y-axis"))
					.addArgument(Argument.of("[interval]", "Sets the interval between generation runs. Must be greater than 0. Default is 10."));

			Help worldFill = new Help("world fill", "fill", "Pre generate chunks in a world outwards from center spawn")
					.setPermission("pjw.cmd.world.fill")
					.setUsage(usageFill)
					.addExample("/world fill MyWorld stop")
					.addExample("/world fill MyWorld 1000");
			
			Usage usageGamemode = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[gamemode]", "Must be one of the following: survival(0), creative(1), adventure(2), spectator(3)"));
			
			Help worldGamemode = new Help("world gamemode", "gamemode", "Change gamemode of the specified world")
					.setPermission("pjw.cmd.world.gamemode")
					.setUsage(usageGamemode)
					.addExample("/world gamemode MyWorld SURVIVAL")
					.addExample("/world gamemode");
			
			Usage usageGamerule = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[rule]", "Specifies the game rule to set or query. May be any value, but only certain predefined game rules will affect gameplay."))
					.addArgument(Argument.of("[value]", "Specifies the value to set the game rule to. May be any value, though only true or false specified for predefined game rules will actually affect gameplay, except in the case of randomTickSpeed and spawnRadius, where any integer 0 or greater will affect gameplay"));
			
			Help worldGamerule = new Help("world gamerule", "gamerule", " Configure varies world properties")
					.setPermission("pjw.cmd.world.gamerule")
					.setUsage(usageGamerule)
					.addExample("/world gamerule MyWorld mobGriefing false")
					.addExample("/world gamerule MyWorld");

			Help worldHardcore = new Help("world hardcore", "hardcore", "Toggle on and off hardcore mode for world")
					.setPermission("pjw.cmd.world.hardcore")
					.setUsage(usageEnable)
					.addExample("/world hardcore MyWorld false")
					.addExample("/world hardcore MyWorld");
			
			Usage usageTime = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[time]", "Sets the time of day, in ticks. The total number of ticks in a day is 24000, however this value does not reset to zero at the start of each day but rather keeps counting passed 24000."));
					
			Help worldTime = new Help("world time", "time", "Set the time of world")
					.setPermission("pjw.cmd.world.time")
					.setUsage(usageTime)
					.addExample("/world time MyWorld 6000")
					.addExample("/world time MyWorld");
			
			Usage usageWeather = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[clear|rain|thunder]", "clear: Set the weather to clear, rain: Set the weather to rain (or snow in cold biomes), thunder: Set the weather to a thunderstorm (or a thunder snowstorm in cold biomes)"))
					.addArgument(Argument.of("[duration]", "Specifies the duration the set weather will occur."));
			
			Help worldWeather = new Help("world weather", "weather", "Set the weather of world")
					.setPermission("pjw.cmd.world.weather")
					.setUsage(usageWeather)
					.addExample("/world weather MyWorld clear")
					.addExample("/world weather MyWorld");
			
			Usage usageImport = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("<dimensionType>", "Sets the DimensionType. Vanilla types are minecraft:overworld, minecraft:nether and minecraft:the_end"))
					.addArgument(Argument.of("<generatorType>", "Sets the GeneratorType. Vanilla types are OVERWORLD, NETHER, THE_END, LARGE_BIOMES, FLAT and AMPLIFIED"))
					.addArgument(Argument.of("[modifer]", "Sets the WorldGeneratorModifer. Sponge modifers included by Sponge are sponge:void and sponge:skylands"));
			
			Help worldImport = new Help("world import", "import", "Import worlds not native to Sponge")
					.setPermission("pjw.cmd.world.import")
					.setUsage(usageImport)
					.addExample("/world import NewWorld minecraft:overworld overworld sponge:void")
					.addExample("/world import NewWorld minecraft:overworld overworld");
			
			
			Help worldKeepSpawnLoaded = new Help("world keepspawnloaded", "keepspawnloaded", "Keeps spawn point of world loaded in memory")
					.setPermission("pjw.cmd.world.keepspawnloaded")
					.setUsage(usageEnable)
					.addExample("/world keepspawnloaded MyWorld true")
					.addExample("/world keepspawnloaded MyWorld");
			
			Help worldLoadOnStartup = new Help("world loadonstartup", "loadonstartup", "Set whether to load world on startup")
					.setPermission("pjw.cmd.world.loadonstartup")
					.setUsage(usageEnable)
					.addExample("/world loadonstartup MyWorld true")
					.addExample("/world loadonstartup MyWorld");
			
			Help worldList = new Help("world list", "list", "Lists all known worlds, loaded or unloaded")
					.setPermission("pjw.cmd.world.list");
			
			Usage usageLoad = new Usage(Argument.of("<world>", "Specifies the targetted world"));
			
			Help worldLoad = new Help("world load", "load", "Loads sepcified world. If world is a non Sponge created world you will need  to /world import")
					.setPermission("pjw.cmd.world.load")
					.setUsage(usageLoad)
					.addExample("/world load BukkitWorld overworld")
					.addExample("/world load NewWorld");
			
			Help worldProperties = new Help("world properties", "properties", "View all properties associated with a world")
					.setPermission("pjw.cmd.world.properties")
					.setUsage(usageLoad)
					.addExample("/world properties MyWorld")
					.addExample("/world properties");
			
			Help worldPvp = new Help("world pvp", "pvp", "Toggle on and off pvp for world")
					.setPermission("pjw.cmd.world.pvp")
					.setUsage(usageEnable)
					.addExample("/world pvp MyWorld true")
					.addExample("/world pvp MyWorld");
			
			Help worldRegen = new Help("world regen", "regen", "Regenerates a world. You can preserve the seed or generate new random")
					.setPermission("pjw.cmd.world.regen")
					.setUsage(usageEnable)
					.addExample("/world regen MyWorld true")
					.addExample("/world regen MyWorld");
			
			Usage usageRename = new Usage(Argument.of("<srcWorld>", "Specifies the targetted world"))
					.addArgument(Argument.of("<newWorld>", "Specifies the new world name"));
			
			Help worldRename = new Help("world rename", "rename", "Allows for renaming worlds. World must be unloaded before you can rename world")
					.setPermission("pjw.cmd.world.rename")
					.setUsage(usageRename)
					.addExample("/world rename MyWorld NewWorldName");
			
			Usage usageSetSpawn = new Usage(Argument.of("[<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[x,y,z]]", "Specifies the coordinates to set spawn to. x and z must fall within the range -30,000,000 to 30,000,000 (exclusive, without the commas), and y must be within the range -4096 to 4096 inclusive."));
			
			Help worldSetSpawn = new Help("world setspawn", "setspawn", "Sets the spawn point of specified world. If no arguments present sets spawn of current world to player location")
					.setPermission("pjw.cmd.world.setspawn")
					.setUsage(usageSetSpawn)
					.addExample("/world setspawn MyWorld -153,75,300")
					.addExample("/world setspawn");
			
			Usage usageTeleport = new Usage(Argument.of("<world>", "Specifies the targetted world"))
					.addArgument(Argument.of("[-c <x,y,z>]", "Specifies the coordinates to teleport to. x and z must fall within the range -30,000,000 to 30,000,000 (exclusive, without the commas), and y must be within the range -4096 to 4096 inclusive."))
					.addArgument(Argument.of("[-d <direction>]", "Specifies the direction player will face upon teleporting. The following can be used: NORTH, NORTH_WEST, WEST, SOUTH_WEST, SOUTH, SOUTH_EAST, EAST, NORTH_EAST"));
			
			Help worldTeleport = new Help("world teleport", "teleport", "Teleport to specified world and location")
					.setPermission("pjw.cmd.world.teleport")
					.setUsage(usageTeleport)
					.addExample("/world tp MyWorld -c -153,75,300 -d WEST")
					.addExample("/world teleport MyWorld -c -153,75,300")
					.addExample("/world tp MyWorld");
			
			Help worldUnload = new Help("world unload", "unload", "Unloads specified world. If players are in world, they will be teleported to default spawn")
					.setPermission("pjw.cmd.world.unload")
					.setUsage(usageLoad)
					.addExample("/world unload MyWorld");
			
			Help world = new Help("world", "world", "Base Project Worlds command")
					.setPermission("pjw.cmd.world")
					.addChild(worldUnload)
					.addChild(worldTeleport)
					.addChild(worldSetSpawn)
					.addChild(worldRename)
					.addChild(worldRegen)
					.addChild(worldPvp)
					.addChild(worldProperties)
					.addChild(worldLoad)
					.addChild(worldList)
					.addChild(worldKeepSpawnLoaded)
					.addChild(worldImport)
					.addChild(worldHardcore)
					.addChild(worldGamerule)
					.addChild(worldGamemode)
					.addChild(worldFill)
					.addChild(worldEnable)
					.addChild(worldDifficulty)
					.addChild(worldRemove)
					.addChild(worldCreate)
					.addChild(worldCopy)
					.addChild(worldTime)
					.addChild(worldWeather)
					.addChild(worldLoadOnStartup);

			Help.register(world);
		}
	}
}