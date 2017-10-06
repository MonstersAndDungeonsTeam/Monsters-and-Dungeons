package monstersanddungeons.entity.automatons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.pawn.EntityAILeaderFindAttackTarget;
import monstersanddungeons.entity.ai.pawn.EntityAILeaderStackMembers;

public class EntityPawnCommander extends MaDEntityMonsterBase {

	public boolean isPartyLeader = true;
	public Entity party_attack_target = null; //leader is the only one to change this
	
	public EntityPawnCommander(World worldIn) 
	{
		super(worldIn);

		this.tasks.addTask(2, new EntityAILeaderFindAttackTarget(this, 50));
		this.tasks.addTask(2, new EntityAILeaderStackMembers(this));
		this.tasks.addTask(1,  new EntityAIAvoidEntity(this, EntityPlayer.class, 5.0F, 0.8D, 0.5D));
		
	}
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	public int getMonsterID() 
	{
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public void acivateAnimationby(int animation, int phase) 
	{
		// TODO Auto-generated method stub

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
		if(this.party_attack_target != null)
		{
			if(this.getDistanceSqToEntity(party_attack_target) > 360)
			{
				this.faceEntity(party_attack_target, 1f, 1f);
				this.getNavigator().tryMoveToEntityLiving(party_attack_target, 0.6f);
			}
		}
	}
}
