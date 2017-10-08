package monstersanddungeons.entity.ai.pawn;

import java.util.List;
import java.util.Random;

import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIAttackTarget extends EntityAIBase
{
	int hammer_cd;
	EntityWhitePawns current;
	List<EntityWhitePawns> nearby_pawn;

	public EntityAIAttackTarget(EntityWhitePawns currentPawn) 
	{
		this.current = currentPawn;
	}

	@Override
	public boolean shouldExecute() 
	{
		if(this.current.group_leader == null)
		{
			List<EntityWhitePawns> nearby_pawn = current.world.getEntitiesWithinAABB(EntityWhitePawns.class, new AxisAlignedBB(current.getPosition().getX() - 15, current.getPosition().getY() - 15, current.getPosition().getZ() - 15, current.getPosition().getX() + 15, current.getPosition().getY() + 15, current.getPosition().getZ() + 15));
			if(nearby_pawn.size() > 1)
			{
				this.nearby_pawn = nearby_pawn;
				this.startExecuting();
				return true;
			}
		}
		return false;
	}

	@Override
	public void startExecuting() 
	{
		EntityPlayer player = current.world.getNearestAttackablePlayer(current, 30, 30);
		if(player != null)
		{
			current.getNavigator().tryMoveToEntityLiving(player, 0.5f);

			if(current.getDistanceSq(player) < 7)
			{
				if(this.hammer_cd == 0)
				{
					Random rand = new Random();
					if(rand.nextInt(10) == 0)
					{
						current.updateClient(0);
						current.hammer_attack = true;
						this.hammer_cd = 200 + rand.nextInt(300);
					}
				}else
					this.hammer_cd--;
			}
		}
	}
}
