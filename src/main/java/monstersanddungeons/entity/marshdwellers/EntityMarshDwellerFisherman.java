package monstersanddungeons.entity.marshdwellers;

import net.minecraft.world.World;

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
