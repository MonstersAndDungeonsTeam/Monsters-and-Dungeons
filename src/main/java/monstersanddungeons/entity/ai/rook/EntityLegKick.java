package monstersanddungeons.entity.ai.rook;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;

public class EntityLegKick extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	Entity attack_player;
	boolean canIncrement[] = new boolean[3];
	float legs[][] = {
			
			{50, 0f, 3f},
			{-70, 0f, 3f},
			{-70, 360f, 10f}
			};
	
	public EntityLegKick(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean shouldActivate(World world, EntityAutomatonsRookBoss bossEntity) {
		
		if(super.shouldActivate(world, bossEntity))
		{
			attack_player = world.getClosestPlayerToEntity(bossEntity, 50);
			if(attack_player != null)
			{
				if(attack_player.getDistanceSqToEntity(bossEntity) < 50)
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
		// TODO Auto-generated method stub
		canIncrement[0] = bossModel.movePiece(bossModel.rightMiddlePiece, 10f*speed, 0, legs[getAnimationNumber()][1], 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftBelt, legs[getAnimationNumber()][2]*speed, legs[getAnimationNumber()][0], legs[getAnimationNumber()][1], 0);
		canIncrement[2] = bossModel.movePiece(bossModel.MiddlePiece, 10f*speed, 0, legs[getAnimationNumber()][1], 0);
	
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
	public void activateEffect(int animationNumber,EntityAutomatonsRookBoss bossEntity) 
	{
		World world = bossEntity.worldObj;
		if(animationNumber == 30)
		{
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 5, bossEntity.getPosition().getY() - 5, bossEntity.getPosition().getZ() - 5, bossEntity.getPosition().getX() + 5, bossEntity.getPosition().getY() + 5, bossEntity.getPosition().getZ() + 5));
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);

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
		if(animationNumber == 10)
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
