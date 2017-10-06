package monstersanddungeons.entity.automatons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.ai.rook.EntityHammerFireSwing;
import monstersanddungeons.entity.ai.rook.EntityHammerHeavySwings;
import monstersanddungeons.entity.ai.rook.EntityHammerLeapSmash;
import monstersanddungeons.entity.ai.rook.EntityLegKick;
import monstersanddungeons.entity.ai.rook.EntityPhantomHammerSmash;
import monstersanddungeons.entity.ai.rook.EntityPunchCombo;
import monstersanddungeons.entity.ai.rook.EntityPunchFissure;
import monstersanddungeons.entity.ai.rook.EntitySevenSwordSlash;
import monstersanddungeons.entity.ai.rook.EntitySlam;
import monstersanddungeons.entity.ai.rook.EntitySwordCrossSlash;
import monstersanddungeons.entity.ai.rook.EntitySwordFall;
import monstersanddungeons.entity.ai.rook.EntitySwordFlurrySlash;
import monstersanddungeons.entity.miscellaneous.EntityFlyingSword;
import monstersanddungeons.entity.miscellaneous.EntitySafeZone;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.packet.UpdateClientEntityAnimation;
import monstersanddungeons.sound.MaDSoundsHandler;
import monstersanddungeons.util.Reference;
import monstersanddungeons.util.entity.IMaDBoss;

public class EntityAutomatonsRookBoss extends MaDEntityMonsterBase implements IMaDBoss{

	//note this is a server copy of the animation number belonging to the client
	int animationNumber, castingInt, attackCD, weakenedCD, phase;
	boolean isCasting, isInvulnerable = false;
	EntitySpecialAttackBase allAttacks[] = {new EntityLegKick(2, 300, 2, 40), new EntitySlam(1, 100, 2, 20), new EntityPunchFissure(1, 300, 2, 20), new EntityPunchCombo(4, 200, 2, 60), new EntitySwordFlurrySlash(4, 200, 1, 60), 
			new EntitySwordCrossSlash(2, 200, 1, 45), new EntityPhantomHammerSmash(9, 2000, -1, 570), new EntityHammerLeapSmash(2, 300, -1, 60), 
			new EntityHammerHeavySwings(5, 200, 0, 50), new EntityHammerFireSwing(2, 700, 0, 30), new EntitySevenSwordSlash(2, 1000, 1, 300)};

	EntitySpecialAttackBase currentAttack;
	List<EntitySafeZone> zones = new ArrayList<EntitySafeZone>();
	List<EntityFlyingSword> flying_sword = new ArrayList<EntityFlyingSword>();
	
	ResourceLocation location = new ResourceLocation(Reference.MOD_ID_GenBlocks +":textures/entities/health_bar.png");
	ResourceLocation name_location = new ResourceLocation(Reference.MOD_ID_GenBlocks +":textures/entities/The Rook.png");
	

	public EntityAutomatonsRookBoss(World worldIn) {
		super(worldIn);
		this.setSize(1.4f, 3.4f);
		ignoreFrustumCheck = true;
		this.isImmuneToFire = true;
		this.attackCD = 50;
	}
	
	@Override
	protected SoundEvent getHurtSound() {
		return MaDSoundsHandler.automatonHurtStone;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(5D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	public ResourceLocation getIcon() {
		// TODO Auto-generated method stub
		return location;
	}

	public void setInvulnerable(boolean isInvulnerable) {
		this.isInvulnerable = isInvulnerable;
	}

	public boolean getInvulnerable()
	{
		return this.isInvulnerable;
	}

	@Override
	public boolean isCasting() {
		// TODO Auto-generated method stub
		return isCasting;
	}

	@Override
	public int getCastingInt() {
		// TODO Auto-generated method stub
		return castingInt;
	}

	@Override
	public void activatePhase(int phase) 
	{
		List<Entity> entities = worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(getPosition().getX() - 50, getPosition().getY() - 50, getPosition().getZ() - 50, getPosition().getX() + 50, getPosition().getY() + 50, getPosition().getZ() + 50));

		for(Entity entity : entities)
		{
			if(entity instanceof EntityPlayer)
			{
				if(!worldObj.isRemote)
				{
					EntityFlyingSword sword = new EntityFlyingSword(this.worldObj);
					EntityFlyingSword sword1 = new EntityFlyingSword(this.worldObj);
					sword1.setPosition(entity.posX, entity.posY, entity.posZ);
					sword.setPosition(entity.posX, entity.posY, entity.posZ);
					worldObj.spawnEntityInWorld(sword);
					worldObj.spawnEntityInWorld(sword1);
				}
			}
		}
	}

	@Override
	public int getAnimationNumber() {
		// TODO Auto-generated method stub
		return animationNumber;
	}

	@Override
	public boolean canBePushed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntitySpecialAttackBase getCurrentAttack() {
		// TODO Auto-generated method stub
		return currentAttack;
	}

	public int getWeakenedCD() {
		return weakenedCD;
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {

		if(this.weakenedCD > 0)
		{
			if(!damageSrc.isExplosion() && !damageSrc.isFireDamage() && !damageSrc.isProjectile() && !damageSrc.isMagicDamage())
			{
				if(!damageSrc.getDamageType().contains(damageSrc.fall.getDamageType()))
				{
					if(damageSrc.getEntity() != null)
					{
						if(damageSrc.getEntity() instanceof EntityPlayer)
						{
							super.damageEntity(damageSrc, damageAmount);
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		switch(rand.nextInt(15))
		{
		case 0:
			this.dropItem(MaDItemsHandler.quartzBoots, 1);
			break;
		case 1:
			this.dropItem(MaDItemsHandler.quartzLegs, 1);
			break;
		case 2:
			this.dropItem(MaDItemsHandler.quartzChest, 1);
			break;
		case 3:
			this.dropItem(MaDItemsHandler.quartzHelmet, 1);
			break;
		case 4:
			this.dropItem(MaDItemsHandler.quartzShield, 1);
			break;
		case 5:
			this.dropItem(MaDItemsHandler.quartzGreatSword, 1);
			break;
		case 6:
			this.dropItem(MaDItemsHandler.quartzWarhammer, 1);
			break;
		default:
		}
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		
		List<MaDEntityMonsterBase> all = worldObj.getEntitiesWithinAABB(MaDEntityMonsterBase.class,  new AxisAlignedBB(getPosition().getX() - 50, getPosition().getY() - 50, getPosition().getZ() - 50, getPosition().getX() + 50, getPosition().getY() + 50, getPosition().getZ() + 50));
		
		for(MaDEntityMonsterBase entity : all)
		{
			if(!(entity instanceof EntityAutomatonsRookBoss))
			{
				entity.setDead();
			}
		}
					
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {

		if(this.currentAttack != null)
		{
			this.attackEntityAsMob(entityIn);
		}
	}

	public void despawnSafeZones()
	{
		for(EntitySafeZone zone : zones)
		{
			zone.setDead();
		}
		zones.clear();
	}

	public EntityFlyingSword getRandomSword()
	{
		Random rand = new Random();
		return this.flying_sword.get(rand.nextInt(this.flying_sword.size()));
	}

	public void despawnFlyingSwords()
	{
		for(EntityFlyingSword zone : flying_sword)
		{
			zone.setDead();
		}
		zones.clear();
	}

	public void spawnFlyingSwords(BlockPos basePosition)
	{
		this.despawnFlyingSwords();
		EntityFlyingSword sword_1 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_2 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_3 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_4 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_5 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_6 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_7 = new EntityFlyingSword(this.worldObj, this);
		EntityFlyingSword sword_8 = new EntityFlyingSword(this.worldObj, this);

		sword_2.setPosition(basePosition.getX() + 10, basePosition.getY(), basePosition.getZ() + 10);
		sword_4.setPosition(basePosition.getX() - 10, basePosition.getY(), basePosition.getZ() - 10);
		sword_7.setPosition(basePosition.getX() + 10, basePosition.getY(), basePosition.getZ()-10);
		sword_8.setPosition(basePosition.getX() - 10, basePosition.getY(), basePosition.getZ()-10);

		sword_1.setPosition(basePosition.getX() - 10, basePosition.getY(), basePosition.getZ());
		sword_3.setPosition(basePosition.getX(), basePosition.getY(), basePosition.getZ()-10);
		sword_5.setPosition(basePosition.getX() + 10, basePosition.getY(), basePosition.getZ());
		sword_6.setPosition(basePosition.getX(), basePosition.getY(), basePosition.getZ()+10);

		this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, basePosition.getX(), basePosition.getY(), basePosition.getZ(), true));
		this.flying_sword.add(sword_1);
		this.flying_sword.add(sword_2);
		this.flying_sword.add(sword_3);
		this.flying_sword.add(sword_4);
		this.flying_sword.add(sword_5);
		this.flying_sword.add(sword_6);
		this.flying_sword.add(sword_7);
		this.flying_sword.add(sword_8);

		worldObj.spawnEntityInWorld(sword_1);
		worldObj.spawnEntityInWorld(sword_2);
		worldObj.spawnEntityInWorld(sword_3);
		worldObj.spawnEntityInWorld(sword_4);
		worldObj.spawnEntityInWorld(sword_5);
		worldObj.spawnEntityInWorld(sword_6);
		worldObj.spawnEntityInWorld(sword_7);
		worldObj.spawnEntityInWorld(sword_8);
	}

	public void spawnSafeZones()
	{
		List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPosition().getX() - 50, this.getPosition().getY() - 50, this.getPosition().getZ() - 50, this.getPosition().getX() + 50, this.getPosition().getY() + 50, this.getPosition().getZ() + 50));

		//	despawnSafeZones();

		for(int i = 0; i < entities.size(); i ++)
		{
			EntitySafeZone zone = new EntitySafeZone(this.worldObj, this);
			BlockPos pos = getRandomPosition();
			zone.setPosition(pos.getX(), pos.getY(), pos.getZ());
			this.zones.add(zone);
			worldObj.spawnEntityInWorld(zone);
		}
	}

	public BlockPos getRandomPosition()
	{

		boolean satisfied = false;
		int xCoord = 0, zCoord = 0;

		BlockPos pos = null;
		Random rand = new Random();

		while(!satisfied)
		{
			xCoord = rand.nextInt(15); 
			zCoord = rand.nextInt(15);

			boolean isXNegative = rand.nextBoolean(), isZNegative = rand.nextBoolean();

			if(isXNegative)
			{
				xCoord = -xCoord;
			}

			if(isZNegative)
			{
				zCoord = -zCoord;
			}

			pos = new BlockPos(this.posX + xCoord, this.posY, this.posZ + zCoord);

			if(this.worldObj.getBlockState(pos.up()).getBlock().equals(Blocks.AIR))
			{
				satisfied = !this.isCloseToSafeZone(pos);
			}
		}

		return pos;
	}

	public boolean isCloseToSafeZone(BlockPos pos)
	{
		for(EntitySafeZone zone : zones)
		{
			if(zone.getPosition().getDistance(pos.getX(), pos.getY(), pos.getZ()) < 5)
			{
				return true;
			}
		}
		return false;
	}

	public boolean isPlayerOnSafeZone(EntityPlayer player)
	{
		for(EntitySafeZone zone : zones)
		{
			if(zone.isPlayerInThisZone(player))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public void resetAnimation() 
	{
		this.attackCD = 240;
		this.animationNumber = 0;
		this.weakenedCD = 140;
		this.currentAttack.resetAnimation();
		this.currentAttack = null;	

		if(!this.worldObj.isRemote)
			MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, 0, this.getPhase(),true));
	}

	public void selectAnimation()
	{
		Random rand = new Random();

		int special = rand.nextInt(this.allAttacks.length);

		if(this.allAttacks[special].shouldActivate(worldObj, this))
		{
			this.currentAttack = this.allAttacks[special];
			this.attackCD = 240;
			this.animationNumber = this.currentAttack.getExpectedTicks();

			if(!this.worldObj.isRemote)
				MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, special, this.getPhase()));
		}	
	}


	@Override
	public void onUpdate() {
		super.onUpdate();

		if(!this.worldObj.isRemote)
		{
			if(this.currentAttack != null)
			{
				if(this.animationNumber == 0)
				{
					this.currentAttack.applyDamage(animationNumber,this.worldObj ,this);
					this.resetAnimation();
				}else
				{
					this.currentAttack.activateEffect(animationNumber, this);
					this.animationNumber--;
				}
			}else
			{
				if(this.attackCD == 0)
				{
					this.selectAnimation();
				}else
					this.attackCD--;
			}
		}
		for(EntitySpecialAttackBase attack : allAttacks)
		{
			attack.decrementCurrentCD();
		}

		if(this.weakenedCD > 0)
		{
			this.weakenedCD--;
		}

		int newPhase = 0;
		if(this.getHealth() >= 666)
		{
			newPhase = 0;
		}else if(this.getHealth() >= 333)
		{
			newPhase = 1;
		}else if(this.getHealth() > 0)
		{
			newPhase = 2;
		}

		if(this.phase != newPhase)
		{
			this.phase = newPhase;
			this.activatePhase(phase);
		}
	}

	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void acivateAnimationby(int animation, int phase) {
		// TODO Auto-generated method stub
		this.currentAttack = this.allAttacks[animation];

	}

	@Override
	public int getPhase() 
	{
		return phase;
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return this.weakenedCD > 0 ? false : true;
	}

	@Override
	public MaDEntityMonsterBase getBossInfo() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public int getStartingX(boolean isEmpty) {
		// TODO Auto-generated method stub
		return isEmpty ? 0 : 0;
	}

	@Override
	public int getStartingY(boolean isEmpty) {
		return isEmpty ? 0 : 20;
	}

	@Override
	public int getWidth(boolean isEmpty) {
		// TODO Auto-generated method stub
		return (int) (isEmpty ? 180 : 180 * (this.getHealth()/1000.0));
	}

	@Override
	public int getHeight(boolean isEmpty) {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public ResourceLocation getBossName() {
		// TODO Auto-generated method stub
		return name_location;
	}
}
