package com.willko.willkostuff.blocks;

import java.util.Random;

import com.willko.willkostuff.init.Config;
import com.willko.willkostuff.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class CrackedStone extends BlockBase {

	public static int MIN_Y=0;
	public static  int MAX_Y=0;
	public static  int SIZE=0;
	public static  int CHANCE=0;

	public CrackedStone(String name, Material material) {
		
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(1.5f);
		setResistance(25f);
		setHarvestLevel("pickaxe",0);
		
		MIN_Y=Config.crackedStoneMinY;
		MAX_Y=Config.crackedStoneMaxY;
		SIZE=Config.crackedStoneSize;
		CHANCE=Config.crackedStoneChance;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rd, int fortune) {
		return ModItems.STONE_CHUNK;
	}
	@Override
	public int quantityDropped(Random rd) {
		int max = 4;
		int min = 1;
		return rd.nextInt(max)+min;
	}

}
