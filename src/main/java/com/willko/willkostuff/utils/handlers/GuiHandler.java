package com.willko.willkostuff.utils.handlers;

import com.willko.containers.ContainerRedstoneGenerator;
import com.willko.guis.GuiRedstoneGenerator;
import com.willko.tileentity.TileEntityRedstoneGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==0) return new ContainerRedstoneGenerator(player.inventory, (TileEntityRedstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==0) return new GuiRedstoneGenerator(player.inventory, (TileEntityRedstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
