package monstersanddungeons.entity.ai.ent;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelEnt;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.world.EntityEnt;

public class EntityThornAttack extends EntitySpecialAttackBase<ModelEnt, EntityEnt> {

	BlockPos attack_Location;
	EntityPlayer attack_player;

	public EntityThornAttack(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
	}

	@Override
	public boolean shouldActivate(World world, EntityEnt bossEntity) {
		// TODO Auto-generated method stub

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
	public void tickAnimation(ModelEnt bossModel, float scale, float speed) 
	{
		bossModel.movePiece(bossModel.lowerbody, 1f *speed, 45f, 0f, 0f);
		bossModel.movePiece(bossModel.leftupperarm, 1f *speed, -60f, 0f, 0f);
		bossModel.movePiece(bossModel.rightupperarm, 1f *speed, -60f, 0f, 0f);

		bossModel.movePiece(bossModel.leftupperleg, 1f *speed, -45f, 0f, 0f);
		bossModel.movePiece(bossModel.rightupperleg, 1f *speed, -45f, 0f, 0f);

		bossModel.movePiece(bossModel.leftboot, 1f *speed, 90f, 0f, 0f);
		bossModel.movePiece(bossModel.rightboot, 1f *speed, 90f, 0f, 0f);
		bossModel.moveoffSet(bossModel.lowerbody, 0.02f, 1.2f);

	}

	@Override
	public void completeAnimation(int animationNumber, ModelEnt bossModel) 
	{


	}

	@Override
	public void activateEffect(int animationNumber, EntityEnt bossEntity) 
	{
		if(animationNumber == 10)
		{
			this.attack_Location = attack_player.getPosition();
		}
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityEnt bossEntity) 
	{
		if(attack_Location != null)
		{
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(attack_Location.getX() - 2, attack_Location.getY() - 2, attack_Location.getZ() - 2, attack_Location.getX() + 2, attack_Location.getY() + 2, attack_Location.getZ() + 2));
			bossEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);

			for(Entity entity : entities)
			{
				if(entity instanceof EntityLivingBase)
				{
					bossEntity.attackEntityAsMob(entity);
					world.setBlockState(attack_Location, Blocks.LOG.getDefaultState());
					world.setBlockState(attack_Location.up(), Blocks.LOG.getDefaultState());
					world.setBlockState(attack_Location.up().up(), Blocks.LOG.getDefaultState());
				}
			}
		}
	}
}
