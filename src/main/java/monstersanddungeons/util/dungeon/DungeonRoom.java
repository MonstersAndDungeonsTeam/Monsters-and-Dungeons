package monstersanddungeons.util.dungeon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import monstersanddungeons.util.StructureData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

public class DungeonRoom {

	StructureData roomStructure;
	String filename;
	EnumDirection prevBuiltDirection;


	List<DungeonExit> exits = new ArrayList<DungeonExit>();
	static List<StructureData> loadedRooms = new ArrayList<StructureData>();

	Random rand = new Random();

	public DungeonRoom(String FileName) {
		this.filename = FileName;
	}

	public boolean isLoaded()
	{
		boolean Yes = false;

		if(this.roomStructure != null)
		{
			return this.roomStructure.getIsLoaded();
		}

		return Yes;
	}

	public void setPrevBuiltDirection(EnumDirection prevBuiltDirection) {
		this.prevBuiltDirection = prevBuiltDirection;
	}


	public StructureData getRoomStructure() {
		return roomStructure;
	}

	public List<DungeonExit> getExits() {
		return exits;
	}

	public void setExits(List<DungeonExit> exits) {
		this.exits = exits;
	}


	public void buildRoom(BlockPos startPos, World world, EnumDirection direction, int offset) {

		if(this.isLoaded())
		{
			if(direction == EnumDirection.EAST)
			{
				for(int x = 0; x < this.roomStructure.blocks.length; x ++)
				{
					for(int y = 0; y < this.roomStructure.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.roomStructure.blocks[0][0].length; z ++)
						{
							BlockPos blockPos = new BlockPos(startPos.getX() + x, startPos.getY() + y - offset, startPos.getZ() + z);

							if(this.roomStructure.blocks[x][y][z].getBlock() == Blocks.STONE){
								if(!(world.getBlockState(blockPos).getBlock() instanceof BlockOre) && !(world.getBlockState(blockPos).getBlock() instanceof BlockRedstoneOre)){
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
								}//if the dungeon block to place is stone, then do not replace the ore that has already been generated.
							}else if(y == (this.roomStructure.blocks[0].length-1) && this.roomStructure.blocks[x][y][z].getBlock() == Blocks.AIR){
								//Don't force air at the top layer
							}
							else{

								if(this.roomStructure.blocks[x][y][z].getBlock().toString().contains("_stairs"))
								{
									world.setBlockState(blockPos, this.getCorrectStairOrientation(this.roomStructure.blocks[x][y][z], direction));
								}else
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);

								if(this.roomStructure.blocks[x][y][z].getBlock() instanceof BlockChest)
								{

									TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
									LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_ABANDONED_MINESHAFT);
									LootContext ctx = new LootContext.Builder((WorldServer) world).withLuck(10).build();
									table.fillInventory(chest, world.rand, ctx);
								}
							}

							
							if(rand.nextInt(100) == 0 && canSpawnWhite(x, y, z) && (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)))
							{
								if(rand.nextInt(20) == 0)
								{
									EntityPawnCommander pawn = new EntityPawnCommander(world);
									pawn.setPosition(startPos.getX() + x, startPos.getY() + y,startPos.getZ() + z);
									world.spawnEntity(pawn);
								}else
								{
									EntityWhitePawns pawn = new EntityWhitePawns(world);
									pawn.setPosition(startPos.getX() + x, startPos.getY() + y,startPos.getZ() + z);
									world.spawnEntity(pawn);
								}
							}
						}
					}
				}
			}else if(direction == EnumDirection.WEST)
			{
				for(int x = 0; x < this.roomStructure.blocks.length; x ++)
				{
					for(int y = 0; y < this.roomStructure.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.roomStructure.blocks[0][0].length; z ++)
						{
							BlockPos blockPos = new BlockPos(startPos.getX() - x, startPos.getY() + y - offset, startPos.getZ() - z);
							if(this.roomStructure.blocks[x][y][z].getBlock() == Blocks.STONE){
								if(!(world.getBlockState(blockPos).getBlock() instanceof BlockOre) && !(world.getBlockState(blockPos).getBlock() instanceof BlockRedstoneOre)){
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
								}//if the dungeon block to place is stone, then do not replace the ore that has already been generated.
							}else if(y == (this.roomStructure.blocks[0].length - 1) && this.roomStructure.blocks[x][y][z].getBlock() == Blocks.AIR){
								//Don't force air at the top layer
							}
							else{

								if(this.roomStructure.blocks[x][y][z].getBlock().toString().contains("_stairs"))
								{
									world.setBlockState(blockPos, this.getCorrectStairOrientation(this.roomStructure.blocks[x][y][z], direction));
								}else
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
							}


							if(this.roomStructure.blocks[x][y][z].getBlock() instanceof BlockChest)
							{
								TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
								LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_ABANDONED_MINESHAFT);
								LootContext ctx = new LootContext.Builder((WorldServer) world).withLuck(10).build();
								table.fillInventory(chest, world.rand, ctx);

							}


							if(rand.nextInt(100) == 0 && canSpawnWhite(x, y, z) && (world.getBlockState(blockPos).equals(Blocks.AIR)))
							{

								if(rand.nextInt(20) == 0)
								{
									EntityPawnCommander pawn = new EntityPawnCommander(world);
									pawn.setPosition(startPos.getX() - x, startPos.getY() + y,startPos.getZ() - z);
									world.spawnEntity(pawn);
								}else
								{
									EntityWhitePawns pawn = new EntityWhitePawns(world);
									pawn.setPosition(startPos.getX() - x, startPos.getY() + y,startPos.getZ() - z);
									world.spawnEntity(pawn);
								}
							}
						}	
					}
				}

			}else if(direction == EnumDirection.NORTH)
			{

				for(int x = 0; x < this.roomStructure.blocks.length; x ++)
				{
					for(int y = 0; y < this.roomStructure.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.roomStructure.blocks[0][0].length; z ++)
						{
							BlockPos blockPos = new BlockPos(startPos.getX() + z, startPos.getY() + y - offset, startPos.getZ() - x);
							if(this.roomStructure.blocks[x][y][z].getBlock() == Blocks.STONE){
								if(!(world.getBlockState(blockPos).getBlock() instanceof BlockOre) && !(world.getBlockState(blockPos).getBlock() instanceof BlockRedstoneOre)){
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
								}//if the dungeon block to place is stone, then do not replace the ore that has already been generated.
							}else if(y == (this.roomStructure.blocks[0].length - 1) && this.roomStructure.blocks[x][y][z].getBlock() == Blocks.AIR){
								//Don't force air at the top layer
							}
							else{
								if(this.roomStructure.blocks[x][y][z].getBlock().toString().contains("_stairs"))
								{
									world.setBlockState(blockPos, this.getCorrectStairOrientation(this.roomStructure.blocks[x][y][z], direction));
								}else
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
							}


							if(this.roomStructure.blocks[x][y][z].getBlock() instanceof BlockChest)
							{
								TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
								LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_ABANDONED_MINESHAFT);
								LootContext ctx = new LootContext.Builder((WorldServer) world).build();
								table.fillInventory(chest, world.rand, ctx);

							}

							if(rand.nextInt(100) == 0 && canSpawnWhite(x, y, z) && (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)))
							{
								if(rand.nextInt(20) == 0)
								{
									EntityPawnCommander pawn = new EntityPawnCommander(world);
									pawn.setPosition(startPos.getX() + z, startPos.getY() + y,startPos.getZ() - x);
									world.spawnEntity(pawn);
								}else
								{
									EntityWhitePawns pawn = new EntityWhitePawns(world);
									pawn.setPosition(startPos.getX() + z, startPos.getY() + y,startPos.getZ() - x);
									world.spawnEntity(pawn);
								}
							}
						}	
					}
				}
			}else if(direction == EnumDirection.SOUTH)
			{
				for(int x = 0; x < this.roomStructure.blocks.length; x ++)
				{
					for(int y = 0; y < this.roomStructure.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.roomStructure.blocks[0][0].length; z ++)
						{
							BlockPos blockPos = new BlockPos(startPos.getX() - z, startPos.getY() + y- offset, startPos.getZ() + x);
							if(this.roomStructure.blocks[x][y][z].getBlock() == Blocks.STONE){
								if(!(world.getBlockState(blockPos).getBlock() instanceof BlockOre) && !(world.getBlockState(blockPos).getBlock() instanceof BlockRedstoneOre)){
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
								}//if the dungeon block to place is stone, then do not replace the ore that has already been generated.
							}else if(y == (this.roomStructure.blocks[0].length - 1) && this.roomStructure.blocks[x][y][z].getBlock() == Blocks.AIR){
								//Don't force air at the top layer
							}
							else{

								if(this.roomStructure.blocks[x][y][z].getBlock().toString().contains("_stairs"))
								{

									world.setBlockState(blockPos, this.getCorrectStairOrientation(this.roomStructure.blocks[x][y][z], direction));
								}else
									world.setBlockState(blockPos, this.roomStructure.blocks[x][y][z]);
							}

							if(this.roomStructure.blocks[x][y][z].getBlock() instanceof BlockChest)
							{
								TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
								LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_ABANDONED_MINESHAFT);
								LootContext ctx = new LootContext.Builder((WorldServer) world).withLuck(10).build();
								table.fillInventory(chest, world.rand, ctx);
							}

							if(rand.nextInt(100) == 0 && canSpawnWhite(x, y, z) && (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)))
							{
								if(rand.nextInt(20) == 0)
								{
									EntityPawnCommander pawn = new EntityPawnCommander(world);
									pawn.setPosition(startPos.getX() - z, startPos.getY() + y,startPos.getZ() + x);
									world.spawnEntity(pawn);
								}else
								{
									EntityWhitePawns pawn = new EntityWhitePawns(world);
									pawn.setPosition(startPos.getX() - z, startPos.getY() + y,startPos.getZ() + x);
									world.spawnEntity(pawn);
								}
							}
						}
					}
				}
			}	
		}
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

	private boolean canSpawnWhite(int x, int y, int z)
	{
		if(y == 2)
		{
			return true;
		}
		return false;
	}

	public boolean isAlreadyLoaded()
	{
		for(StructureData datter : loadedRooms)
		{
			if(datter.getFileName().equals(this.filename))
			{
				this.roomStructure = datter;
				this.roomStructure.setLoaded(true);
				this.loadExits();
				return true;
			}
		}
		return false;
	}


	public boolean loadRoom() {

		if(!isAlreadyLoaded())
		{
			this.roomStructure = new StructureData();
			InputStream dungeonRoom = getClass().getClassLoader().getResourceAsStream(this.filename);

			int howManyExits;			
			if(dungeonRoom != null)
			{
				this.roomStructure.setLoaded(true);
				NBTTagCompound cmp = DungeonNBTTag.getTagCompoundInFile(dungeonRoom);

				this.roomStructure.xSize = cmp.getInteger("MaxXValue");
				this.roomStructure.ySize = cmp.getInteger("MaxYValue");
				this.roomStructure.zSize = cmp.getInteger("MaxZValue");

				this.roomStructure.blocks = new IBlockState[this.roomStructure.xSize][this.roomStructure.ySize][this.roomStructure.zSize];

				for(int x = 0; x < this.roomStructure.blocks.length; x ++)
				{
					for(int y = 0; y < this.roomStructure.blocks[0].length; y ++)
					{
						for(int z = 0; z < this.roomStructure.blocks[0][0].length; z ++)
						{
							int ID = cmp.getInteger(x + "," + y + "," + z);
							this.roomStructure.blocks[x][y][z] = Block.getStateById(ID);
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

					this.getExits().add(new DungeonExit(new BlockPos(X,Y,Z), dir));
					this.roomStructure.blocks[X][Y][Z] = Blocks.AIR.getDefaultState();
				}

				this.roomStructure.setFileName(this.filename);
				this.loadedRooms.add(roomStructure);
			}		
		}
		return false;
	}

	public void loadExits()
	{
		InputStream dungeonRoom = getClass().getClassLoader().getResourceAsStream(this.filename);
		NBTTagCompound cmp = DungeonNBTTag.getTagCompoundInFile(dungeonRoom);

		int howManyExits = cmp.getInteger("TotalExits");
		for(int i = 0; i < howManyExits; i ++)
		{
			int X =	cmp.getInteger("ExitNumberX" + i);
			int Y =	cmp.getInteger("ExitNumberY" + i);
			int Z =	cmp.getInteger("ExitNumberZ" + i);
			EnumDirection dir = EnumDirection.getDirectionFromString(cmp.getString("ExitDirection" + i));

			this.getExits().add(new DungeonExit(new BlockPos(X,Y,Z), dir));
			this.roomStructure.blocks[X][Y][Z] = Blocks.AIR.getDefaultState();
		}
	}

	/*public static EnumDirection getDirectionBasedOnState(IBlockState state){
		return EnumDirection.getDirectionFromID(state.getBlock().getMetaFromState(state));
	}*/



	public DungeonExit alignWithRoom(DungeonRoom nextRoom, DungeonRoom previousRoom, DungeonExit previousEntrance, BlockPos startingPosition) {

		BlockPos realPrevEntrance = null;
		EnumDirection PreviousBuiltDirection = previousRoom.getPrevBuiltDIRECTION();
		EnumDirection direction = null;
		DungeonExit exit = null;

		int j = 0;
		here:
			do{
				for(int i = 0; i < nextRoom.getExits().size(); i ++)// 0 = west, 1 = north, 2 = east, 3 = south
				{
					if(nextRoom.getExits().get(i).getDirectionWithRotation(j) == previousEntrance.getOppositeDirection())
					{
						direction = EnumDirection.getDirectionFromID(j);
						exit = nextRoom.getExits().get(i);

						for(int l = 0; l < nextRoom.getExits().size(); l ++)
						{
							nextRoom.getExits().get(l).setRotation(j);
						}

						break here;
					}
				}

				j++;
			}while(j < 4);


		if(PreviousBuiltDirection == EnumDirection.WEST) // gets the entrance actual position
		{
			realPrevEntrance = new BlockPos(startingPosition.getX() - previousEntrance.getPos().getX(), startingPosition.getY(), startingPosition.getZ() - previousEntrance.getPos().getZ());
		}else if(PreviousBuiltDirection == EnumDirection.EAST)
		{
			realPrevEntrance = new BlockPos(startingPosition.getX() + previousEntrance.getPos().getX(), startingPosition.getY(), startingPosition.getZ() + previousEntrance.getPos().getZ());
		}else if(PreviousBuiltDirection == EnumDirection.NORTH)
		{
			realPrevEntrance = new BlockPos(startingPosition.getX() + previousEntrance.getPos().getZ(), startingPosition.getY(), startingPosition.getZ() - previousEntrance.getPos().getX());
		}else if(PreviousBuiltDirection == EnumDirection.SOUTH)
		{
			realPrevEntrance = new BlockPos(startingPosition.getX() - previousEntrance.getPos().getZ(), startingPosition.getY(), startingPosition.getZ() + previousEntrance.getPos().getX());
		}

		if(previousEntrance.getDirection() == EnumDirection.EAST)
		{
			if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}

		}else if(previousEntrance.getDirection() == EnumDirection.WEST)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.xSize , realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}

		}else if(previousEntrance.getDirection() == EnumDirection.NORTH)
		{
			//RealPrevEntrance = new BlockPos(RealPrevEntrance.getX() - this.roomStructure.xSize, RealPrevEntrance.getY(), RealPrevEntrance.getZ() - this.roomStructure.zSize);
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{

				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}

		}else if(previousEntrance.getDirection() == EnumDirection.SOUTH)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}

		}
		if(exit != null)
		{
			if(previousEntrance.getDirection() == EnumDirection.WEST)
			{
				realPrevEntrance = realPrevEntrance.south(j % 2 == 0 ? exit.getPos().getZ() : exit.getPos().getX());

			}else if(previousEntrance.getDirection() == EnumDirection.NORTH)
			{
				realPrevEntrance =	realPrevEntrance.west(j % 2 == 0 ? exit.getPos().getX() : exit.getPos().getZ());

			}else if(previousEntrance.getDirection() == EnumDirection.EAST)
			{
				realPrevEntrance = realPrevEntrance.north(j % 2 == 0 ? exit.getPos().getZ() : exit.getPos().getX());

			}else if(previousEntrance.getDirection() == EnumDirection.SOUTH)
			{
				realPrevEntrance = realPrevEntrance.east(j % 2 == 0 ? exit.getPos().getX() : exit.getPos().getZ());

			}
		}

		return new DungeonExit(realPrevEntrance, direction, exit);
	}

	//only method that uses this is close, but is just for testing purposes
	public DungeonExit TESTalignWithRoom(DungeonRoom Nextroom, DungeonRoom PreviousRoom, DungeonExit PreviousEntrance, BlockPos StartingPosition) {

		BlockPos realPrevEntrance = StartingPosition;
		EnumDirection PreviousBuiltDirection = PreviousRoom.getPrevBuiltDIRECTION();
		EnumDirection direction = null;
		DungeonExit exit = null;

		int j =0;
		here:
			while(j < 4){
				for(int i = 0; i < Nextroom.getExits().size(); i ++)// 0 = west, 1 = north, 2 = east, 3 = south
				{
					if(Nextroom.getExits().get(i).getDirectionWithRotation(j) == PreviousEntrance.getOppositeDirection())
					{
						direction = EnumDirection.getDirectionFromID(j);
						exit = Nextroom.getExits().get(i);

						for(int l = 0; l < Nextroom.getExits().size(); l ++)
						{
							Nextroom.getExits().get(l).setRotation(j);
						}

						break here;
					}
				}

				j++;
			}
		if(PreviousEntrance.getDirection() == EnumDirection.EAST)
		{
			if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}

		}else if(PreviousEntrance.getDirection() == EnumDirection.WEST)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.xSize , realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}

		}else if(PreviousEntrance.getDirection() == EnumDirection.NORTH)
		{
			//RealPrevEntrance = new BlockPos(RealPrevEntrance.getX() - this.roomStructure.xSize, RealPrevEntrance.getY(), RealPrevEntrance.getZ() - this.roomStructure.zSize);
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.zSize);
			}else if(direction == EnumDirection.SOUTH)
			{

				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() - this.roomStructure.xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}

		}else if(PreviousEntrance.getDirection() == EnumDirection.SOUTH)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - this.roomStructure.zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + this.roomStructure.zSize);
			}

		}

		if(exit != null)
		{
			if(PreviousEntrance.getDirection() == EnumDirection.WEST)
			{
				realPrevEntrance = realPrevEntrance.south(j % 2 == 0 ? exit.getPos().getZ() : exit.getPos().getX());

			}else if(PreviousEntrance.getDirection() == EnumDirection.NORTH)
			{
				realPrevEntrance =	realPrevEntrance.west(j % 2 == 0 ? exit.getPos().getX() : exit.getPos().getZ());

			}else if(PreviousEntrance.getDirection() == EnumDirection.EAST)
			{
				realPrevEntrance = realPrevEntrance.north(j % 2 == 0 ? exit.getPos().getZ() : exit.getPos().getX());

			}else if(PreviousEntrance.getDirection() == EnumDirection.SOUTH)
			{
				realPrevEntrance = realPrevEntrance.east(j % 2 == 0 ? exit.getPos().getX() : exit.getPos().getZ());

			}
		}

		return new DungeonExit(realPrevEntrance, direction, exit);
	}


	public void unloadRoom() {
		this.roomStructure = null;
		this.exits.clear();
	}


	public DungeonExit setCorrectPath() {
		Random Rand = new Random();
		int RandomNumber;


		if(this.getExits().size() > 0)
		{
			RandomNumber = Rand.nextInt(this.getExits().size());
			this.getExits().get(RandomNumber).setCorrectPath(true);
			return this.getExits().get(RandomNumber);
		}

		return null;
	}

	public DungeonExit setCorrectPath(EnumDirection enumDirection) {

		if(enumDirection != null)
		{
			for(DungeonExit tree : exits){
				if(tree.getDirection() == enumDirection){
					tree.setCorrectPath(true);
					return tree;
				}
			}
		}

		return setCorrectPath();
	}

	public void removeOppositeExit(DungeonExit oppositeExit)
	{
		for(int i = 0 ; i < this.exits.size(); i ++)
		{
			if(this.exits.get(i).getDirection().equals(oppositeExit.getOppositeDirection()))
			{
				this.exits.remove(i);
				return;
			}
		}
	}

	public EnumDirection getPrevBuiltDIRECTION() {
		return prevBuiltDirection;
	}

	public static void clearDungeonRoom()
	{
		loadedRooms.clear();
	}
}
