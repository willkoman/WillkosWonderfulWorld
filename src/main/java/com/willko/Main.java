package com.willko;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.willko.willkostuff.blocks.CrackedStone;
import com.willko.willkostuff.init.ModRecipes;
import com.willko.willkostuff.main.CommonProxy;
import com.willko.willkostuff.world.ModWorldGen;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class Main {

	public static final String MODID = "willkostuff";
	public static final String MODNAME = "Willko's Pile o Stuff";
	public static final String MODVERSION = "0.0.2";

	@SidedProxy(clientSide = "com.willko.willkostuff.main.ClientProxy", serverSide = "com.willko.willkostuff.main.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static Main instance;

	public static Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		logger.log(Level.ERROR, "V THIS IS JUST A CONFIG TEST V "+CrackedStone.SIZE);
		logger.log(Level.ERROR, "THE CURRENT SIZE OF CRACKLED VEINS IS "+CrackedStone.SIZE);
	}
	

}