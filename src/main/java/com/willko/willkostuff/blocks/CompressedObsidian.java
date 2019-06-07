package com.willko.willkostuff.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CompressedObsidian extends BlockBase {

	public CompressedObsidian(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(50.0f);
		setResistance(6000f);
		setHarvestLevel("pickaxe",3);
		
	}

}
