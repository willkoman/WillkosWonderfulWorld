package com.willko.willkostuff.init;

import org.apache.logging.log4j.Level;

import com.willko.Main;
import com.willko.willkostuff.main.CommonProxy;

import net.minecraftforge.common.config.Configuration;

public class Config {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_OREGEN = "oregen";

	// This values below you can access elsewhere in your mod:
/*	public static boolean isThisAGoodTutorial = true;
	public static String yourRealName = "Steve";*/
	public static int crackedStoneMinY,crackedStoneMaxY,crackedStoneSize,crackedStoneChance;
	// Call this from CommonProxy.preInit(). It will create our config if it doesn't
	// exist yet and read the values if it does exist.
	public static void readConfig() {
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initDimensionConfig(cfg);
		} catch (Exception e1) {
			Main.logger.log(Level.FATAL, "Problem loading config file!", e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");

/*		isThisAGoodTutorial = cfg.getBoolean("goodTutorial", CATEGORY_GENERAL, isThisAGoodTutorial,
				"Set to false if you don't like this tutorial");
		yourRealName = cfg.getString("realName", CATEGORY_GENERAL, yourRealName, "Set your real name here");*/
		
	}

	private static void initDimensionConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_OREGEN, "Ore Generation");
		
		cfg.addCustomCategoryComment(CATEGORY_OREGEN, "Cracked Stone");
		crackedStoneMinY = cfg.getInt("Min Y", CATEGORY_OREGEN, 4, 1, 256, "Minimum Spawn Level");
		crackedStoneMaxY = cfg.getInt("Max Y", CATEGORY_OREGEN, 128, 1, 256, "Maximum Spawn Level");
		crackedStoneSize = cfg.getInt("Size", CATEGORY_OREGEN, 3, 1, 50, "Size Of Generated Vein");
		crackedStoneChance = cfg.getInt("Amount", CATEGORY_OREGEN, 18, 1, 50, "How Many Veins Per Chunk");
		
	}
}