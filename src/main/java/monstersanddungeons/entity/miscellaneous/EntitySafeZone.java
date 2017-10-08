package monstersanddungeons.entity.miscellaneous;

import java.util.List;

import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySafeZone extends Entity {

	private final DataParameter<Boolean> playerNearby = EntityDataManager.<Boolean>createKey(EntitySafeZone.class, DataSerializers.BOOLEAN);
	EntityAutomatonsRookBoss owner = null;

	//Server and client
	boolean isPlayerNearby;

	//Server only Functions
	int cooldown;
	List<EntityPlayer> players;

	public EntitySafeZone(World worldIn) {
		super(worldIn);
		this.setSize(3f, 1f);
		this.getDataManager().register(playerNearby, Boolean.valueOf(isPlayerNearby));
	}

	public EntitySafeZone(World worldIn, EntityAutomatonsRookBoss owner) {
		super(worldIn);
		this.setSize(5f, 1f);
		this.getDataManager().register(playerNearby, Boolean.valueOf(isPlayerNearby));
		this.owner = owner;
	}


	public boolean isPlayerInThisZone(EntityPlayer Player)
	{

		if(Player != null)
		{
			for(EntityPlayer player : players)
			{
				if(Player.equals(player))
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean isPlayerNearby()
	{
		return this.getDataManager().get(this.playerNearby).booleanValue();
	}

	@Override
	public void onUpdate() 
	{
		if(this.owner != null )
		{
			if(this.owner.isDead)
			{
				this.setDead();
			}

			if(this.cooldown == 0)
			{

				players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.posX - 2, this.posY - 2, this.posZ - 2,  this.posX + 2,  this.posY + 2, this.posZ + 2));

				if(!players.isEmpty())
				{
					if(players.size() > 1)
					{
						this.isPlayerNearby = false;
						this.getDataManager().set(this.playerNearby, Boolean.valueOf(false));
					}else
					{
						this.isPlayerNearby = true;
						this.getDataManager().set(this.playerNearby, Boolean.valueOf(true));
					}
				}else
				{
					this.isPlayerNearby = false;
					this.getDataManager().set(this.playerNearby, Boolean.valueOf(false));
				}

				this.cooldown = 10;
			}else
				this.cooldown--;
		}else
		{
			if(!this.world.isRemote)
				this.setDead();
		}
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

}
