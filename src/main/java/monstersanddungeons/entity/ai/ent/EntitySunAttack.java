package monstersanddungeons.entity.ai.ent;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelEnt;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.miscellaneous.EntitySunBeam;
import monstersanddungeons.entity.world.EntityEnt;

public class EntitySunAttack extends EntitySpecialAttackBase<ModelEnt, EntityEnt> {


	List<EntitySunBeam> sun = new ArrayList<EntitySunBeam>();

	public EntitySunAttack(int max, int cd, int phase, int expectedTicks) 
	{
		super(max, cd, phase, expectedTicks);
	}

	@Override
	public boolean shouldActivate(World world, EntityEnt bossEntity) 
	{
		if(super.shouldActivate(world, bossEntity))
		{
			List<Entity> nearby = bossEntity.worldObj.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 15, bossEntity.getPosition().getY() - 15, bossEntity.getPosition().getZ() - 15, bossEntity.getPosition().getX() + 15, bossEntity.getPosition().getY() + 15, bossEntity.getPosition().getZ() + 15));
			for(Entity entity : nearby)
			{	
				if(entity instanceof EntityLivingBase)
				{
					EntitySunBeam beam = new EntitySunBeam(world);
					beam.setPosition(entity.posX, entity.posY, entity.posZ);
					sun.add(beam);
					world.spawnEntityInWorld(beam);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void tickAnimation(ModelEnt bossModel, float scale, float speed) 
	{
		bossModel.movePiece(bossModel.rightupperarm, 2f, -45f, -45f, 0f);
		bossModel.movePiece(bossModel.leftupperarm, 2f, -45f, 45f, 0f);
		bossModel.movePiece(bossModel.upperbody, 2f, -45f, 0f, 0f);
	}

	@Override
	public void completeAnimation(int animationNumber, ModelEnt bossModel) 
	{

	}

	@Override
	public void activateEffect(int animationNumber, EntityEnt bossEntity) 
	{


	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityEnt bossEntity) 
	{

	}
}
