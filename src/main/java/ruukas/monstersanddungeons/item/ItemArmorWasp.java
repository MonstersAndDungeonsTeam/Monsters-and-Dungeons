package ruukas.monstersanddungeons.item;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.mojang.authlib.GameProfile;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ruukas.monstersanddungeons.MonstersAndDungeons;
import ruukas.monstersanddungeons.client.model.ModelWaspArmor;

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

            if (player.canPlayerEdit(pos, facing, itemstack) && Blocks.SKULL.canPlaceBlockAt(worldIn, pos))
            {
                if (worldIn.isRemote)
                {
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    worldIn.setBlockState(pos, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, facing), 11);
                    int i = 0;

                    if (facing == EnumFacing.UP)
                    {
                        i = MathHelper.floor((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
                    }

                    TileEntity tileentity = worldIn.getTileEntity(pos);

                    if (tileentity instanceof TileEntitySkull)
                    {
                        TileEntitySkull tileentityskull = (TileEntitySkull)tileentity;

                        if (itemstack.getMetadata() == 3)
                        {
                            GameProfile gameprofile = null;

                            if (itemstack.hasTagCompound())
                            {
                                NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                                if (nbttagcompound.hasKey("SkullOwner", 10))
                                {
                                    gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                                }
                                else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner")))
                                {
                                    gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                                }
                            }

                            tileentityskull.setPlayerProfile(gameprofile);
                        }
                        else
                        {
                            tileentityskull.setType(itemstack.getMetadata());
                        }

                        tileentityskull.setSkullRotation(i);
                        Blocks.SKULL.checkWitherSpawn(worldIn, pos, tileentityskull);
                    }

                    if (player instanceof EntityPlayerMP)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
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
