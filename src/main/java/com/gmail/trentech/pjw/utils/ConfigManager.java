package com.gmail.trentech.pjw.utils;

import java.io.File;
import java.io.IOException;

import com.gmail.trentech.pjw.Main;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class ConfigManager {

	private File file;
	private CommentedConfigurationNode config;
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	
	public ConfigManager(String configName) {
		String folder = "config" + File.separator + "projectworlds";
        if (!new File(folder).isDirectory()) {
        	new File(folder).mkdirs();
        }
		file = new File(folder, configName);
		
		create();
		load();
		init();
	}
	
	public ConfigManager() {
		String folder = "config" + File.separator + "projectworlds";
        if (!new File(folder).isDirectory()) {
        	new File(folder).mkdirs();
        }
		file = new File(folder, "config.conf");
		
		create();
		load();
		init();
	}
	
	public ConfigurationLoader<CommentedConfigurationNode> getLoader() {
		return loader;
	}

	public CommentedConfigurationNode getConfig() {
		return config;
	}

	public void save() {
		try {
			loader.save(config);
		} catch (IOException e) {
			Main.getLog().error("Failed to save config");
			e.printStackTrace();
		}
	}
	
	private void init() {
		if(file.getName().equalsIgnoreCase("config.conf")) {
			if(config.getNode("options", "first_join", "world").isVirtual()) {
				config.getNode("options", "first_join", "world").setValue("world").setComment("World player spawns to when joining for the first time");
			}
			if(config.getNode("options", "first_join", "title").isVirtual()) {
				config.getNode("options", "first_join", "title").setValue("&2Welcome to the server").setComment("First join title");
			}
			if(config.getNode("options", "first_join", "sub_title").isVirtual()) {
				config.getNode("options", "first_join", "sub_title").setValue("&eThe best minecraft server ever").setComment("First join subtitle");
			}
			//UPDATE CONFIG
			if(!config.getNode("settings", "commands").isVirtual()) {
				config.getNode("settings").removeChild("commands");
			}
		}
		save();
	}

	private void create() {
		if(!file.exists()) {
			try {
				Main.getLog().info("Creating new " + file.getName() + " file...");
				file.createNewFile();		
			} catch (IOException e) {				
				Main.getLog().error("Failed to create new config file");
				e.printStackTrace();
			}
		}
	}
	
	private void load() {
		loader = HoconConfigurationLoader.builder().setFile(file).build();
		try {
			config = loader.load();
		} catch (IOException e) {
			Main.getLog().error("Failed to load config");
			e.printStackTrace();
		}
	}
}
