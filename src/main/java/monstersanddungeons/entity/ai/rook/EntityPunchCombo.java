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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityPunchCombo extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	boolean spawnFireSphere = false;
	float renderSphere = 0;
	boolean canIncrement[] = new boolean[5];

	//right 4 X, Y, left 4 X, Y, Hammer, middle Piece

	float left_arm[][] = 
		{
			{0F, 0F},
			{0F, 0F},
			{-15F, -90F},
			{-40F, -40F},
			{0F, -0F},
		};

	float right_arm[][] = 
		{
			{15F, -90F},
			{-40F,-40F},
			{0F, 0F},
			{0F, 0F},
			{0F, 0F},
		};
	float chest [] = {-35, 15, 35, -15, 0};

	public EntityPunchCombo(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldActivate(World world, EntityAutomatonsRookBoss bossEntity) {

		if(super.shouldActivate(world, bossEntity))
		{
			EntityPlayer attack_player = world.getClosestPlayerToEntity(bossEntity, 50);
			if(attack_player != null)
			{
				if(attack_player.getDistanceSq(bossEntity) < 50)
				{
					if(bossEntity.getNavigator().tryMoveToEntityLiving(attack_player, 1f))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) {

		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 2.2f*speed, right_arm[getAnimationNumber()][0], 0, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 2.2f*speed,  left_arm[getAnimationNumber()][0], 0, 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 2.2f*speed, right_arm[getAnimationNumber()][1], 0, 0);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 2.2f*speed, left_arm[getAnimationNumber()][1], 0, 0);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, 2.2f*speed, 0f, chest[getAnimationNumber()], 0);

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
	public void completeAnimation(int animationNumber,
			ModelAutomatonsRookBoss bossModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) {

		World world = bossEntity.world;
		if(animationNumber == 50)
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
	}

	@Override
	public void applyDamage(int animationNumber, World world,
			EntityAutomatonsRookBoss bossEntity) {
		// TODO Auto-generated method stub

	} 

}
