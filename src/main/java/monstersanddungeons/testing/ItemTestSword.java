package monstersanddungeons.testing;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTestSword extends ItemSword
{

	public ItemTestSword(ToolMaterial material, String name) 
	{
		super(material);

		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.COMBAT);
		setUnlocalizedName(name);
		setRegistryName(name);

		
		this.addPropertyOverride(new ResourceLocation("decay"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {


				if (entityIn == null)
				{
					return 0.0F;
				}
				else
				{
					ItemStack itemstack = entityIn.getActiveItemStack();
					System.out.println("Dylan" + itemstack.getTagCompound().getFloat("number"));
					return itemstack.getTagCompound().getFloat("number");
				}
			}
		});

		GameRegistry.register(this);
	}


	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) 
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("blade_value", EnumColours.swordColour.DIAMOND.getID());
		tag.setInteger("handle_value", EnumColours.handleColour.REDSTONE.getID());
		tag.setInteger("hilt_value", EnumColours.hiltColour.STEEL.getID());
		tag.setFloat("number", 0.0f);

		ItemStack example = new ItemStack(itemIn);
		example.setTagCompound(tag);

		NBTTagCompound tag1 = new NBTTagCompound();
		tag1.setInteger("blade_value", EnumColours.swordColour.WOOD.getID());
		tag1.setInteger("handle_value", EnumColours.handleColour.REDSTONE.getID());
		tag1.setInteger("hilt_value", EnumColours.hiltColour.CHEESE.getID());
		tag1.setFloat("number", 1.0f);

		ItemStack example1 = new ItemStack(itemIn);
		example1.setTagCompound(tag1);

		subItems.add(example);
		subItems.add(example1);


		super.getSubItems(itemIn, tab, subItems);
	}

}
