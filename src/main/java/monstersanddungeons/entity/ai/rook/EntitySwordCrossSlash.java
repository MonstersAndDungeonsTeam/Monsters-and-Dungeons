package monstersanddungeons.entity.ai.rook;

import java.util.List;

import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySwordCrossSlash extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{
	//TODO: replace with teleporting strike

	boolean spawnCrossSection = false, teleportEntity = false;
	float crossSectionLength = 0;
	boolean canIncrement[] = new boolean[9];
	EntityPlayer attack_player = null;
	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/entities/crossSection.png");

	// right/left shoulders  Y,Z, right/left shoulder 4, 3, Z  Speed, Chest X
	float body[][] = 
		{
			{ 0f,   0f,   0f,   1f,   0f},
			{-40f,   0f,   0f,  1f,   0f},
			{ 60f,  0f,   0f,  5f,  30f},
		};

	public EntitySwordCrossSlash(int max, int cd, int phase, int expectedTicks) {
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

		if(getAnimationNumber() == 2)
		{
			this.setPaused(true);
			if(!this.spawnCrossSection)
			{
				GlStateManager.pushMatrix();
				GlStateManager.translate(-0.5, -1.2, -2- this.crossSectionLength);
				GlStateManager.scale(1f, 2f, 1f);
				Minecraft.getMinecraft().getTextureManager().bindTexture(this.location);
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder vertexbuffer = tessellator.getBuffer();

				vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

				vertexbuffer.pos(0, 1, 0).tex(0, 1).color(1f, 1f, 1f, 1f).normal(0, 0, 1).endVertex();
				vertexbuffer.pos(1, 1, 0).tex(1, 1).color(1f, 1f, 1f, 1f).normal(0, 0, 1).endVertex();
				vertexbuffer.pos(1, 0, 0).tex(1, 0).color(1f, 1f, 1f, 1f).normal(0, 0, 1).endVertex();
				vertexbuffer.pos(0, 0, 0).tex(0, 0).color(1f, 1f, 1f, 1f).normal(0, 0, 1).endVertex();

				tessellator.draw();
				GlStateManager.popMatrix();

				if(this.crossSectionLength < 10)
				{
					this.crossSectionLength+= 0.3 * speed;
				}else
				{
					this.spawnCrossSection = true;
					this.setPaused(false);
				}
			}
		}
		canIncrement[0] = bossModel.movePiece(bossModel.left_BigSword.getHandle(), body[getAnimationNumber()][3]* speed, 45f, 0, 0);
		canIncrement[1] = bossModel.movePiece(bossModel.right_BigSword.getHandle(), body[getAnimationNumber()][3]* speed, 45f, 0, 0);

		canIncrement[2] = bossModel.movePiece(bossModel.RightShoulder1, body[getAnimationNumber()][3]* speed, -30f, -body[getAnimationNumber()][0], body[getAnimationNumber()][1]);
		canIncrement[3] = bossModel.movePiece(bossModel.LeftShoulder1, body[getAnimationNumber()][3]* speed, -30f,  body[getAnimationNumber()][0], -body[getAnimationNumber()][1]);
		canIncrement[4] = bossModel.movePiece(bossModel.RightShoulder4, body[getAnimationNumber()][3]* speed, -35f, 0f, 0f);
		canIncrement[5] = bossModel.movePiece(bossModel.LeftShoulder3, body[getAnimationNumber()][3]* speed, -35f,  0f, 0f);
		canIncrement[6] = bossModel.movePiece(bossModel.MiddlePiece, 1f* speed, body[getAnimationNumber()][4], 0f, 0);

		canIncrement[7] = bossModel.movePiece(bossModel.LeftWrist, 1f* speed, 45, -45, 0);
		canIncrement[8] = bossModel.movePiece(bossModel.RightWrist, 1f* speed, 45, 45, 0);

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
		bossModel.idolAnimation();
	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) {
		// TODO Auto-generated method stub

		if(!this.teleportEntity)
		{
			bossEntity.faceEntity(attack_player, 10, 10);
			bossEntity.attemptTeleport(attack_player.posX, attack_player.posY, attack_player.posZ);
			this.teleportEntity = true;
		}
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) {

		if(this.attack_player != null)
		{
			List<Entity> entities  = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 3, bossEntity.getPosition().getY() - 3, bossEntity.getPosition().getZ() - 3, bossEntity.getPosition().getX() + 3, bossEntity.getPosition().getY() + 3, bossEntity.getPosition().getZ() + 3));
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(75.0D);

			for(Entity entity : entities)
			{
				if(entity != null)
				{
					bossEntity.attackEntityAsMob(entity);
				}
			}

		}
	}

	@Override
	public void resetAnimation() {
		super.resetAnimation();

		this.spawnCrossSection = false;
		this.crossSectionLength = 0;	
	}

}
