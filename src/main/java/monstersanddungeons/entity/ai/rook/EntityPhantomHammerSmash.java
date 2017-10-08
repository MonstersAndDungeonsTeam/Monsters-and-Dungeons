package monstersanddungeons.entity.ai.rook;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import monstersanddungeons.client.ClientProxy;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityPhantomHammerSmash extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss> {

	boolean summonPhantoms = false, summonSphere = false, summonSafeZones = false, spoken = false;
	boolean canIncrement[] = new boolean[15];

	ModelAutomatonsRookBoss phantoms = new ModelAutomatonsRookBoss();
	ModelAutomatonsRookBoss phantoms1 = new ModelAutomatonsRookBoss();
	ModelAutomatonsRookBoss phantoms2 = new ModelAutomatonsRookBoss();
	ModelAutomatonsRookBoss phantoms3 = new ModelAutomatonsRookBoss();

	int PhantomNumber = 0, cooldown = 0;
	float renderSphere = 0;

	//right shoulder 1 X, left shoulder 1 X, right shoulder 4 X, left shoulder 3 X, right shoulder 4 Y
	// right shoulder 4 Z, right finger 1 2 and 3, left wristZ, rightShoulder 1 Y, Left Shoulder 1 Y
	float arms[][] = 
		{
			{0F,0F,0F,0F,0F,0f, 0f, 0f, 0f},
			{  0F, -60F,   0F,  -60F,   0F,   0f, 0f, 0f, 0f},
			{-10F, -50F, -10F,  -40F,   0F,   0f, 0f,0f,0f},
			{-10F, -50F, -30F,  -40F,   0F,   0f, 0f ,0f, 0f},
			{-0F,  -45F, -90F,  -40F,  40F,   0f, 0f ,0f, 0f},
			{-0F,  -45F, -90F,  -40F,  0F,   -90f, 0f ,0f, 0f},
			{0F,0F,0F,0F,0F,0f, 0f,  0f, 0f},
			{-90F,  -90F, -0F,  -0F,  0F,   -0f, 45f, 35f, -45f},
			{-105F,  -105F, -0F,  -0F,  0F,   -0f, 45f, 35f, -45f},
			{-105F,  -105F, -0F,  -0F,  0F,   -0f, 30f, 35f, -45f},
			{-0F,  -45F, -90F,  -40F,  40F,   0f, 0f ,0f, 0f},
		}; 

	//right armor piece, left armor piece, right shin, left shin
	float legs[][] = 
		{
			{0F,0F,0F,0F,0F,0f},
			{   0F,   0F,   0F,   0F,   0F,   0f},
			{   0F,   0F,   0F,   0F,   0F,   0f}, //starts to move down 
			{  -40F,   20F,  40F,   0F,   0F,   0f},
			{  -80F,   20F,  80F,   50F,   0F,   0f}, 
			{  -80F,   20F,  80F,   50F,   0F,   0f}, 
			{0F,0F,0F,0F,0F,0f},
			{0F,0F,0F,0F,0F,0f},
			{0F,0F,0F,0F,0F,0f},
			{0F,0F,0F,0F,0F,0f},
			{  -80F,   20F,  80F,   50F,   0F,   0f}, 
		}; 

	//Middle piece, 
	float chest[][]= 
		{
			{0F,0.7F},
			{0F,0.7F},
			{25F,0.7F}, //starts to move down
			{25F,0.7F},
			{0F,0.7F},
			{0F,0.7F},
			{0F,0.7F},
			{0F,0.7f},
			{-30F,0.7f},
			{80F, 3f},
			{0F,0.7F},
		}; 

	float height[] = {0f, 0f, 0f, 0f, 0.6f, 0.6f, 0, 0f, 0f, 0f, 0.6f}, weapon[] = {45, 310, 250, 250, 270, 270, 45, 45, 90, 90, 270f};

	@Override
	public boolean shouldActivate(World world, EntityAutomatonsRookBoss bossEntity) {
	
		return super.shouldActivate(world, bossEntity);
	}

	public EntityPhantomHammerSmash(int Animmax, int cd, int phase, int expectedTime) {
		super(Animmax, cd, phase, expectedTime);
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) 
	{
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 0.7f * speed, arms[getAnimationNumber()][0], arms[getAnimationNumber()][7], 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 0.7f* speed, arms[getAnimationNumber()][1], arms[getAnimationNumber()][8], 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 0.7f* speed, arms[getAnimationNumber()][2], arms[getAnimationNumber()][4], arms[getAnimationNumber()][5]);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 0.7f* speed, arms[getAnimationNumber()][3], 0, 0);
		canIncrement[14] = bossModel.movePiece(bossModel.LeftWrist, 0.7f* speed, 0, 0, arms[getAnimationNumber()][6]);
		canIncrement[4] = bossModel.movePiece(bossModel.MiddlePiece, chest[getAnimationNumber()][1]* speed, chest[getAnimationNumber()][0], 0, 0);

		canIncrement[5] = bossModel.movePiece(bossModel.RightLegArmor, 0.7f* speed, legs[getAnimationNumber()][0], 0, 0);
		canIncrement[6] = bossModel.movePiece(bossModel.LeftLegArmor, 0.7f* speed, legs[getAnimationNumber()][1], 0, 0);
		canIncrement[7] = bossModel.movePiece(bossModel.RightShin, 0.7f* speed, legs[getAnimationNumber()][2], 0, 0);
		canIncrement[8] = bossModel.movePiece(bossModel.LeftShin, 0.7f* speed, legs[getAnimationNumber()][3], 0, 0);

		canIncrement[9] = bossModel.movePiece(bossModel.hammer.Shaft, 2.5f* speed, weapon[getAnimationNumber()], 0, 0);
		canIncrement[10] = bossModel.moveoffSet(0.01f * speed, height[getAnimationNumber()]);

		canIncrement[11] = bossModel.movePiece(bossModel.RightFinger1, 0.7f* speed, 0, 0, arms[getAnimationNumber()][5]);
		canIncrement[12] = bossModel.movePiece(bossModel.RightFinger2, 0.7f* speed, 0, 0, arms[getAnimationNumber()][5]);
		canIncrement[13] = bossModel.movePiece(bossModel.rightHand1, 0.7f* speed, 0, 0, arms[getAnimationNumber()][5]);

		bossModel.right_BigSword.getHandle().isHidden = true;
		bossModel.left_BigSword.getHandle().isHidden = true;
		bossModel.hammer.Shaft.isHidden = false;
		
		if(getAnimationNumber() == 4)
		{
			if(!this.summonPhantoms)
				this.setPaused(true);

			if(this.cooldown == 0)
			{
				this.cooldown = (int) (180/speed);
				this.PhantomNumber++;

				if(this.PhantomNumber >= 5)
				{
					this.summonPhantoms = true;
					this.setPaused(false);
				}

			}else
				this.cooldown--;
		}
		if(PhantomNumber > 1)
			phantoms.renderPhantomRook(phantoms, -5f, 5f, scale, -90, arms, legs, chest, weapon, height, getAnimationNumber(), speed);

		if(PhantomNumber > 2)
			phantoms1.renderPhantomRook(phantoms1, 5f, 5f, scale, 90, arms, legs, chest, weapon, height, getAnimationNumber(), speed);	

		if(PhantomNumber > 3)
			phantoms2.renderPhantomRook(phantoms2, 0f, 10f, scale, 180,arms, legs, chest, weapon, height, getAnimationNumber(), speed);

		if(getAnimationNumber() == 5)
		{	
			if(!this.summonSphere)
				this.setPaused(true);

			if(this.cooldown == 0)
			{
				this.cooldown = (int) (10/speed);
				this.renderSphere += 0.2;

				if(this.renderSphere >= 5)
				{
					this.summonSphere = true;
					this.setPaused(false);
				}
			}else
				this.cooldown--;

		}

		if(renderSphere > 0)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(0, 0, -4.5);
			GL11.glScalef(renderSphere, renderSphere, renderSphere);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(255F, 69F, 0F, 0.2F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);

			GL11.glCallList(ClientProxy.sphereOutside);
			GL11.glCallList(ClientProxy.sphereInside);

			GL11.glPopMatrix();
		}


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
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) 
	{
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 50, bossEntity.getPosition().getY() - 50, bossEntity.getPosition().getZ() - 50, bossEntity.getPosition().getX() + 50, bossEntity.getPosition().getY() + 50, bossEntity.getPosition().getZ() + 50));
		bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100.0D);
		
		world.createExplosion(bossEntity, bossEntity.posX + 4, bossEntity.posY, bossEntity.posZ, 1, false);

		if(bossEntity instanceof EntityAutomatonsRookBoss)
		{
			for(Entity entity : entities)
			{
				if(entity instanceof EntityPlayer)
				{
					if(!(((EntityAutomatonsRookBoss) bossEntity).isPlayerOnSafeZone((EntityPlayer)entity)))
					{
						bossEntity.attackEntityAsMob(entity);
					}
				}else
					bossEntity.attackEntityAsMob(entity);
			}
		}
	}

	@Override
	public void completeAnimation(int animationNumber, @Nullable ModelAutomatonsRookBoss bossModel) 
	{
		this.setAnimationNumber(10);
		this.PhantomNumber = 0;
		this.renderSphere = 0;
		this.summonPhantoms = false;
		this.summonSphere = false;
		this.setPaused(false);
		
		if(!this.spoken)
		{
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Rook Boss: So tired..."));	
			this.spoken = true;
		}
	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) {
	
		if(animationNumber < getExpectedTicks()/2)
		{
			if(bossEntity instanceof EntityAutomatonsRookBoss)
			{
				if(!this.summonSafeZones)
				{
					((EntityAutomatonsRookBoss) bossEntity).spawnSafeZones();
					this.summonSafeZones = true;
				}
			}
		}
	}
	
	@Override
	public void resetAnimation() {
		super.resetAnimation();
		
		this.PhantomNumber = 0;
		this.renderSphere = 0;
		this.summonPhantoms = false;
		this.summonSphere = false;
		this.summonSafeZones = false;
		this.spoken = false;
	
	}
}
