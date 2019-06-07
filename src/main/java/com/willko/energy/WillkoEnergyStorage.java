package com.willko.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class WillkoEnergyStorage extends EnergyStorage {
	public WillkoEnergyStorage(int capacity) {
		super(capacity, capacity, capacity, 0);
	}

	public WillkoEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer, maxTransfer, 0);
	}

	public WillkoEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract, 0);
	}

	public WillkoEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return super.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return super.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored() {
		// TODO Auto-generated method stub
		return super.getEnergyStored();
	}

	@Override
	public boolean canExtract() {
		// TODO Auto-generated method stub
		return super.canExtract();
	}

	@Override
	public boolean canReceive() {
		// TODO Auto-generated method stub
		return super.canReceive();
	}
	public int setEnergyStored(final int energy, final boolean simulate) {
		this.onEnergyChanged();
		final int toSet = Math.min(energy, this.getMaxReceive());
		if (!simulate) {
			this.energy = toSet;
		}
		return toSet;
	}
	public int getMaxReceive() {
		return this.maxReceive;
	}
	public void onEnergyChanged() {
	}
	public void readFromNBT(NBTTagCompound compound) {
		this.energy = compound.getInteger("Energy");
		this.capacity = compound.getInteger("Capacity");
		this.maxReceive = compound.getInteger("MaxRecieve");
		this.maxExtract = compound.getInteger("MaxExtract");
	}

	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("Energy", energy);
		compound.setInteger("Capacity", capacity);
		compound.setInteger("MaxRecieve", maxReceive);
		compound.setInteger("MaxExtract", maxExtract);
	}

	public void setMaxReceive(int max) {
		this.maxReceive=max;
		
	}
}
