package monstersanddungeons.entity.ai.rook;

import java.util.List;

import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHammerHeavySwings extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	EntityPlayer AttackPlayer;
	boolean canIncrement[] = new boolean[7];

	//RS1 X, LS1 X, LS1 Y, LS1 Speed, RS4 X, LS3 X, LS3 Y, LS3 Z, Left Wrist Y
	float arms[][] = 
		{
			{-30, -70, 0f, 1.2f, -30, -50, 45,  0f, 0f},
			{-10, -70, -40f, 3f, -30,   0, 45,  0f, 0f},
			{-10, -80, -30f, 3f,   0,   0,  0, -50f, -60f},
			{-10, -65, 80f, 3f,   0,   0,  0,  -50f, -60f},
			{-40, -110, -40f, 3f,   0,   0,  0,  0f, 40f},
			{0, -30, -40f, 3f,   0,   0,  0,  0f, 40f},
		}; 

	//Middle piece X, Y
	float chest[][]= 
		{
			{-10F, 30f},
			{ 20F, -10f},
			{ 20F, -10f},
			{ 20F, -10f},
			{ -20F, 0f},
			{ 40F, 0f},
		}; 

	float weapon[] = {90f, 135f, 135f, 135f, 110f, 90f};

	public EntityHammerHeavySwings(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
	}

	@Override
	public boolean shouldActivate(World world, EntityAutomatonsRookBoss bossEntity) {

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
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) {

		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 3f * speed, arms[getAnimationNumber()][0], 0, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 4f* speed, arms[getAnimationNumber()][1], arms[getAnimationNumber()][2], 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 3f* speed, arms[getAnimationNumber()][4], 0, 0);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 3f* speed, arms[getAnimationNumber()][5], arms[getAnimationNumber()][6], arms[getAnimationNumber()][7]);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, 3f* speed, chest[getAnimationNumber()][0], chest[getAnimationNumber()][1], 0);
		canIncrement[5] = bossModel.movePiece(bossModel.LeftWrist, 3f*speed, 0f, arms[getAnimationNumber()][8], 0);
		canIncrement[6] = bossModel.movePiece(bossModel.hammer.Shaft, 3f* speed, weapon[getAnimationNumber()], 0, 0);

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
	public void completeAnimation(int animationNumber, ModelAutomatonsRookBoss bossModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) 
	{
		World world = bossEntity.worldObj;
		if(animationNumber == 40)
		{
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 5, bossEntity.getPosition().getY() - 5, bossEntity.getPosition().getZ() - 5, bossEntity.getPosition().getX() + 5, bossEntity.getPosition().getY() + 5, bossEntity.getPosition().getZ() + 5));
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);

			for(Entity entity : entities)
			{
				if(entity instanceof EntityLivingBase)
				{
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.getPotionById(2), 100, 2));
					bossEntity.attackEntityAsMob(entity);
				}
			}
		}
		else
			if(animationNumber == 30)
			{
				List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 5, bossEntity.getPosition().getY() - 5, bossEntity.getPosition().getZ() - 5, bossEntity.getPosition().getX() + 5, bossEntity.getPosition().getY() + 5, bossEntity.getPosition().getZ() + 5));
				bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);

				for(Entity entity : entities)
				{
					if(entity instanceof EntityLivingBase)
					{
						bossEntity.attackEntityAsMob(entity);
					}
				}
			}
			else
				if(animationNumber == 10)
				{
					List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 5, bossEntity.getPosition().getY() - 5, bossEntity.getPosition().getZ() - 5, bossEntity.getPosition().getX() + 5, bossEntity.getPosition().getY() + 5, bossEntity.getPosition().getZ() + 5));
					bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);

					for(Entity entity : entities)
					{
						if(entity instanceof EntityLivingBase)
						{
							bossEntity.knockBack(entity, 3f, 3f, 3f);
							bossEntity.attackEntityAsMob(entity);
						}
					}
				}
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) 
	{
		// TODO Auto-generated method stub

	}

}
