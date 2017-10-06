package monstersanddungeons.entity.marshdwellers;

import net.minecraft.world.World;

public class EntityMarshDwellerShaman extends EntityMarshDweller {

	public EntityMarshDwellerShaman(World worldIn) {
		super(worldIn);
		
		this.setSize(1.0f, 2.4f);
		this.setCustomNameTag("Shaman");
		this.setAlwaysRenderNameTag(true);
	}

	@Override
	public int getMonsterID() {
	
		return 7;
	}

	@Override
	public void acivateAnimationby(int animation, int phase) {
		
		
	}

	@Override
	public void resetAnimation() {
		
		
	}
}
