package monstersanddungeons.entity.ai.pawn;

import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILeaderFindAttackTarget extends EntityAIBase {


	float maxDistance;
	EntityPawnCommander leader;
	public EntityAILeaderFindAttackTarget(EntityPawnCommander pawn, float MaxDistance) 
	{
		leader = pawn;
		maxDistance = MaxDistance;
		this.setMutexBits(8);
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub

		if(leader.isPartyLeader)
		{
			if(leader.party_attack_target == null)
			{
				this.startExecuting();
				return true;
			}else if(leader.party_attack_target.isDead || leader.getDistanceSqToEntity(leader.party_attack_target) > maxDistance)
			{
				this.startExecuting();
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void startExecuting() 
	{
		leader.party_attack_target = leader.worldObj.getClosestPlayerToEntity(leader, maxDistance);
	}
}
