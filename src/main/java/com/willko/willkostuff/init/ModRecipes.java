package com.willko.willkostuff.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		GameRegistry.addSmelting(ModItems.STONE_CHUNK, new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN,1), 1.5f);
	}
}
