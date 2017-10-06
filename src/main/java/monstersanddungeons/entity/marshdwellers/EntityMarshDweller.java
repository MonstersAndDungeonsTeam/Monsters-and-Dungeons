package monstersanddungeons.entity.marshdwellers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.marshDweller.EntityAISitDown;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.tileentity.MaDTileEntityHandler;
import monstersanddungeons.util.entity.IMaDShopKeeper;
import monstersanddungeons.util.entity.ShopItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public class EntityMarshDweller extends MaDEntityMonsterBase implements IMaDShopKeeper
{

	private static final DataParameter<Boolean> playerNearby = EntityDataManager.<Boolean>createKey(EntityMarshDweller.class, DataSerializers.BOOLEAN);
	EntityAIWander wander = new EntityAIWander(this, 0.5D);

	int sitting_cd, currentShopTime;
	boolean isSittingDown;
	List<ShopItem> shop_items = new ArrayList<ShopItem>();

	public EntityMarshDweller(World worldIn) 
	{
		super(worldIn);

		this.setCustomNameTag("Villager");
		this.setAlwaysRenderNameTag(true);
		this.setSize(1.0F, 2.4F);

		this.tasks.addTask(0, wander);
		this.tasks.addTask(1, new EntityAISitDown(this));

		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.getDataManager().register(playerNearby, Boolean.valueOf(isSittingDown));
		
		if(!worldObj.isRemote)
			this.fillShopItems();	
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) 
	{	
		if(hand.equals(EnumHand.MAIN_HAND))
		{
			player.openGui(MonstersAndDungeons.instance, 0, worldObj, this.getEntityId(), (int)posY, (int)posZ);
		}
		return super.processInteract(player, hand, stack);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSittingDown()
	{
		return this.getDataManager().get(this.playerNearby).booleanValue();
	}
	public int getSitting_cd() {
		return sitting_cd;
	}

	public void setSitting_cd(int sitting_cd) {
		this.sitting_cd = sitting_cd;
	}

	public void setSittingDown(boolean value)
	{
		this.getDataManager().set(this.playerNearby, Boolean.valueOf(value));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public EnumHandSide getPrimaryHand() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 5;
	}


	@Override
	public void acivateAnimationby(int animation, int phase) 
	{
		if(animation == 0)
		{
			this.isSittingDown = true;
		}
	}


	@Override
	public void resetAnimation() 
	{
		this.isSittingDown = false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(this.sitting_cd > 0)
		{
			this.setSittingDown(false);
			this.sitting_cd --;
		}
		
		if(this.getCurrentRestockTime() >= this.getMaxRestockTime())
		{
			this.shop_items.clear();
			this.fillShopItems();
			this.currentShopTime = 0;
		}
	}

	@Override
	public List<ShopItem> getSellList() {
		// TODO Auto-generated method stub
		return this.shop_items;
	}


	@Override
	public int getMaxRestockTime() {
		// TODO Auto-generated method stub
		return 12000;
	}


	@Override
	public int getCurrentRestockTime() {
		// TODO Auto-generated method stub
		return currentShopTime;
	}

	public void fillShopItems()
	{
		for(int i = 0; i < 6; i ++)
		{
			Random rand = new Random();
			
			switch(rand.nextInt(9))
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				this.shop_items.add(this.getRandomCommon());
				break;
			case 5:
			case 6:
			case 7:
				this.shop_items.add(this.getRandomRare());
				break;
			case 8:
				this.shop_items.add(this.getRandomEpic());
				break;
			}	
		}
	}

	public ShopItem getRandomCommon()
	{
		Random rand = new Random();

		switch(rand.nextInt(20))
		{
		case 0:
			return new ShopItem(5, 0, new ItemStack(Items.COOKED_PORKCHOP, 1), new ItemStack(Items.STRING, 3));
		case 1:
			return new ShopItem(5, 0, new ItemStack(Items.BREAD, 1), new ItemStack(Items.WHEAT, 2));
		case 2:
			return new ShopItem(5, 0, new ItemStack(Items.CARROT, 1), new ItemStack(Items.WHEAT, 2));
		case 3:
			return new ShopItem(5, 0, new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Items.FISH, 3));
		case 4:
			return new ShopItem(5, 0, new ItemStack(Items.IRON_INGOT, 5), new ItemStack(Items.FISH, 3, 1));
		case 5:
			return new ShopItem(5, 0, new ItemStack(Items.IRON_INGOT, 5), new ItemStack(Items.FISH, 3, 2));
		case 6:
			return new ShopItem(5, 0, new ItemStack(Blocks.CLAY, 1), new ItemStack(Items.CLAY_BALL, 3));
		case 7:
			return new ShopItem(5, 0, new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Items.ROTTEN_FLESH, 10));
		case 8:
			return new ShopItem(5, 0, new ItemStack(Items.MUSHROOM_STEW, 1), new ItemStack(Items.CHICKEN, 2));
		case 9:
			return new ShopItem(5, 0, new ItemStack(Items.COAL, 3), new ItemStack(Blocks.LOG, 1, 2));
		case 10:
			return new ShopItem(5, 0, new ItemStack(Items.COAL, 3), new ItemStack(Blocks.LOG, 1, 3));
		case 11:
			return new ShopItem(5, 0, new ItemStack(Items.COAL, 3), new ItemStack(Blocks.LOG, 1, 1));
		case 12:
			return new ShopItem(5, 0, new ItemStack(Items.COAL, 3), new ItemStack(Blocks.LOG2, 1, 1));
		case 13:
			return new ShopItem(5, 0, new ItemStack(Items.COAL, 3), new ItemStack(Blocks.LOG, 1, 0));
		case 14:
			return new ShopItem(5, 0, new ItemStack(Blocks.BOOKSHELF, 2), new ItemStack(Items.LEATHER, 4));
		case 15:
			return new ShopItem(5, 0, new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Blocks.RAIL, 13));
		case 16:
			return new ShopItem(5, 0, new ItemStack(Items.QUARTZ, 20), new ItemStack(Items.GHAST_TEAR, 1));
		case 17:
			return new ShopItem(5, 0, new ItemStack(Items.FISH, 4), new ItemStack(Blocks.PUMPKIN, 8));
		case 18:
			return new ShopItem(5, 0, new ItemStack(Items.FISH, 3), new ItemStack(Items.APPLE, 2));
		case 19:
			return new ShopItem(5, 0, new ItemStack(Items.BAKED_POTATO, 3), new ItemStack(Items.COOKED_BEEF, 1));
		}

		return new ShopItem(5, 0, new ItemStack(Items.BAKED_POTATO, 3), new ItemStack(Items.COOKED_BEEF, 1));
	}
	public ShopItem getRandomRare()
	{
		Random rand = new Random();

		switch(rand.nextInt(10))
		{
		case 0:
			return new ShopItem(5, 0, new ItemStack(Items.GOLD_INGOT, 1), new ItemStack(Blocks.SANDSTONE, 10));
		case 1:
			return new ShopItem(5, 0, new ItemStack(Items.ENDER_PEARL, 2), new ItemStack(Items.FISH, 1, 3));
		case 2:
			return new ShopItem(5, 0, new ItemStack(Items.CAKE, 1), new ItemStack(Items.BONE, 5));
		case 3:
			return new ShopItem(5, 0, new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Items.SLIME_BALL, 1	));
		case 4:
			return new ShopItem(5, 0, new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.STONE_SWORD));
		case 5:
			return new ShopItem(5, 0, new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.BLAZE_ROD, 3));
		case 6:
			return new ShopItem(5, 0, new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GLOWSTONE_DUST, 20));
		case 7:
			return new ShopItem(5, 0, new ItemStack(Blocks.ENDER_CHEST, 1), new ItemStack(Blocks.MELON_BLOCK, 6));
		case 8:
			return new ShopItem(5, 0, new ItemStack(Blocks.ENCHANTING_TABLE, 1), new ItemStack(Blocks.PUMPKIN, 40));
		case 9:
			return new ShopItem(5, 0, new ItemStack(Blocks.QUARTZ_BLOCK, 3), new ItemStack(Blocks.LOG, 5, 2));
		}

		return new ShopItem(5, 0, new ItemStack(Items.BAKED_POTATO, 3), new ItemStack(Items.COOKED_BEEF, 1));
	}
	public ShopItem getRandomEpic()
	{
		switch(rand.nextInt(6))
		{
		case 0:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzGreatSword, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		case 1:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzWarhammer, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		case 2:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzChest, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		case 3:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzBoots, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		case 4:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzLegs, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		case 5:
			return new ShopItem(5, 0, new ItemStack(MaDItemsHandler.quartzShield, 1), new ItemStack(Blocks.QUARTZ_BLOCK, 64));
		}
		return null;
	}


	@Override
	public int getShopID() {
		// TODO Auto-generated method stub
		return this.getEntityId();
	}
	
	@Override
	public boolean turnsIntoBlock() {
		// TODO Auto-generated method stub
		return false;
	}
}
