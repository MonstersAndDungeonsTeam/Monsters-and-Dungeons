package monstersanddungeons.entity.miscellaneous;

import java.util.Random;

import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFlyingSword extends MaDEntityMonsterBase{

	int attackCD = 0;
	boolean isAttacking = false, updateClient = false, isSpinning = false;
	EntityAutomatonsRookBoss isSevenSword;

	public EntityFlyingSword(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
		this.setHealth(50);
	}

	public EntityFlyingSword(World worldIn, EntityAutomatonsRookBoss isSevenSword) {
		super(worldIn);
		this.isSevenSword = isSevenSword;
	}

	public int getAttackCD() {
		return attackCD;
	}
	public EntityAutomatonsRookBoss getIsSevenSword() {
		return isSevenSword;
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) 
	{
		if(damageSrc.getTrueSource() != null)
		{
			if(damageSrc.getTrueSource() instanceof EntityPlayer)
			{
				super.damageEntity(damageSrc, damageAmount);
			}
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) 
	{

		this.attackEntityAsMob(entityIn);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(isSevenSword != null)
		{
			getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.posY = this.isSevenSword.getPosition().getY();
		}else
		{	
			if(!isAttacking)
			{
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
				this.isAttacking = true;
			}

			if(this.attackCD == 0)
			{
				Random rand = new Random();
				EntityPlayer player = this.world.getNearestAttackablePlayer(this.getPosition(), 60, 30);
				if(player != null)
					this.getNavigator().tryMoveToEntityLiving(player, 1f);

				this.attackCD = 100 + rand.nextInt(100);


			}else
			{
				this.attackCD--;	
			}


		}
	}

	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void acivateAnimationby(int animation, int phase) {
		// TODO Auto-generated method stub

		this.isSevenSword = (EntityAutomatonsRookBoss) world.getEntityByID(animation);
	}

	@Override
	public void resetAnimation() {
		// TODO Auto-generated method stub

	}
}
