package monstersanddungeons.entity.ai.pawn;

import java.util.List;

import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAILeaderStackMembers extends EntityAIBase
{

	List<EntityWhitePawns> nearby_pawns;
	EntityPawnCommander pawn;
	
	
	public EntityAILeaderStackMembers(EntityPawnCommander leader) 
	{
		this.pawn = leader;
		this.setMutexBits(8);
	}

	@Override
	public boolean shouldExecute() 
	{
		if(this.pawn.isPartyLeader)
		{
			List<EntityWhitePawns> nearby_pawn = pawn.world.getEntitiesWithinAABB(EntityWhitePawns.class, new AxisAlignedBB(pawn.getPosition().getX() - 15, pawn.getPosition().getY() - 15, pawn.getPosition().getZ() - 15, pawn.getPosition().getX() + 15, pawn.getPosition().getY() + 15, pawn.getPosition().getZ() + 15));

			if(nearby_pawn.size() > 2)
			{
				this.nearby_pawns = nearby_pawn;
				this.startExecuting();
				return true;
			}
		}
		return false;
	}

	@Override
	public void startExecuting() 
	{
		if(this.nearby_pawns.size() > 2)
		{
			for(int i = 0; i < this.nearby_pawns.size(); i ++)
			{
				EntityWhitePawns new_top = this.nearby_pawns.get(i);	

				if(new_top.hasBottom == null && new_top.hasTop == null)
				{
					for(int j = 0; j < this.nearby_pawns.size(); j ++)
					{
						if(!nearby_pawns.get(j).equals(new_top))
						{
							EntityWhitePawns new_bottom = nearby_pawns.get(j);
							int currentChain = EntityAILeaderStackMembers.getChainSize(new_bottom);
							
							if(new_bottom.hasTop == null && currentChain < 2)
							{
								new_top.hasBottom = new_bottom;
								new_bottom.hasTop = new_top;
								return;
							}
						}
					}
				}
			}
		}
	}
	public static int getChainSize(EntityWhitePawns starting)
	{
		int size = 0;
		EntityWhitePawns currentPawn = starting.hasTop;
		
		while(currentPawn != null)
		{
			currentPawn = currentPawn.hasTop;
			size++;
		}

		currentPawn = starting;
		while(currentPawn != null)
		{
			currentPawn = currentPawn.hasBottom;
			size++;
		}
		
		return size - 1;
	}
}
