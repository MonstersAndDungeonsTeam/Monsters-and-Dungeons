package monstersanddungeons.entity.automatons;

import java.util.List;

import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.pawn.EntityAIAttackGroupTarget;
import monstersanddungeons.entity.ai.pawn.EntityAIAttackTarget;
import monstersanddungeons.entity.ai.pawn.EntityAIFindLeader;
import monstersanddungeons.entity.ai.pawn.EntityAIFollowLeader;
import monstersanddungeons.entity.ai.pawn.EntityAILeaderStackMembers;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.packet.UpdateClientEntityAnimation;
import monstersanddungeons.sound.MaDSoundsHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityWhitePawns extends MaDEntityMonsterBase 
{
	/*
	 * the entity will try to find a leader, only if it has found a leader will it be able to attack its attack target
	 */
	int pawn_check_cd, hammer_attack_cd, collisonCD = 0;
	public boolean removeAvoidEntity = false, hammer_attack = false;

	public EntityPawnCommander group_leader = null;
	public EntityWhitePawns hasTop, hasBottom;

	private EntityAIAvoidEntity<EntityPlayer> avoidEntity=  new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D);
	private EntityAIWander wander = new EntityAIWander(this, 0.8D);

	public EntityWhitePawns(World worldIn) 
	{
		super(worldIn);
		this.setHealth(15);

		this.tasks.addTask(4, new EntityAIAttackTarget(this));
		this.tasks.addTask(0, new EntityAIFollowLeader(this, 300f));
		this.tasks.addTask(1, new EntityAIFindLeader(this));
		this.tasks.addTask(1, new EntityAIAttackGroupTarget(this));	
		this.tasks.addTask(3, avoidEntity);
		this.tasks.addTask(4, wander);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) 
	{
		if(!this.world.isRemote)
		{
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D + 2 * EntityAILeaderStackMembers.getChainSize(this) + (this.group_leader != null ? 4D : 0D));
			return super.attackEntityAsMob(entityIn);
		}
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) 
	{
		if(this.collisonCD == 0)
		{
			this.collisonCD = 20;
			this.attackEntityAsMob(entityIn);
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSrc) {
		return MaDSoundsHandler.automatonHurtStone;
	}
	public int getHammer_attack_cd() {
		return hammer_attack_cd;
	}

	public boolean getHammer_attack()
	{
		return this.hammer_attack;
	}

	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 1;
	}

	public void updateClient(int animationNumber)
	{
		MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, animationNumber, 0));
	}

	@Override
	public void acivateAnimationby(int animation, int phase) 
	{
		switch(animation)
		{
		case 0:
			this.hammer_attack = true;
			this.hammer_attack_cd = 0;
			break;
		default:
			break;
		};

	}

	@Override
	public void resetAnimation() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if(pawn_check_cd == 0)
		{
			List<EntityWhitePawns> nearby_pawn = world.getEntitiesWithinAABB(EntityWhitePawns.class, new AxisAlignedBB(getPosition().getX() - 15, getPosition().getY() - 15, getPosition().getZ() - 15, getPosition().getX() + 15, getPosition().getY() + 15, getPosition().getZ() + 15));

			if(nearby_pawn.size() > 1 || this.group_leader != null)
			{
				if(!removeAvoidEntity)
				{
					this.tasks.removeTask(avoidEntity);
					this.tasks.removeTask(wander);
					this.removeAvoidEntity = true;
				}
			}else
			{
				if(this.removeAvoidEntity)
				{
					this.tasks.addTask(3, avoidEntity);
					this.tasks.addTask(4, wander);
					this.removeAvoidEntity = false;
				}
			}
			this.pawn_check_cd = 10;
		}else
			this.pawn_check_cd--;

		if(this.group_leader != null)
		{
			if(this.group_leader.isDead)
			{
				this.removeAvoidEntity = true;
				this.group_leader = null;
			}
		}

		if(this.hasBottom != null)
		{
			this.posX = this.hasBottom.posX;
			this.posY = this.hasBottom.posY + 1.3;
			this.posZ = this.hasBottom.posZ;
			this.rotationYaw = this.hasBottom.rotationYaw;
			this.rotationPitch = this.hasBottom.rotationPitch;

			if(this.hasBottom.isDead || this.group_leader == null)
			{
				this.hasBottom = null;
			}
		}

		if(this.hasTop != null)
		{
			if(this.hasTop.isDead)
			{
				this.hasTop = null;
			}
		}

		if(this.hammer_attack)
		{
			if(this.hammer_attack_cd < 20)
			{
				this.hammer_attack_cd ++;
			}else
			{
				List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(getPosition().getX() - 5, getPosition().getY() - 5, getPosition().getZ() - 5, getPosition().getX() + 5, getPosition().getY() + 5, getPosition().getZ() + 5));

				for(Entity entity : entities)
				{
					if(entity instanceof EntityPlayer)
					{
						this.attackEntityAsMob(entity);
					}
				}
				this.hammer_attack = false;
				this.hammer_attack_cd = 0;
			}
		}
		if(this.collisonCD > 0)
		{
			this.collisonCD--;
		}
	}
}
