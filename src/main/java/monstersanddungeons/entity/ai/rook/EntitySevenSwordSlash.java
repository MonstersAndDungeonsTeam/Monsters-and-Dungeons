package monstersanddungeons.entity.ai.rook;

import java.util.List;

import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.entity.miscellaneous.EntityFlyingSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySevenSwordSlash extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss> {

	boolean canIncrement[] = new boolean[5];
	boolean spawnSwords = false;
	int increment = 0;
	EntityFlyingSword currentSword;
	EntityPlayer attack_player;

	public EntitySevenSwordSlash(int max, int cd, int phase, int expectedTicks) {
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
				return true;
			}
		}
		return false;
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale,
			float speed) {

		canIncrement[0] = bossModel.movePiece(bossModel.left_BigSword.getHandle(), 0.7f* speed, 135f, 0, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.right_BigSword.getHandle(), 0.7f* speed, 135f, 0, 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder1, 0.7f* speed, -45f, -45f, 0);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder1, 0.7f* speed, -45f, 45f, 0);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, 10f* speed, 0f, 7200f + increment, 0);
		increment += 5;

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
		if(bossEntity instanceof EntityAutomatonsRookBoss)	
		{
			if(!spawnSwords)
			{
				bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(70.0D);
				EntityPlayer player = attack_player;

				if(player != null)
				{
					((EntityAutomatonsRookBoss) bossEntity).spawnFlyingSwords(player.getPosition());
				}else
				{
					((EntityAutomatonsRookBoss) bossEntity).spawnFlyingSwords(bossEntity.getPosition());
				}
				this.spawnSwords = true;
			}else
			{
				if(currentSword == null || currentSword.isDead)
				{
					this.currentSword = ((EntityAutomatonsRookBoss) bossEntity).getRandomSword();
				}else
				{
					if(bossEntity.getPosition().distanceSq(currentSword.getPosition().getX(), currentSword.getPosition().getY(), currentSword.getPosition().getZ()) <= 7)
					{
						bossEntity.world.addWeatherEffect(new EntityLightningBolt(bossEntity.world, bossEntity.getPosition().getX(), bossEntity.getPosition().getY(), bossEntity.getPosition().getZ(), true));
						currentSword.setDead();
						this.currentSword = null;

					}else
						bossEntity.getNavigator().tryMoveToXYZ(currentSword.getPosition().getX(), currentSword.getPosition().getY(), currentSword.getPosition().getZ(), 1f);
				}
			}

			List<Entity> entities = bossEntity.world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() -3, bossEntity.getPosition().getY() -3, bossEntity.getPosition().getZ() -3, bossEntity.getPosition().getX() +3, bossEntity.getPosition().getY() +3, bossEntity.getPosition().getZ() +3));

			for(Entity entity : entities)
			{
				if(!(entity instanceof EntityFlyingSword))
				{
					bossEntity.attackEntityAsMob(entity);
				}
			}
		}
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) 
	{
		//applied through collision 
	}

	@Override
	public void resetAnimation() {
		super.resetAnimation();
		this.spawnSwords = false;
		this.increment = 0;
	}
}
