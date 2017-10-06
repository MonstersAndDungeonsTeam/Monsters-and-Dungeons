package monstersanddungeons.entity.ai.pawn;

import java.util.List;

import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIFindLeader extends EntityAIBase{


	EntityWhitePawns pawn;
	World world;
	int cooldown;

	public EntityAIFindLeader(EntityWhitePawns pawn) 
	{
		this.pawn = pawn;
		world = pawn.worldObj;
		this.setMutexBits(8);
	}

	@Override
	public boolean shouldExecute() 
	{
		if(this.pawn.group_leader == null)
		{
			this.startExecuting();
			return true;
		}
		return false;
	}

	@Override
	public void startExecuting() 
	{
		
		List<EntityPawnCommander> nearby_pawn = world.getEntitiesWithinAABB(EntityPawnCommander.class, new AxisAlignedBB(pawn.getPosition().getX() - 15, pawn.getPosition().getY() - 15, pawn.getPosition().getZ() - 15, pawn.getPosition().getX() + 15, pawn.getPosition().getY() + 15, pawn.getPosition().getZ() + 15));

		for(EntityPawnCommander group_pawn : nearby_pawn)
		{
			if(group_pawn.isPartyLeader)
			{
				
				pawn.group_leader = group_pawn;
				return;
			}
		}
	}
}
