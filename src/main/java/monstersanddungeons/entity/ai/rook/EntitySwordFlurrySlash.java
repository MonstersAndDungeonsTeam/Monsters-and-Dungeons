package monstersanddungeons.entity.ai.rook;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;

public class EntitySwordFlurrySlash extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	//left4,1 X,Y,Z, wrist X, Speed, left sword
	float Leftarm[][] = 
		{
			{-30F,  60F,   70F, -45F,  1.5f,   135f},
			{  0F, -60F,  -10F, -45F,    4f,   135f},
			{  0F,  60F,    0F, -45F,    4f,   135f},
			{  0F,  60F,    0F, -45F,    4f,   135f},
			{  0F,  60F,    0F, -45F,    4f,   135f},
		}; 
	
	//right4,1 X,Y,Z, Speed, right sword
	float Rightarm[][] = 
		{
			{  0F,   0F,  0F,   0F,   1.5f, 45f},
			{-30F, -60F,-70F, -45F,   4f, 135f},
			{  0F,  60F, 10F, -45F,     4f, 135f},
			{  0F,  -60F, 10F,-45F,     4f, 135f},
			{  0F,  -60F, 10F,-45F,     4f, 135f},
		}; 
	
	//MiddleY, Right/left leg, X
	
	float middle[] = {0f,0f,0f,0f, 360f};
		
	boolean canIncrement[] = new boolean[9];
	
	public EntitySwordFlurrySlash(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) {
	
		
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, Rightarm[getAnimationNumber()][4] * speed,  Rightarm[getAnimationNumber()][0], Rightarm[getAnimationNumber()][1], Rightarm[getAnimationNumber()][2]);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, Leftarm[getAnimationNumber()][4]* speed, Leftarm[getAnimationNumber()][0], Leftarm[getAnimationNumber()][1], Leftarm[getAnimationNumber()][2]);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, Rightarm[getAnimationNumber()][4]* speed, Rightarm[getAnimationNumber()][3], 0f, 0f);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, Leftarm[getAnimationNumber()][4]* speed, Leftarm[getAnimationNumber()][3], 0, 0);
		
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, 10f* speed, 0f, middle[getAnimationNumber()], 0);
		canIncrement[5] = bossModel.movePiece(bossModel.rightMiddlePiece, 10f* speed, 0f, middle[getAnimationNumber()], -7);
		canIncrement[6] = bossModel.movePiece(bossModel.LeftBelt, 10f* speed, 0f, middle[getAnimationNumber()], 7);
			
		canIncrement[7] = bossModel.movePiece(bossModel.left_BigSword.getHandle(), Leftarm[getAnimationNumber()][4]* speed, Leftarm[getAnimationNumber()][5], 0f, 0);
		canIncrement[8] = bossModel.movePiece(bossModel.right_BigSword.getHandle(), Rightarm[getAnimationNumber()][4], Rightarm[getAnimationNumber()][5], 0f, 0);
		
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
	public boolean shouldActivate(World world, EntityAutomatonsRookBoss bossEntity) {
		
		if(super.shouldActivate(world, bossEntity))
		{
			EntityPlayer attack_player = world.getClosestPlayerToEntity(bossEntity, 50);
			if(attack_player != null)
			{
				if(attack_player.getDistanceSqToEntity(bossEntity) < 50)
				{
					if(bossEntity.getNavigator().tryMoveToEntityLiving(attack_player, 0.7f))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void completeAnimation(int animationNumber,
			ModelAutomatonsRookBoss bossModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) {
		
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
		
		if(animationNumber == 30)
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
		
		if(animationNumber == 5)
		{
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 5, bossEntity.getPosition().getY() - 5, bossEntity.getPosition().getZ() - 5, bossEntity.getPosition().getX() + 5, bossEntity.getPosition().getY() + 5, bossEntity.getPosition().getZ() + 5));
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(70.0D);

			for(Entity entity : entities)
			{
				if(entity instanceof EntityLivingBase)
				{
					bossEntity.knockBack(entity, 3, 1, 1);
					bossEntity.attackEntityAsMob(entity);
				}
			}
		}

	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) {
		
		
	}

}
