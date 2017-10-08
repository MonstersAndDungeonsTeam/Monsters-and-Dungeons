package monstersanddungeons.entity.ai.rook;

import java.util.List;

import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHammerLeapSmash extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	float addedHeight = 0;
	boolean addedVelocity = false;
	boolean canIncrement[] = new boolean[11];
	EntityPlayer attack_player;

	//right shoulder 1 X, left shoulder 1 X, right shoulder 4 X, left shoulder 3 X, right shoulder 4 Y
	// right shoulder 4 Z, right finger 1 2 and 3, left wristZ, rightShoulder 1 Y, Left Shoulder 1 Y
	float arms[][] = 
		{
			{-30F, 30F,-30F,-50F,0F,0f, 0f, 0f, 0f},
			{0F,-60F,-65F,-10F,0F,0f, 0f, 0f, 0f},
			{0F,-60F, -65F, -10F,0F,0f, 0f, 0f, 0f},
			{0F,-0F, -0F, -0F,0F,0f, 0f, 0f, 0f},
		}; 

	//right armor piece, left armor piece, right shin, left shin
	float legs[][] = 
		{
			{-50F, 35F, 50F, -50F,0F,0f},
			{275F,275F, 0F, 0F,0F,0f},
			{275F,275F, 0F, 0F,0F,0f},
			{0F, 0F, 0F, 0F,0F,0f},
		}; 

	//Middle piece, speed, middle Y
	float chest[][]= 
		{
			{25F,1.2F, 3F},
			{370F,2.5F, 30F},
			{370F,2.5F, 0F},
			{0F, 2.5F, 0F},
		}; 

	float height[] = {0.1f, 1.4f, 1.4f, 0f}, weapon[] = {90f,135, 155f, 45f};

	public EntityHammerLeapSmash(int max, int cd, int phase, int expectedTicks) {
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
				if(attack_player.getDistanceSq(bossEntity) > 20)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) {
 
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 3.5f * speed, arms[getAnimationNumber()][0], arms[getAnimationNumber()][7], 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 3.5f* speed, arms[getAnimationNumber()][1], arms[getAnimationNumber()][8], 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 3.5f* speed, arms[getAnimationNumber()][2], arms[getAnimationNumber()][4], arms[getAnimationNumber()][5]);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 3.5f* speed, arms[getAnimationNumber()][3], 0, 0);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, chest[getAnimationNumber()][1]* speed, chest[getAnimationNumber()][0], chest[getAnimationNumber()][2], 0);

		canIncrement[5] = bossModel.movePiece(bossModel.RightLegArmor, 4f* speed, legs[getAnimationNumber()][0], 0, 0);
		canIncrement[6] = bossModel.movePiece(bossModel.LeftLegArmor, 4f* speed, legs[getAnimationNumber()][1], 0, 0);
		canIncrement[7] = bossModel.movePiece(bossModel.RightShin, 4f* speed, legs[getAnimationNumber()][2], 0, 0);
		canIncrement[8] = bossModel.movePiece(bossModel.LeftShin, 4f* speed, legs[getAnimationNumber()][3], 0, 0);

		canIncrement[9] = bossModel.movePiece(bossModel.hammer.Shaft, 6f* speed, weapon[getAnimationNumber()], 0, 0);
		canIncrement[10] = bossModel.moveoffSet(0.06f * speed, height[getAnimationNumber()]);


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

		if(animationNumber > 10)
		{
			if(this.addedHeight < 15)
			{
				this.addedHeight += 0.5;
			}else
			{
				if(!this.addedVelocity)
				{
					EntityPlayer player = attack_player;
					if(player != null)
					{
						bossEntity.setPosition(player.posX, player.posY, player.posZ);
						this.addedVelocity = true;
					}
				}
			}
			bossEntity.posY = bossEntity.posY + this.addedHeight;
		}
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) {
		// TODO Auto-generated method stub
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 3, bossEntity.getPosition().getY() - 3, bossEntity.getPosition().getZ() - 3, bossEntity.getPosition().getX() + 3, bossEntity.getPosition().getY() + 3, bossEntity.getPosition().getZ() + 3));
		bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(75.0D);

		for(Entity entity : entities)
		{
			bossEntity.attackEntityAsMob(entity);
		}
	}

	@Override
	public void resetAnimation() {
		// TODO Auto-generated method stub
		super.resetAnimation();
		this.addedVelocity = false;
		this.addedHeight = 0f;
	}

}
