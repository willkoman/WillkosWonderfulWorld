package com.willko.willkostuff.init;

import java.util.List;

import com.willko.willkostuff.blocks.BlockBase;
import com.willko.willkostuff.blocks.CompressedObsidian;
import com.willko.willkostuff.blocks.CrackedStone;
import com.willko.willkostuff.blocks.RedstoneGenerator;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block COMPRESSED_OBSIDIAN = new CompressedObsidian("compressed_obsidian", Material.ROCK);
	public static final Block CRACKED_STONE = new CrackedStone("cracked_stone",Material.ROCK);
	public static final Block REDSTONE_GENERATOR = new RedstoneGenerator("redstone_generator",Material.IRON);
}
