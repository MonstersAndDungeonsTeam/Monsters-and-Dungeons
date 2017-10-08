package monstersanddungeons.entity.ai;

import monstersanddungeons.util.entity.IMaDBoss;
import net.minecraft.world.World;

public abstract class EntitySpecialAttackBase<T, V extends IMaDBoss> {

	boolean isPaused;
	int maximumCycle, maxAttackCD, phase, animationNumber, expectedTicks, currentCD;

	public EntitySpecialAttackBase(int max, int cd, int phase, int expectedTicks) {
		// TODO Auto-generated constructor stub
		this.maximumCycle = max;
		this.maxAttackCD = cd;
		this.phase = phase;
		this.expectedTicks = expectedTicks;
	}

	public int getMaximumCycle() {
		return maximumCycle;
	}
	public int getMaxAttackCD() {
		return maxAttackCD;
	}
	public int getPhase() {
		return phase;
	}
	public int getAnimationNumber()
	{
		return animationNumber;
	}
	public boolean getPaused()
	{
		return this.isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public int getExpectedTicks() {
		return expectedTicks;
	}

	public boolean decrementCurrentCD()
	{

		if(this.currentCD > 0)
		{
			this.currentCD--;
			return true;
		}

		return false;
	}


	public boolean incrementAnimationNumber()
	{
		if(this.animationNumber < this.getMaximumCycle())
		{
			this.animationNumber++;
			return true;
		}

		return false;
	}
	public void setAnimationNumber(int value)
	{
		this.animationNumber = value;
	}

	/** Called before the action is chosen to see if the entity should perform the function
	 * @return - true if should activate, false otherwise
	 */
	public boolean shouldActivate(World world, V bossEntity)
	{
		if(this.currentCD == 0)
		{
			if(this.getPhase() == -1)
			{

				return true;
			}else
				if(bossEntity.getPhase() == this.getPhase())
				{
					return true;
				}
		}
		return false;
	}

	/** Client side only method where the animation will be ticked
	 * @param animationNumber
	 * @param bossModel
	 */
	public abstract void tickAnimation(T bossModel, float scale, float speed);

	/** Client side only method called after the entity is finished
	 * @param animationNumber
	 * @param bossModel
	 */
	public abstract void completeAnimation(int animationNumber, T bossModel);


	/** Server side only method called to update entity actions such as position, variables, and etc
	 * @param animationNumber
	 * @param bossEntity
	 */
	public abstract void activateEffect(int animationNumber, V bossEntity);

	/** Server side only method called every tick to see when the entity should be applying damage
	 * @param animationNumber
	 * @param world
	 * @param bossEntity
	 */
	public abstract void applyDamage(int animationNumber, World world, V bossEntity);

	/** Called on both sides to reset the animation and be ready for the next call
	 * 
	 */
	public void resetAnimation()
	{
		this.setAnimationNumber(0);
		this.setPaused(false);
		this.currentCD = getMaxAttackCD();
	}

}
