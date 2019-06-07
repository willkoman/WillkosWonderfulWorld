package com.willko.tileentity;

import com.willko.energy.WillkoEnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRedstoneGenerator extends TileEntity implements ITickable {
	public ItemStackHandler handler = new ItemStackHandler(1);
	private WillkoEnergyStorage storage = new WillkoEnergyStorage(100000, 0, 100);
	private String customName;
	public int cookTime, cookMax = 25;
	public int energy = storage.getEnergyStored();
	public int TRANSFER_AMOUNT = 60;
	TileEntity TE = this;

	@Override
	public void update() {

		if (!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0))) {
			cookTime++;
			cookMax = (getFuelValue(handler.getStackInSlot(0)) / 10);
			if (cookTime == cookMax) {
				energy += getFuelValue(handler.getStackInSlot(0));
				handler.getStackInSlot(0).shrink(1);
				cookTime = 0;
  //TODO FIX THE GODDAMN ENERGY TRANSFER
			}
		} else
			cookTime = 0;

		if (getWorld() != null && !getWorld().isRemote
				&& getWorld().getTileEntity(getPos().offset(EnumFacing.EAST)) != null) {

			IEnergyStorage storages = getWorld().getTileEntity(getPos().offset(EnumFacing.EAST))
					.getCapability(CapabilityEnergy.ENERGY, EnumFacing.EAST.getOpposite());

			if (storages != null && storages.canReceive() && energy > 0) {
				storage.extractEnergy(storages.receiveEnergy(TRANSFER_AMOUNT, false), false);
				
			}
		}

	}

	public int getFuelValue(ItemStack stackInSlot) {
		if (stackInSlot.getItem() == Items.REDSTONE)
			return 1000;
		else if (stackInSlot.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK))
			return 9000;
		else if (stackInSlot.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_ORE))
			return 4000;
		return 0;
	}

	private boolean isItemFuel(ItemStack stackInSlot) {

		return getFuelValue(stackInSlot) > 0;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY)
			return (T) this.storage;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("CookTime", this.cookTime);
		compound.setInteger("GuiEnergy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		this.storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
		this.energy = compound.getInteger("GuiEnergy");
		this.customName = compound.getString("Name");
		this.storage.readFromNBT(compound);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.redstone_generator");
	}

	public int getEnergyStored() {
		return this.energy;
	}

	public int getMaxEnergyStored() {
		return this.storage.getMaxEnergyStored();
	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.energy;
		case 1:
			return this.cookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.energy = value;
		case 1:
			this.cookTime = value;
		}
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

}