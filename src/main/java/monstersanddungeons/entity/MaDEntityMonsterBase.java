package monstersanddungeons.entity;

import java.util.List;

import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.tileentity.miscellaneous.TileEntityMonsterStatue;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class MaDEntityMonsterBase extends EntityMob
{

	int playerCheckCD, animationCycle;

	public MaDEntityMonsterBase(World worldIn) {
		super(worldIn);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public abstract int getMonsterID();

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(this.turnsIntoBlock())
		{
			if(playerCheckCD == 0)
			{
				playerCheckCD = 20;
	
				if(!isPlayerNearby())
				{
					TileEntityMonsterStatue statue = new TileEntityMonsterStatue(this);
	
					worldObj.setBlockState(this.getPosition().up(), MaDBlocksHandler.BlockEntityStatue.getDefaultState());
					worldObj.setTileEntity(this.getPosition().up(), statue);
	
					this.setDead();
				}
			}else
			{
				playerCheckCD --;
			}
		}

	}

	public boolean isPlayerNearby()
	{
		List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPosition().getX() - 50, this.getPosition().getY() - 50, this.getPosition().getZ() - 50, this.getPosition().getX() + 50, this.getPosition().getY() + 50, this.getPosition().getZ() + 50));

		if (entities.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public NBTTagCompound getNBTData() {

		NBTTagCompound compound = new NBTTagCompound();

		compound.setFloat("Health", this.getHealth());
		compound.setInteger("EntityID", this.getMonsterID());

		return compound;
	}

	public abstract void acivateAnimationby(int animation, int phase);
	public abstract void resetAnimation();
	
	public boolean turnsIntoBlock()
	{
		return true;
	}

}
