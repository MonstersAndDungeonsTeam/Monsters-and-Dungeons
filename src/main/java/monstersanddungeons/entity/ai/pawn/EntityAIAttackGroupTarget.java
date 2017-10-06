package monstersanddungeons.entity.ai.pawn;

import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAttackGroupTarget extends EntityAIBase
{

	EntityWhitePawns pawn;
	public EntityAIAttackGroupTarget(EntityWhitePawns pawn) 
	{
		this.pawn = pawn;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() 
	{
		if(pawn.group_leader != null)
		{
			if(pawn.group_leader.party_attack_target != null)
			{
				if(this.pawn.hasBottom == null)
				{
					this.startExecuting();
					return true;
				}
			}
		}
		return false;
	}



	@Override
	public void startExecuting() 
	{
		pawn.getNavigator().tryMoveToEntityLiving(pawn.group_leader.party_attack_target, 0.6f);
	}
	
}
