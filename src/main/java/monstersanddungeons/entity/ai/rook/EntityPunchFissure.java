package monstersanddungeons.entity.ai.rook;

import java.util.List;

import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityPunchFissure extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	
	boolean spawnFissure = false;
	boolean canIncrement[] = new boolean[5];
	float arm[] = {45, -45};
	

	public EntityPunchFissure(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) 
	{
		
		canIncrement[0] = bossModel.movePiece(bossModel.RightShoulder1, 4f*speed, -arm[getAnimationNumber()], -arm[getAnimationNumber()], 0);
		canIncrement[1] = bossModel.movePiece(bossModel.LeftShoulder1, 4f*speed,  -arm[getAnimationNumber()], arm[getAnimationNumber()], 0);
		
		
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
		
	}
	
	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) 
	{
		if(!spawnFissure)
		{
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(bossEntity, new AxisAlignedBB(bossEntity.getPosition().getX() - 10, bossEntity.getPosition().getY() - 10, bossEntity.getPosition().getZ() - 10, bossEntity.getPosition().getX() + 10, bossEntity.getPosition().getY() + 10, bossEntity.getPosition().getZ() + 10));
			
			for(Entity entity : entities)
			{
				if(entity instanceof EntityPlayer)
				{
					if(!entity.isAirBorne)
					{
						world.setBlockState(entity.getPosition().down(), Blocks.SOUL_SAND.getDefaultState());
						world.setBlockState(entity.getPosition().down().north(), Blocks.SOUL_SAND.getDefaultState());
						world.setBlockState(entity.getPosition().down().south(), Blocks.SOUL_SAND.getDefaultState());
						world.setBlockState(entity.getPosition().down().east(), Blocks.SOUL_SAND.getDefaultState());
						world.setBlockState(entity.getPosition().down().west(), Blocks.SOUL_SAND.getDefaultState());
					}
				}
			}
		}
	}
}
