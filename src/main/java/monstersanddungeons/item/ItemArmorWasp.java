package monstersanddungeons.item;

import java.util.List;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.block.AllBlocks;
import monstersanddungeons.client.model.ModelWaspArmor;
import monstersanddungeons.tileentity.TileHelmet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorWasp extends ItemArmor {
	public ItemArmorWasp(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		ModelWaspArmor wasp = new ModelWaspArmor(armorSlot);
        wasp.setModelAttributes(_default);
		return wasp;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return MonstersAndDungeons.MODID + ":textures/models/armor/wasp.png";
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		if(getEquipmentSlot() == EntityEquipmentSlot.HEAD){
			tooltip.add(TextFormatting.AQUA + "You can place this.");
		}
		
		if(flagIn == TooltipFlags.ADVANCED){
			tooltip.add("Modeled by KaijuRizard for his trial.");
		}
	}
	
	
	//Skull

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(getEquipmentSlot() != EntityEquipmentSlot.HEAD){
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		
		
        if (facing == EnumFacing.DOWN)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos))
            {
                facing = EnumFacing.UP;
                pos = pos.down();
            }
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

            if (!flag)
            {
                if (!worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.isSideSolid(pos, facing, true))
                {
                    return EnumActionResult.FAIL;
                }

                pos = pos.offset(facing);
            }

            ItemStack itemstack = player.getHeldItem(hand);

            if (player.canPlayerEdit(pos, facing, itemstack) && AllBlocks.HELMET.canPlaceBlockAt(worldIn, pos))
            {
                if (worldIn.isRemote)
                {
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    worldIn.setBlockState(pos, AllBlocks.HELMET.getDefaultState().withProperty(BlockSkull.FACING, facing), 11);
                    int i = 0;

                    if (facing == EnumFacing.UP)
                    {
                        i = MathHelper.floor((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
                    }

                    TileEntity tileentity = worldIn.getTileEntity(pos);

                    if (tileentity instanceof TileHelmet)
                    {
                    	TileHelmet tileHelmet = (TileHelmet)tileentity;
                    	
                    	tileHelmet.setItemStack(itemstack);

                        tileHelmet.setHelmetRotation(i);
                    }

                    itemstack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }
}
