package monstersanddungeons.entity.ai.ent;

import java.util.List;

import monstersanddungeons.client.models.ModelEnt;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.world.EntityEnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLogSmash extends EntitySpecialAttackBase<ModelEnt, EntityEnt>
{
	EntityPlayer AttackPlayer = null;

	boolean canIncrement[] = new boolean[1];

	//upperleft X, Y speed, lower chest Y
	float arms[][] = 
		{
			{-70,  70, 1.5f, 30f},
			{-40, -40, 5f, -30f},
			{  0,   0, 0f, 0f},
			
		}; 

	public EntityLogSmash(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldActivate(World world, EntityEnt bossEntity) 
	{
		if(super.shouldActivate(world, bossEntity))
		{
			AttackPlayer = world.getClosestPlayerToEntity(bossEntity, 50);
			if(AttackPlayer != null)
			{
				if(bossEntity.getNavigator().tryMoveToEntityLiving(AttackPlayer, 0.5f))
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void tickAnimation(ModelEnt bossModel, float scale, float speed) 
	{
		canIncrement[0] = bossModel.movePiece(bossModel.leftupperarm, arms[getAnimationNumber()][2], arms[getAnimationNumber()][0], arms[getAnimationNumber()][1], 0f);
		
		boolean flag = false;
		for(int i = 0; i < canIncrement.length; i ++)
		{
			if(!canIncrement[i])
			{
				flag = true;
			}	
		}
		if(!flag)
		{
			if(!getPaused())
			{
				if(!this.incrementAnimationNumber())
				{
					this.completeAnimation(0, bossModel);
				}
			}
		}
	}

	@Override
	public void completeAnimation(int animationNumber, ModelEnt bossModel) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void activateEffect(int animationNumber, EntityEnt bossEntity) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityEnt bossEntity) 
	{
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 3, bossEntity.getPosition().getY() - 3, bossEntity.getPosition().getZ() - 3, bossEntity.getPosition().getX() + 3, bossEntity.getPosition().getY() + 3, bossEntity.getPosition().getZ() + 3));
		bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);

		for(Entity entity : entities)
		{
			if(entity instanceof EntityLivingBase)
			{
				bossEntity.attackEntityAsMob(entity);
			}
		}
	}
}
