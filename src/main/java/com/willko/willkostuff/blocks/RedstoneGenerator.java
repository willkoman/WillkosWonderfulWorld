package com.willko.willkostuff.blocks;

import com.willko.Main;
import com.willko.tileentity.TileEntityRedstoneGenerator;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RedstoneGenerator extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public RedstoneGenerator(String name, Material material) {
		super(name, material);
		 setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		
	}
	 @SideOnly(Side.CLIENT)
	    public void initModel() {
	        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	    }

	    @Override
	    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
	    }

	    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
	        return EnumFacing.getFacingFromVector(
	             (float) (entity.posX - clickedBlock.getX()),
	             (float) (entity.posY - clickedBlock.getY()),
	             (float) (entity.posZ - clickedBlock.getZ()));
	    }

	    @Override
	    public IBlockState getStateFromMeta(int meta) {
	        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	    }

	    @Override
	    public int getMetaFromState(IBlockState state) {
	        return state.getValue(FACING).getIndex();
	    }

	    @Override
	    protected BlockStateContainer createBlockState() {
	        return new BlockStateContainer(this, FACING);
	    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(Main.instance, 0//this is changed  per block
					, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return new TileEntityRedstoneGenerator();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityRedstoneGenerator tileEntity = (TileEntityRedstoneGenerator)worldIn.getTileEntity(pos);
		worldIn.spawnEntity(new EntityItem(worldIn,pos.getX(), pos.getY(), pos.getZ(),tileEntity.handler.getStackInSlot(0)));
		super.breakBlock(worldIn, pos, state);
	}
	
}
