package com.willko.willkostuff.utils.handlers;

import com.willko.Main;
import com.willko.tileentity.TileEntityRedstoneGenerator;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRedstoneGenerator.class, new ResourceLocation(Main.MODID+":redstone_generator"));
	}
}
