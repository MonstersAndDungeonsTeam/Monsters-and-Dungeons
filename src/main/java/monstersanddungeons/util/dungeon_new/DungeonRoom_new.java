package monstersanddungeons.util.dungeon_new;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import monstersanddungeons.util.StructureData;
import monstersanddungeons.util.dungeon.DungeonNBTTag;
import monstersanddungeons.util.dungeon.DungeonRoom;
import monstersanddungeons.util.dungeon.EnumDirection;

public class DungeonRoom_new 
{
	String fileName;
	StructureData room_data;

	int rotation_times;
	EnumDirection buildingdirection;

	boolean isLoaded = false;
	BlockPos fictitious_startingLocation;
	List<DungeonExit_new> exits = new ArrayList<DungeonExit_new>();

	static List<StructureData> loadedRooms = new ArrayList<StructureData>();

	public DungeonRoom_new(String room) 
	{
		this.fileName = room;
		this.loadRoom();
	}

	public DungeonExit_new getExitByDirection(EnumDirection dir)
	{
		for(DungeonExit_new exit : exits)
		{
			if(exit.getFacing_direction().equals(dir))
			{
				return exit;
			}
		}
		return null;
	}

	public IBlockState getCorrectStairOrientation(IBlockState stairblock, EnumDirection currentDirection)
	{
		IBlockState newState = null;
		int meta = stairblock.getBlock().getMetaFromState(stairblock);
		switch(currentDirection)
		{
		case EAST:
			if(meta == 1)
			{
				meta = 0;
			}else if(meta == 0)
			{
				meta = 1;
			}
			else
				if(meta == 5)
				{
					meta = 4;
				}else if(meta == 4)
				{
					meta = 5;
				}
				else
					if(meta == 2)
					{
						meta = 3;
					}else if(meta == 3)
					{
						meta = 2;
					}else
						if(meta == 6)
						{
							meta = 7;
						}else if(meta == 7)
						{
							meta = 6;
						}
			break;
		case WEST:
			break;
		case NORTH:
			if(meta == 1)
			{
				meta = 3;
			}else if(meta == 3)
			{
				meta = 0;
			}else if(meta == 0)
			{
				meta = 2;
			}else if(meta == 2)
			{
				meta = 1;
			}

			if(meta == 5)
			{
				meta = 7;
			}else if(meta == 7)
			{
				meta = 4;
			}else if(meta == 4)
			{
				meta = 6;
			}else if(meta == 6)
			{
				meta = 5;
			}
			break;
		case SOUTH:
			if(meta == 1)
			{
				meta = 2;
			}else if(meta == 3)
			{
				meta = 1;
			}else if(meta == 0)
			{
				meta = 3;
			}else if(meta == 2)
			{
				meta = 0;
			}
			if(meta == 5)
			{
				meta = 6;
			}else if(meta == 7)
			{
				meta = 5;
			}else if(meta == 4)
			{
				meta = 7;
			}else if(meta == 6)
			{
				meta = 4;
			}	
			break;
		};


		newState = stairblock.getBlock().getStateFromMeta(meta);
		return newState;
	}

	public void setRotation_times(int rotation_times) 
	{
		this.rotation_times = rotation_times;

		for(DungeonExit_new exit : exits)
		{
			exit.setFacing_direction(exit.getDirectionWithRotation(rotation_times));
		}
	}

	public int getRotation_times() {
		return rotation_times;
	}

	public BlockPos getFictitious_startingLocation() {
		return fictitious_startingLocation;
	}

	public void setFictitious_startingLocation(BlockPos fictitious_startingLocation) 
	{
		this.fictitious_startingLocation = fictitious_startingLocation;

		for(DungeonExit_new exit : exits)
		{
			exit.calculateRealPosition(fictitious_startingLocation, getBuildingdirection());
		}
	}

	public int getX()
	{
		return this.room_data.blocks.length;
	}

	public int getY()
	{
		return this.room_data.blocks[0].length;
	}

	public int getZ()
	{
		return this.room_data.blocks[0][0].length;
	}

	public void setBuildingdirection(EnumDirection buildingdirection) {
		this.buildingdirection = buildingdirection;
	}
	public EnumDirection getBuildingdirection() {
		return buildingdirection;
	}

	public List<DungeonExit_new> getExits() {
		return exits;
	}

	public void setExits(List<DungeonExit_new> exits) {
		this.exits = exits;
	}

	public boolean hasDirection(EnumDirection wanted)
	{
		for(DungeonExit_new exit : exits)
		{
			if(exit.getFacing_direction().equals(wanted))
			{
				return true;
			}
		}
		return false;
	}

	/** finds the rotation needed to match the required direction
	 * @param wanted
	 * @return - the rotation number otherwise -1 if something broke
	 */
	public int hasDirectionWithRotation(EnumDirection wanted)
	{
		int rotation;
		for(rotation = 0; rotation < 4; rotation++)
		{
			for(DungeonExit_new exit : exits)
			{
				if(exit.getDirectionWithRotation(rotation).equals(wanted))
				{
					return rotation;
				}
			}
		}
		return -1;
	}

	public int hasDirectionsWithRotation(EnumDirection wanted1, EnumDirection wanted2)
	{
		int rotation;
		for(rotation = 0; rotation < 4; rotation++)
		{
			for(DungeonExit_new exit : exits)
			{
				if(exit.getDirectionWithRotation(rotation).equals(wanted1))
				{
					for(DungeonExit_new exit2 : exits)
					{
						if(exit2.getDirectionWithRotation(rotation).equals(wanted2))
						{
							return rotation;
						}
					}
				}
			}
		}
		return -1;
	}
	//return rotation;
	public boolean loadRoom() 
	{
		if(!isAlreadyLoaded())
		{
			this.room_data = new StructureData();
			InputStream dungeonRoom = getClass().getClassLoader().getResourceAsStream(this.fileName);

			int howManyExits;			
			if(dungeonRoom != null)
			{
				this.room_data.setLoaded(true);
				NBTTagCompound cmp = DungeonNBTTag.getTagCompoundInFile(dungeonRoom);

				this.room_data.xSize = cmp.getInteger("MaxXValue");
				this.room_data.ySize = cmp.getInteger("MaxYValue");
				this.room_data.zSize = cmp.getInteger("MaxZValue");

				this.room_data.blocks = new IBlockState[this.room_data.xSize][this.room_data.ySize][this.room_data.zSize];

				for(int x = 0; x < this.room_data.blocks.length; x ++)
				{
					for(int y = 0; y < this.room_data.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.room_data.blocks[0][0].length; z ++)
						{
							int ID = cmp.getInteger(x + "," + y + "," + z);

							try
							{
								this.room_data.blocks[x][y][z] = Block.getStateById(ID);
							}catch(ArrayIndexOutOfBoundsException exception)
							{
								this.room_data.blocks[x][y][z] = Blocks.AIR.getDefaultState();
							}
						}
					}
				}

				howManyExits = cmp.getInteger("TotalExits");
				for(int i = 0; i < howManyExits; i ++)
				{
					int X =	cmp.getInteger("ExitNumberX" + i);
					int Y =	cmp.getInteger("ExitNumberY" + i);
					int Z =	cmp.getInteger("ExitNumberZ" + i);


					EnumDirection dir = EnumDirection.getDirectionFromString(cmp.getString("ExitDirection" + i));
					this.getExits().add(new DungeonExit_new(dir, new BlockPos(X,Y,Z)));
					this.room_data.blocks[X][Y][Z] = Blocks.AIR.getDefaultState();
				}

				this.room_data.setFileName(this.fileName);
				this.loadedRooms.add(room_data);
			}		
		}else
			this.loadExits();

		return false;
	}

	public void loadExits()
	{	
		if(!this.isLoaded)
		{
			InputStream dungeonRoom = getClass().getClassLoader().getResourceAsStream(this.fileName);
			NBTTagCompound cmp = DungeonNBTTag.getTagCompoundInFile(dungeonRoom);

			int howManyExits = cmp.getInteger("TotalExits");
			for(int i = 0; i < howManyExits; i ++)
			{
				int X =	cmp.getInteger("ExitNumberX" + i);
				int Y =	cmp.getInteger("ExitNumberY" + i);
				int Z =	cmp.getInteger("ExitNumberZ" + i);
				EnumDirection dir = EnumDirection.getDirectionFromString(cmp.getString("ExitDirection" + i));

				this.getExits().add(new DungeonExit_new(dir, new BlockPos(X,Y,Z)));
				this.room_data.blocks[X][Y][Z] = Blocks.AIR.getDefaultState();
			}
		}
	}

	public boolean isAlreadyLoaded()
	{
		for(StructureData datter : loadedRooms)
		{
			if(datter.getFileName().equals(this.fileName))
			{
				this.room_data = datter;
				this.loadExits();
				this.isLoaded = true;
				return true;
			}
		}

		return false;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
