package monstersanddungeons.items.spawnEggs;

import java.util.Iterator;
import java.util.List;

import monstersanddungeons.entity.MaDEntityList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class ItemSpawnEgg extends Item 
{
	public ItemSpawnEgg(String name) {

		this.setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName(name);
		setRegistryName(name);

		this.setHasSubtypes(true);

		GameRegistry.register(this);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String s = "Spawn ";
		String entityName = MaDEntityList.getStringFromID(stack.getItemDamage());
		if (entityName != null) {
			s = s + entityName;
		}
		return s;
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote)
		{
			this.spawnCreature(worldIn, stack.getItemDamage(), playerIn.posX, playerIn.posY, playerIn.posZ);
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}


	public Entity spawnCreature(World world, int entityID, double x, double y, double z) {
		
		Entity entity = null;
		Class<? extends Entity> oclass = MaDEntityList.getClassFromID(entityID);
		
		if (MaDEntityList.entityEggs.containsKey(oclass)) {
			entity = MaDEntityList.createEntity(oclass, world);
			
			if (entity instanceof EntityLiving) {
				EntityLiving entityliving = (EntityLiving) entity;
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				entityliving.setPosition(x, y, z);
				world.spawnEntity(entity);
				entityliving.playLivingSound();
			}
		}
		
		return entity;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		Iterator<Class<? extends Entity>> iterator = MaDEntityList.entityEggs.keySet().iterator();
		
		while (iterator.hasNext()) {
			
			Class<? extends Entity> oclass = iterator.next();
			List<Integer> colors = MaDEntityList.entityEggs.get(oclass);
			
			if (colors != null && colors.size() == 2) 
			{	
				items.add(new ItemStack(this, 1, MaDEntityList.getEntityId(oclass)));
			}
		}
	}

}
