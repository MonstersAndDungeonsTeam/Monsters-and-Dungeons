package monstersanddungeons.entity.world;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.ai.EntitySpecialAttackBase;
import monstersanddungeons.entity.ai.ent.EntityLogSmash;
import monstersanddungeons.entity.ai.ent.EntitySunAttack;
import monstersanddungeons.entity.ai.ent.EntityThornAttack;
import monstersanddungeons.entity.ai.rook.EntityLegKick;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.packet.UpdateClientEntityAnimation;
import monstersanddungeons.util.entity.IMaDBoss;

public class EntityEnt extends MaDEntityMonsterBase implements IMaDBoss{

	boolean removeWander = false;
	int animationNumber, attackCD, dylan;
	EntitySpecialAttackBase current_attack = null;
	EntitySpecialAttackBase allAttacks[] = {new EntityThornAttack(1, 100, 0, 40), new EntityLogSmash(2, 100, 0, 20), new EntitySunAttack(1, 100, 0, 40)};
	EntityAIWander wander = new EntityAIWander(this, 0.5D);
	
	public EntityEnt(World worldIn) 
	{
		super(worldIn);
		ignoreFrustumCheck = true;
		this.setSize(1.5f, 5f);
		
		this.tasks.addTask(7, wander);
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {}));
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	public int getMonsterID() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void acivateAnimationby(int animation, int phase) {
		// TODO Auto-generated method stub
		this.current_attack = this.allAttacks[animation];

	}

	@Override
	public void resetAnimation() 
	{
		this.attackCD = 60;
		this.animationNumber = 0;
		this.current_attack.resetAnimation();
		this.current_attack = null;	

		if(!this.world.isRemote)
			MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, 0, this.getPhase(),true));
	}

	@Override
	public int getStartingX(boolean isEmpty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartingY(boolean isEmpty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth(boolean isEmpty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight(boolean isEmpty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCasting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCastingInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResourceLocation getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getBossName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaDEntityMonsterBase getBossInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activatePhase(int phase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPhase() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnimationNumber() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public EntitySpecialAttackBase getCurrentAttack() {
		// TODO Auto-generated method stub
		return this.current_attack;
	}

	public void selectAnimation()
	{
		Random rand = new Random();

		int special = rand.nextInt(this.allAttacks.length);

		if(this.allAttacks[special].shouldActivate(world, this))
		{
			this.current_attack = this.allAttacks[special];
			this.attackCD = 240;
			this.animationNumber = this.current_attack.getExpectedTicks();

			if(!this.world.isRemote)
				MaDPacketHandler.INSTANCE.sendToAll(new UpdateClientEntityAnimation(this, special, this.getPhase()));
		}	
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(!this.world.isRemote)
		{
			if(this.getAttackingEntity() != null)
			{
				if(!removeWander)
				{
					this.tasks.removeTask(wander);
					this.removeWander = true;
				}
				
				if(this.current_attack != null)
				{
					if(this.animationNumber == 0)
					{
						this.current_attack.applyDamage(animationNumber,this.world ,this);
						this.resetAnimation();
					}else
					{
						this.current_attack.activateEffect(animationNumber, this);
						this.animationNumber--;
					}
				}else
				{
					if(this.attackCD == 0)
					{
						this.selectAnimation();
					}else
						this.attackCD--;
					
					EntityPlayer player = world.getNearestAttackablePlayer(this, 20, 20);
					if(player != null)
						this.getNavigator().tryMoveToEntityLiving(player, 0.5f);
				}
			}else
			{
				if(this.removeWander)
				{
					this.tasks.addTask(7, wander);
					this.removeWander = false;
				}
			}
		}
		for(EntitySpecialAttackBase attack : allAttacks)
		{
			attack.decrementCurrentCD();
		}
	}
}
