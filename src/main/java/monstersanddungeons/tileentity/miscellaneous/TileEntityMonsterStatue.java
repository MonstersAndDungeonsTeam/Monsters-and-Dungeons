package monstersanddungeons.tileentity.miscellaneous;

import java.util.List;

import monstersanddungeons.entity.MaDEntityMonsterBase;
import monstersanddungeons.entity.automatons.EntityAutomatonsRook;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerFisherman;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerShaman;
import monstersanddungeons.entity.miscellaneous.EntityFlyingSword;
import monstersanddungeons.entity.world.EntityEnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityMonsterStatue extends TileEntity implements ITickable { 


	NBTTagCompound storedDATA;
	int checkPlayerCD;
	boolean spawnOnce = true;
	
	public TileEntityMonsterStatue()
	{
		
	}

	public TileEntityMonsterStatue(MaDEntityMonsterBase entity) {
		// TODO Auto-generated constructor stub
		storedDATA = entity.getNBTData();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		compound.setTag("storedDATA", storedDATA);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		storedDATA = (NBTTagCompound) compound.getTag("storedDATA");
		super.readFromNBT(compound);
	}

	public boolean isPlayerNearby()
	{
		List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPos().getX() - 25, this.getPos().getY() - 25, this.getPos().getZ() - 25, this.getPos().getX() + 25, this.getPos().getY() + 25, this.getPos().getZ() + 25));

		if (entities.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}



	@Override
	public void update() {

		if(this.checkPlayerCD == 0)
		{
			this.checkPlayerCD = 20;
			if(isPlayerNearby())
			{
				if(storedDATA != null)
				{
					int EntityID = storedDATA.getInteger("EntityID");
					Entity entityToSpawn = null;

					switch(EntityID)
					{
					case 0:
						entityToSpawn = new EntityAutomatonsRook(this.worldObj);
						break;
					case 1:
						entityToSpawn = new EntityWhitePawns(this.worldObj);
						break;
					case 2:
						entityToSpawn = new EntityAutomatonsRookBoss(this.worldObj);
						break;
					case 3:
						entityToSpawn = new EntityFlyingSword(this.worldObj);
						break;
					case 4:
						entityToSpawn = new EntityPawnCommander(this.worldObj);
						break;
					case 5:
						entityToSpawn = new EntityMarshDweller(this.worldObj);
						break;
					case 6:
						entityToSpawn = new EntityMarshDwellerFisherman(this.worldObj);
						break;
					case 7:
						entityToSpawn = new EntityMarshDwellerShaman(this.worldObj);
						break;
					case 8:
						entityToSpawn = new EntityEnt(this.worldObj);
						break;
					}
					entityToSpawn.setPosition(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
					if(spawnOnce)
					{
						if(!this.worldObj.isRemote)
							this.worldObj.spawnEntityInWorld(entityToSpawn);

						this.spawnOnce = false;
					}

					this.worldObj.setBlockToAir(this.getPos());
					this.worldObj.removeTileEntity(this.getPos());
				}
			}
		}else
			this.checkPlayerCD--;
	}	
}
