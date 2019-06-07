package com.willko.willkostuff.init;

import java.util.ArrayList;
import java.util.List;

import com.willko.willkostuff.items.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {
	public static final CreativeTabs tabWillkosStuff = (new CreativeTabs("tabWillkosStuff") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(STONE_CHUNK);
		}
	});
	
	
	
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item STONE_CHUNK = new ItemBase("stonechunk");

	
	
}
