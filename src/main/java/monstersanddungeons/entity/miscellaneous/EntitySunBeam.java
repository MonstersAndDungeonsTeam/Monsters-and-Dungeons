package monstersanddungeons.entity.miscellaneous;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySunBeam extends Entity{

	int ageInTicks;

	public EntitySunBeam(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.getPosition().getX() - 3, this.getPosition().getY() - 3, this.getPosition().getZ() - 3, this.getPosition().getX() + 3, this.getPosition().getY() + 3, this.getPosition().getZ() + 3));
		for(Entity entity : entities)
		{
			if(entity instanceof EntityPlayer)
			{
			//	entity.attackEntityFrom(DamageSource.onFire, 4f);
			}
		}
		this.ageInTicks++;
		if(this.ageInTicks > 160)
		{
		//	this.setDead();
		}
	}
}
