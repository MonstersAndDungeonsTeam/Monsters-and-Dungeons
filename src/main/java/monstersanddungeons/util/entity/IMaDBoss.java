package monstersanddungeons.util.entity;

import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import net.minecraft.util.ResourceLocation;

public interface IMaDBoss
{
 
	public int getStartingX(boolean isEmpty);
	public int getStartingY(boolean isEmpty);
	
	public int getWidth(boolean isEmpty);
	public int getHeight(boolean isEmpty);
	
	public boolean isCasting();
    public int getCastingInt();
	
    public boolean isInvulnerable();
    
	public ResourceLocation getIcon();
	public ResourceLocation getBossName();
    
	public MaDEntityMonsterBase getBossInfo();
	
    public void activatePhase(int phase);
    public int getPhase();
    public int getAnimationNumber();
        
    public EntitySpecialAttackBase getCurrentAttack();
  
  
}
