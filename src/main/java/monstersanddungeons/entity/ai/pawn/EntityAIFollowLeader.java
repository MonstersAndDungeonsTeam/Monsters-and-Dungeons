package monstersanddungeons.entity.ai.pawn;

import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowLeader extends EntityAIBase{

	//responsible to ensure the members are always within a distance to group leader 

	EntityWhitePawns pawn;
	float maxDistance;

	public EntityAIFollowLeader(EntityWhitePawns pawn, float MaxDistance) {
		// TODO Auto-generated constructor stub
		this.pawn = pawn;
		this.maxDistance = MaxDistance;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		if(this.pawn.group_leader != null)
		{
			if(this.pawn.getDistanceSqToEntity(this.pawn.group_leader) > maxDistance)
			{
				if(!this.pawn.group_leader.isDead)
				{
					if(this.pawn.hasBottom == null)
					{
						this.startExecuting();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting() 
	{
		if(!pawn.getNavigator().tryMoveToEntityLiving(this.pawn.group_leader, 0.7f))
		{
			pawn.attemptTeleport(this.pawn.group_leader.posX, this.pawn.group_leader.posY, this.pawn.group_leader.posZ);
		}
	}

	@Override
	public boolean continueExecuting() {
		if(this.pawn.group_leader != null)
		{
			if(this.pawn.getDistanceSqToEntity(this.pawn.group_leader) > 10)
			{
				if(!this.pawn.group_leader.isDead)
				{
					if(this.pawn.hasBottom == null)
					{
						this.startExecuting();
						return true;
					}
				}
			}
		}
		return false;
	}
}
