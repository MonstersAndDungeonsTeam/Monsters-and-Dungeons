package monstersanddungeons.entity.ai.rook;

import net.minecraft.world.World;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;

public class EntitySwordFall extends EntitySpecialAttackBase<ModelAutomatonsRookBoss, EntityAutomatonsRookBoss>{

	float arm[][] = {};
	
	public EntitySwordFall(int max, int cd, int phase, int expectedTicks) {
		super(max, cd, phase, expectedTicks);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tickAnimation(ModelAutomatonsRookBoss bossModel, float scale, float speed) {
		
		
	}

	@Override
	public void completeAnimation(int animationNumber, ModelAutomatonsRookBoss bossModel) {
		
		
	}

	@Override
	public void activateEffect(int animationNumber, EntityAutomatonsRookBoss bossEntity) {
		
		
	}

	@Override
	public void applyDamage(int animationNumber, World world, EntityAutomatonsRookBoss bossEntity) {
		
		
	}

}
