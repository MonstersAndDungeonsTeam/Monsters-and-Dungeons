package monstersanddungeons.entity.ai.rook;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import monstersanddungeons.client.ClientProxy;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;

public class EntityHammerFireSwing extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	boolean spawnFireSphere = false;
	float renderSphere = 0;
	boolean canIncrement[] = new boolean[6];

	//right 4 X, Y, left 4 X, Y, Hammer, middle Piece
	float arms[][] = 
		{
			{-50F, -45F,-50F,45F,90F,-25f},
			{-50F, -45F,-50F,45F,90F,-25f},
			{0F, 0F,0F,0F,45F,0f},
		}; 

	public EntityHammerFireSwing(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) 
	{
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 1.2f*speed, -50, -45, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 1.2f*speed,  -50, 45, 0);
		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder4, 1.2f*speed, 0, 0, 0);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder3, 1.2f*speed, 0, 0, 0);
		canIncrement[4] = bossModel.movePiece(bossModel.hammer.Shaft, 2.5f*speed, 90f, 0, 0);
		canIncrement[5] = bossModel.movePiece(bossModel.MiddlePiece, 1.2f*speed, -25f, 0, 0);

		if(getAnimationNumber() == 1)
		{
			if(!this.spawnFireSphere)
			{
				this.setPaused(true);
				if(renderSphere > 0)
				{
					GL11.glPushMatrix();

					GL11.glTranslated(0, 0, 0);
					GL11.glScalef(renderSphere, renderSphere, renderSphere);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glDepthMask(false);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(128F, 0F, 128F, 0.7F);
					GL11.glEnable(GL11.GL_ALPHA_TEST);

					GL11.glCallList(ClientProxy.sphereOutside);
					GL11.glCallList(ClientProxy.sphereInside);

					GL11.glPopMatrix();

				}
				if(renderSphere < 10)
				{
					renderSphere += (0.15 *speed);
				}else
				{
					this.spawnFireSphere = true;
					this.setPaused(false);
				}
			}
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
	public void completeAnimation(int animationNumber, ModelAutomatonsRookBoss bossModel) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) 
	{
		

	}

	@Override
	public void applyDamage(int animationNumber, World world,EntityAutomatonsRookBoss bossEntity) 
	{
		// TODO Auto-generated method stub
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 10, bossEntity.getPosition().getY() - 10, bossEntity.getPosition().getZ() - 10, bossEntity.getPosition().getX() + 10, bossEntity.getPosition().getY() + 10, bossEntity.getPosition().getZ() + 10));
		bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(70.0D);
	
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
	public void resetAnimation() 
	{
		super.resetAnimation();
		this.renderSphere = 0;
		this.spawnFireSphere = false;
	}
}
