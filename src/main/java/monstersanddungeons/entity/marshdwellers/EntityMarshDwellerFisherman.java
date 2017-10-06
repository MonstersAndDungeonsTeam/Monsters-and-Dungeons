package monstersanddungeons.entity.marshdwellers;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.marshDweller.EntityAISitDown;

public class EntityMarshDwellerFisherman extends EntityMarshDweller{


	public EntityMarshDwellerFisherman(World worldIn) {
		super(worldIn);
		
		this.setSize(1.0f, 2.4f);
		this.setCustomNameTag("Fisherman");
		this.setAlwaysRenderNameTag(true);
	}

	@Override
	public int getMonsterID() {
	
		return 6;
	}

	@Override
	public void acivateAnimationby(int animation, int phase) {
		
		
	}

	@Override
	public void resetAnimation() {
		
		
	}

}
