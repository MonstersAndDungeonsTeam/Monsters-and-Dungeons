package monstersanddungeons.entity.ai.marshDweller;

import java.util.Random;

import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISitDown extends EntityAIBase 
{

	int sitting;
	EntityMarshDweller dweller;
	
	public EntityAISitDown(EntityMarshDweller dweller) 
	{
		this.dweller = dweller;
		this.setMutexBits(1);
	}
	
	@Override
	public void startExecuting() 
	{
		this.dweller.setSittingDown(true);
		this.sitting--;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		if(dweller.getSitting_cd() == 0)
		{
			this.sitting = 200;
			return true;
		}
		return false;
	}
	@Override
	public boolean shouldContinueExecuting() 
	{
		this.dweller.setSittingDown(true);
		sitting--;
		return sitting > 0;
	}
	
	@Override
	public void resetTask() 
	{
		Random rand = new Random();
		this.dweller.setSittingDown(false);
		this.dweller.setSitting_cd(1000 + rand.nextInt(500));
	}
}
