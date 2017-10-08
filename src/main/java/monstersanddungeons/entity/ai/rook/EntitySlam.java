package monstersanddungeons.entity.ai.rook;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;

public class EntitySlam extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	boolean canIncrement[] = new boolean[6];
	float arms[] = {-50f,-120f};
	
	//middle, height speed;
	float middle[][] = 
		{
			{-60f, 0f, 1.2f},	
			{120f, 0.3f, 5f},	
		};
	
	public EntitySlam(int max, int cd, int phase, int expectedTicks) {
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
		// TODO Auto-generated method stub
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 1.2f*speed, arms[getAnimationNumber()], -20, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 1.2f*speed,  arms[getAnimationNumber()], 20, 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 1.2f*speed, -45, 0, 45);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 1.2f*speed, -45, 0, -45);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, middle[getAnimationNumber()][2]*speed, middle[getAnimationNumber()][0], 0, 0);
		canIncrement[5] = bossModel.moveoffSet(0.01f * speed, middle[getAnimationNumber()][1]);
		
		
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
	public void activateEffect(int animationNumber,
			EntityAutomatonsRookBoss bossEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) 
	{
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 7, bossEntity.getPosition().getY() - 7, bossEntity.getPosition().getZ() - 7, bossEntity.getPosition().getX() + 7, bossEntity.getPosition().getY() + 7, bossEntity.getPosition().getZ() + 7));
		
		if(!entities.isEmpty())
		{
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);
			world.createExplosion(bossEntity, bossEntity.posX, bossEntity.posY, bossEntity.posZ, 2, false);
			
			for(Entity entity : entities)
			{
				bossEntity.attackEntityAsMob(entity);
			}
		}
	}
}
