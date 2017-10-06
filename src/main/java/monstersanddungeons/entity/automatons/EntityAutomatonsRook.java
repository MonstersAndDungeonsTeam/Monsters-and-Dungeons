package monstersanddungeons.entity.automatons;

import java.util.List;
import java.util.Random;

import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.packet.UpdateClientEntityAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityAutomatonsRook extends MaDEntityMonsterBase {

	int animationCycle = 0, TickCount = 0;

	int kickCD, chargeCD;
	public boolean SlamAttack, PunchMode, KickMode, chargeMode;

	ItemStack weapon = new ItemStack(Items.GOLDEN_SWORD);

	public EntityAutomatonsRook(World worldIn) {
		super(worldIn);
		this.setSize(1.4f, 3.4f);

		this.setHealth(300);

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));

		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weapon);
	}


	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// TODO Auto-generated method stub
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(5D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
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

	public int getAnimationCycle() {
		return animationCycle;
	}

	public void setAnimationCycle(int number)
	{
		this.animationCycle = number;
	}
	public void setKickMode(boolean kickMode) {
		KickMode = kickMode;
	}
	public void setChargeMode(boolean chargeMode) {
		this.chargeMode = chargeMode;
	}

	public void setKickCD(int kickCD) {
		this.kickCD = kickCD;
	}

	public void setChargeCD(int chargeCD) {
		this.chargeCD = chargeCD;
	}

	/**
	 * @param animationID - 0 = slam, 1 = punch, 2 = leg kick, 3 = charge, 4 = death, 5 = half hp animation
	 */
	public void acivateAnimationby(int animationID)
	{
		switch(animationID)
		{
		case 0:
			this.setSlamAttack(true);
			break;
		case 1:
			this.setPunchMode(true);
			break;
		case 2:
			this.setKickMode(true);
			break;
		case 3:
			this.setChargeMode(true);
			break;
		}
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {

		if(!damageSrc.isExplosion() && !damageSrc.isFireDamage() && !damageSrc.isProjectile() && !damageSrc.isMagicDamage())
		{
			super.damageEntity(damageSrc, damageAmount);
		}
	}

	/** 0 = slam, 1 = punch
	 * @param attackNumber
	 */
	public void activateAttack(int attackNumber)
	{
		//slam
		if(attackNumber == 0)
		{
			List<Entity> entities = worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.getPosition().getX() - 5, this.getPosition().getY() - 5, this.getPosition().getZ() - 5, this.getPosition().getX() + 5, this.getPosition().getY() + 5, this.getPosition().getZ() + 5));

			for(Entity entity : entities)
			{
				if(entity != null)
				{
					this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
					this.attackEntityAsMob(entity);
				}
			}
		}else if (attackNumber == 1) // punch 
		{
			if(this.getAttackTarget() != null)
			{
				if(this.getAttackTarget().getDistanceToEntity(this) <= 3)
				{
					this.attackEntityAsMob(this.getAttackTarget());
				}
			}
		}else if (attackNumber == 2) // kick 
		{
			if(this.getAttackTarget() != null)
			{
				if(this.getAttackTarget().getDistanceToEntity(this) <= 3)
				{
					this.getAttackTarget().knockBack(this, (float)7, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
					this.attackEntityAsMob(this.getAttackTarget());
				}
			}
		}
	}

	public void setSlamAttack(boolean slamAttack) {
		SlamAttack = slamAttack;
	}

	public void setPunchMode(boolean punchMode) {
		PunchMode = punchMode;
	}
	/**
	 * @param animationID - 0 = slam, 1 = punch, 2 = leg kick, 3 = charge, 4 = death, 5 = half hp animation
	 */
	public void startAnimation(int animation)
	{
		if(!worldObj.isRemote)
		{	
			MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, animation, 0));
		}
	}

	public void inAnimation()
	{
		if(this.SlamAttack)
		{
			if(TickCount == 0)
			{
				TickCount = 1;
				if(animationCycle < 26)
				{
					animationCycle ++;

					if(animationCycle >= 14 && animationCycle <= 17)
					{
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3, false);
						this.activateAttack(0);
					}
				}
				else
				{
					animationCycle = 0;
					this.SlamAttack = false;
				}
			}else
			{
				TickCount --;
			}
		}else if(this.PunchMode)
		{

			if(TickCount == 0)
			{
				TickCount = 1;
				if(animationCycle < 24)
				{
					animationCycle ++;

					if((animationCycle >= 8 && animationCycle <= 10) || (animationCycle >= 20 && animationCycle <= 22))
					{
						this.activateAttack(1);
					}
				}
				else
				{
					animationCycle = 0;
					this.PunchMode = false;
				}
			}else
			{
				TickCount --;
			}
		}else if(this.KickMode)
		{
			if(TickCount == 0)
			{
				TickCount = 1;
				if(animationCycle < 16)
				{
					animationCycle ++;

					if((animationCycle == 13 || animationCycle == 14))
					{
						this.activateAttack(2);
					}
				}
				else
				{
					animationCycle = 0;
					this.KickMode = false;
				}
			}else
			{
				TickCount --;
			}
		}else if(this.chargeMode)
		{
			animationCycle ++;

			if(this.getAttackTarget() != null)
			{
				BlockPos pos = this.getAttackTarget().getPosition();

				if((animationCycle > 200) || this.getPosition().distanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 7)
				{
					this.activateAttack(1);
					this.SlamAttack = false;
					this.animationCycle = 0;
				}
			}
		}

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		
		if(this.getAttackTarget() != null)
		{
			BlockPos pos = this.getAttackTarget().getPosition();

			if(this.getPosition().distanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 7)
			{
				if(this.getAnimationCycle() == 0)
				{
					if(this.kickCD != 0)
					{
						this.kickCD--;
						if(this.getHealth() <= 150)
						{
							this.startAnimation(0);
							this.setSlamAttack(true);
						}else
						{
							this.startAnimation(1);
							this.setPunchMode(true);
						}
					}else
					{
						this.KickMode = true;
						this.kickCD = 7;
						this.startAnimation(2);
					}

					if(!this.PunchMode && !this.SlamAttack && !this.KickMode)
						this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
				}
			}else
			{
				/*
			}
				if((this.getPosition().distanceSq(pos.getX(), pos.getY(), pos.getZ()) > 150) && this.chargeCD <= 0)
				{
					this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(this.getAttackTarget()), 2);
					this.startAnimation(3);
					this.chargeCD = 300;
					this.setChargeMode(true);
				}else
				{
					this.chargeCD--;
				 */
				if(!this.PunchMode && !this.SlamAttack && !this.KickMode && !this.chargeMode)
					this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(this.getAttackTarget()), this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

			}
		}
		inAnimation();
	}
	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void resetAnimation() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void acivateAnimationby(int animation, int phase) {
		// TODO Auto-generated method stub
		
	}

}

