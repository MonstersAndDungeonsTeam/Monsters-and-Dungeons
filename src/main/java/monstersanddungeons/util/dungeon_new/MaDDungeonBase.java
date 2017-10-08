package monstersanddungeons.util.dungeon_new;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import monstersanddungeons.util.dungeon.EnumDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MaDDungeonBase 
{
	boolean placeBoosRoom = false;
	int maxDungeonSize = 500, maxYSize = 100;

	protected IBlockState arena[][][] = new IBlockState[maxDungeonSize][maxYSize][maxDungeonSize];
	List<DungeonExit_new> all_exits = new ArrayList<DungeonExit_new>();



	public DungeonExit_new tryPlaceRoom(int x, int y, int z, EnumDirection dir)
	{
		DungeonRoom_new newRoom = this.getRoom();
		BlockPos fictitious_position = new BlockPos(x,y,z);
		boolean successful = tryConstructRoom(fictitious_position, newRoom, EnumDirection.WEST);

		return newRoom.exits.get(0);
	}

	public void createRandomNetwork(int size)
	{
		this.createRandomNetwork(size, maxDungeonSize/2, 20, maxDungeonSize/2);
	}

	public void createRandomNetwork(int size, int x, int y, int z)
	{
		BlockPos fictitious_position = new BlockPos(x,y,z);

		DungeonRoom_new newRoom = this.getRoom();
		boolean successful = tryConstructRoom(fictitious_position, newRoom, EnumDirection.WEST);

		if(successful)
		{
			this.all_exits.addAll(newRoom.getExits());
			for(int i = 0; i < all_exits.size(); i ++)
			{
				DungeonExit_new exit = all_exits.get(i);
				if(!exit.getLookedAt())
				{
					newRoom = this.getRoom();
					successful = tryContructRoom(exit, newRoom);

					if(successful)
					{
						size--;
						this.all_exits.addAll(newRoom.getExits());
					}else
					{
						//this.tryToCloseExit(exit, false);
					}
					exit.setLookedat(true);
				}
				if(size == 0)
				{
					return;
				}
			}

		}
	}

	public List<DungeonExit_new> getAll_exits() {
		return all_exits;
	}


	public boolean tryContructRoom(DungeonExit_new ExittoMatch, DungeonRoom_new room)
	{
		return this.tryContructRoom(ExittoMatch, room, -1);
	}

	/** This function will have to align before calling its brother
	 * @param Exit - exit to match
	 * @param room - room to add to the matching room 
	 * @return - null if unsuccessful, otherwise the fictitious location of the room
	 */
	public boolean tryContructRoom(DungeonExit_new ExittoMatch, DungeonRoom_new room, int rotation_override)
	{
		EnumDirection required_direction = ExittoMatch.getOppositeDirection();
		EnumDirection building_direction = null;
		BlockPos realPrevEntrance = ExittoMatch.getReal_position();

		DungeonExit_new nextRoomMatchingExit = null;

		int rotation = rotation_override == -1 ? room.hasDirectionWithRotation(required_direction) : rotation_override;
		if(rotation != -1)
		{
			building_direction = EnumDirection.getDirectionFromID(rotation);
			room.setRotation_times(rotation);
			nextRoomMatchingExit = room.getExitByDirection(required_direction);
		}

		if(ExittoMatch.getFacing_direction() == EnumDirection.EAST)
		{
			if(building_direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + room.getZ());
			}else if(building_direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + room.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(building_direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + room.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + room.getZ());
			}

		}else if(ExittoMatch.getFacing_direction() == EnumDirection.WEST)
		{
			if(building_direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - room.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - room.getZ());
			}else if(building_direction== EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - room.getZ(), realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(building_direction== EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - room.getZ());
			}

		}else if(ExittoMatch.getFacing_direction() == EnumDirection.NORTH)
		{
			//RealPrevEntrance = new BlockPos(RealPrevEntrance.getX() - room.getX(), RealPrevEntrance.getY(), RealPrevEntrance.getZ() - room.getZ());
			if(building_direction== EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - room.getZ());
			}else if(building_direction== EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + room.getZ(), realPrevEntrance.getY(), realPrevEntrance.getZ() - room.getX());
			}else if(building_direction== EnumDirection.WEST)
			{

				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + room.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ());
			}

		}else if(ExittoMatch.getFacing_direction() == EnumDirection.SOUTH)
		{
			if(building_direction== EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - room.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(building_direction== EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - room.getZ(), realPrevEntrance.getY(), realPrevEntrance.getZ() + room.getX());
			}else if(building_direction== EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + room.getZ());
			}
		}


		room.setBuildingdirection(building_direction);
		room.setFictitious_startingLocation(realPrevEntrance);
		//Shifter 
		if(nextRoomMatchingExit != null)
		{
			if(ExittoMatch.getFacing_direction() == EnumDirection.WEST)//goes south
			{
				//realPrevEntrance = realPrevEntrance.south(rotation % 2 == 0 ? nextRoomMatchingExit.getFictitious_position().getZ() : nextRoomMatchingExit.getFictitious_position().getX()).west();
				if(ExittoMatch.getReal_position().getZ() != nextRoomMatchingExit.getReal_position().getZ())
				{
					int difference = ExittoMatch.getReal_position().getZ()  - nextRoomMatchingExit.getReal_position().getZ();
					realPrevEntrance = realPrevEntrance.south(difference);
				}

			}else if(ExittoMatch.getFacing_direction() == EnumDirection.NORTH)// goes west
			{
				//realPrevEntrance =	realPrevEntrance.west(rotation % 2 == 0 ? nextRoomMatchingExit.getFictitious_position().getX() : nextRoomMatchingExit.getFictitious_position().getZ()).north();

				if(ExittoMatch.getReal_position().getX() != nextRoomMatchingExit.getReal_position().getX())
				{
					int difference = nextRoomMatchingExit.getReal_position().getX() -ExittoMatch.getReal_position().getX();
					realPrevEntrance = realPrevEntrance.west(difference);
				}


			}else if(ExittoMatch.getFacing_direction() == EnumDirection.EAST)//goes north
			{
				//realPrevEntrance = realPrevEntrance.north(rotation % 2 == 0 ? nextRoomMatchingExit.getFictitious_position().getZ() : nextRoomMatchingExit.getFictitious_position().getX()).east();

				if(ExittoMatch.getReal_position().getZ() != nextRoomMatchingExit.getReal_position().getZ())
				{
					int difference = nextRoomMatchingExit.getReal_position().getZ() -ExittoMatch.getReal_position().getZ();
					realPrevEntrance = realPrevEntrance.north(difference);
				}

			}else if(ExittoMatch.getFacing_direction() == EnumDirection.SOUTH)//goes east
			{
				//realPrevEntrance = realPrevEntrance.east(rotation % 2 == 0 ? nextRoomMatchingExit.getFictitious_position().getX() : nextRoomMatchingExit.getFictitious_position().getZ()).south();
				if(ExittoMatch.getReal_position().getX() != nextRoomMatchingExit.getReal_position().getX())
				{
					int difference =  ExittoMatch.getReal_position().getX() - nextRoomMatchingExit.getReal_position().getX();
					realPrevEntrance = realPrevEntrance.east(difference);
				}
			}

			realPrevEntrance = realPrevEntrance.down(nextRoomMatchingExit.getFictitious_position().getY());
			nextRoomMatchingExit.setLookedat(true);
		}


		return this.tryConstructRoom(realPrevEntrance, room, building_direction);

	}


	/** Will try to add the room into the array...This function Assumes the startingPosition is already aligned and will update the room direction
	 * and position on successful constructs
	 * @param startingPosition - fictitious starting position of
	 * @param room - wanted room 
	 * @param buildingDirection - desired building direction
	 * @return - null if unsuccessful, otherwise the fictitious starting location of the room
	 */
	public boolean tryConstructRoom(BlockPos startingPosition, DungeonRoom_new room, EnumDirection buildingDirection)
	{
		BlockPos newPosition = startingPosition;

		if(!hasSpaceForRoom(startingPosition, room, buildingDirection))
		{
			switch(buildingDirection)
			{
			case NORTH:
				startingPosition = startingPosition.north();
				break;
			case SOUTH:
				startingPosition = startingPosition.south();
				break;
			case EAST:
				startingPosition = startingPosition.east();
				break;
			case WEST:
				startingPosition = startingPosition.west();
				break;	
			}
			if(!hasSpaceForRoom(startingPosition, room, buildingDirection))
			{
				return false;
			}
		}

		room.setBuildingdirection(buildingDirection);
		room.setFictitious_startingLocation(startingPosition);

		for(int x = 0; x < room.getX(); x ++)
		{
			for(int y = 0; y < room.getY() - 1; y ++)
			{
				for(int z = 0; z < room.getZ(); z ++)
				{
					switch(buildingDirection)
					{
					case EAST: // + X, + Z
						newPosition = startingPosition.east(x).south(z).up(y);
						break;
					case WEST: // -X, - Z
						newPosition = startingPosition.west(x).north(z).up(y);
						break;
					case NORTH: // + Z, - X
						newPosition = new BlockPos(startingPosition.getX() + z, startingPosition.getY() + y, startingPosition.getZ() - x);
						break;
					case SOUTH://- Z, + X
						newPosition = new BlockPos(startingPosition.getX() - z, startingPosition.getY() + y, startingPosition.getZ() + x);
						break;
					}

					if(newPosition.getX() >= 0 && newPosition.getZ() >= 0 && newPosition.getY() >= 0)
					{
						//TODO: ADD changeLogInfoHERE
						if(newPosition.getX() < this.maxDungeonSize && newPosition.getZ() < this.maxDungeonSize && newPosition.getY() < this.maxYSize)
						{
							if(room.room_data.blocks[x][y][z].getBlock().toString().contains("_stairs"))
							{
								this.arena[newPosition.getX()][newPosition.getY()][newPosition.getZ()] = room.getCorrectStairOrientation(room.room_data.blocks[x][y][z], buildingDirection);
							}else
								this.arena[newPosition.getX()][newPosition.getY()][newPosition.getZ()] = room.room_data.blocks[x][y][z];
						}
					}
				}
			}
		}
		return true;
	}

	public void tryConnectExits(DungeonExit_new starting, DungeonExit_new finishing)
	{
		DungeonPath[] path = this.createDungeonPath(starting, finishing);

		if(path != null)
		{
			int amount = path[0].length, increment = 0, failures = 0;
			EnumDirection direction = path[0].getDir();
			DungeonExit_new current_exit = starting, old_exit = null;

			while(increment < path.length && failures <= 100)
			{
				DungeonRoom_new room = this.getRoom();
				int rotation = room.hasDirectionsWithRotation(starting.getOppositeDirection(), direction);

				if(rotation != -1)
				{
					boolean success = this.tryContructRoom(current_exit, room, rotation);

					if(success)
					{
						old_exit = current_exit;
						current_exit = room.getExitByDirection(direction);		

						int diff = 0;
						if(direction.equals(EnumDirection.NORTH) || direction.equals(EnumDirection.SOUTH))
						{
							diff = Math.abs(current_exit.getReal_position().getZ() - old_exit.getReal_position().getZ());
						}else
						{
							diff = Math.abs(current_exit.getReal_position().getX() - old_exit.getReal_position().getX());
						}

						if(amount - diff >= 10)
						{
							amount -= diff;
						}else
							amount = 0;

						if(amount == 0)
						{
							increment++;
							if(increment < path.length)
							{
								if(path[increment] != null)
								{
									amount = path[increment].getLength();
									direction = path[increment].getDir();
								}else
								{
									break;
								}
							}
						}
					}
				}else
					failures++;
			}

			path = createDungeonPath(current_exit, finishing);
			System.out.println("" + path);
			if(path != null)
			{
				if(path[0] != null)
				{
					BlockPos updated_pos = new BlockPos(current_exit.getReal_position());

					for(int i = 0; i < path.length; i ++)
					{
						if(path[i] != null)
						{
							amount = path[i].getLength();
							direction = path[i].getDir();
							
							for(int j = 0; j < amount; j ++)
							{
								switch(direction)
								{
								case EAST:
									updated_pos = updated_pos.east();
									break;
								case NORTH:
									updated_pos = updated_pos.north();
									break;
								case SOUTH:
									updated_pos = updated_pos.south();
									break;
								case WEST:
									updated_pos = updated_pos.west();
									break;
								}
	
								if(updated_pos.getX() < 500 && updated_pos.getZ() < 500)
								{
									if(updated_pos.getX() > 0 && updated_pos.getZ() > 0)
										arena[updated_pos.getX()][updated_pos.getY()][updated_pos.getZ()] = Blocks.STONE.getDefaultState();
								}
							}
						}

					}
					//create Y level height now
				}
			}
		}

		return;
	}

	/** Needs some function to determine which direction I need to build in, static results
	 * @param starting
	 * @param finishing
	 * @return
	 */
	public DungeonPath[] createDungeonPath(DungeonExit_new starting, DungeonExit_new finishing)
	{
		int differenceZ = starting.getReal_position().getZ() - finishing.getReal_position().getZ(); // if this is positive then head north, if negative then south
		int differenceX = starting.getReal_position().getX() - finishing.getReal_position().getX(); // if this is positive then head west, if negative then east

		int space, current = 0;
		EnumDirection dir = null;
		DungeonPath path[] = new DungeonPath[10];
		BlockPos pushed_pos = this.pushExitInDirection(starting), check_point2;

		if(starting.facing_direction.equals(EnumDirection.EAST) || starting.facing_direction.equals(EnumDirection.WEST))
		{
			if(differenceZ >= 0)
			{
				space = this.findDirectionWithSpace(pushed_pos, EnumDirection.NORTH);
				System.out.println("" + space);
				if(space + 5 >= Math.abs(differenceZ))
				{
					space = this.findDirectionWithSpace(pushed_pos.north(space), differenceX < 0 ? EnumDirection.EAST : EnumDirection.WEST);
					if(space + 5 >= Math.abs(differenceX))
					{
						dir = EnumDirection.NORTH;
						path[current] = new DungeonPath(differenceZ, dir);
						current++;
						path[current] = new DungeonPath(differenceX, differenceX < 0 ? EnumDirection.EAST : EnumDirection.WEST);
						current++;
					}
				}
			}else
			{
				space = this.findDirectionWithSpace(pushed_pos, EnumDirection.SOUTH);
				System.out.println("" + space);
				if(space + 5 >= Math.abs(differenceZ))
				{
					space = this.findDirectionWithSpace(pushed_pos.south(space), differenceX < 0 ? EnumDirection.EAST : EnumDirection.WEST);
					if(space + 5 >= Math.abs(differenceX))
					{
						dir = EnumDirection.SOUTH;
						path[current] = new DungeonPath(differenceZ, dir);
						current++;
						path[current] = new DungeonPath(differenceX, differenceX < 0 ? EnumDirection.EAST : EnumDirection.WEST);
						current++;
					}
				}
			}
		}else
		{
			if(differenceX >= 0)
			{
				space = this.findDirectionWithSpace(pushed_pos, EnumDirection.WEST);
				System.out.println("" + space);
				if(space + 5>= Math.abs(differenceX))
				{
					space = this.findDirectionWithSpace(pushed_pos.north(space), differenceZ < 0 ? EnumDirection.SOUTH : EnumDirection.NORTH);
					if(space + 5>= Math.abs(differenceX))
					{
						dir = EnumDirection.WEST;
						path[current] = new DungeonPath(differenceX, dir);
						current++;
						path[current] = new DungeonPath(differenceZ, differenceZ < 0 ? EnumDirection.SOUTH : EnumDirection.NORTH);
						current++;
					}
				}
			}else
			{
				space = this.findDirectionWithSpace(pushed_pos, EnumDirection.EAST);
				System.out.println("" + space);
				if(space + 5 >= Math.abs(differenceX))
				{
					space = this.findDirectionWithSpace(pushed_pos.south(space), differenceZ < 0 ? EnumDirection.SOUTH : EnumDirection.NORTH);
					if(space + 5 >= Math.abs(differenceX))
					{
						dir = EnumDirection.EAST;
						path[current] = new DungeonPath(differenceX, dir);
						current++;
						path[current] = new DungeonPath(differenceZ, differenceZ < 0 ? EnumDirection.SOUTH : EnumDirection.NORTH);
						current++;
					}
				}
			}
		}
		System.out.println("" + path);

		if(dir != null)
		{
			return path;
		}

		return null;
	}

	public BlockPos pushExitInDirection(DungeonExit_new starting)
	{
		switch(starting.getFacing_direction())
		{
		case EAST:
			return starting.getReal_position().east();
		case NORTH:
			return starting.getReal_position().north();
		case SOUTH:
			return starting.getReal_position().south();
		case WEST:
			return starting.getReal_position().west();
		}
		return null;
	}


	public int findDirectionWithSpace(BlockPos location1, EnumDirection dir)
	{
		BlockPos location = new BlockPos(location1);
		int air = 0;
		if(dir.equals(EnumDirection.NORTH))
		{
			for(int i =0; i < location.getZ(); i ++)
			{
				if(location.north(i).getZ() < 499)
				{	
					if(this.arena[location.getX()][location.getY()][location.north(i).getZ()] == null || this.arena[location.getX()][location.getY()][location.north(i).getZ()].equals(Blocks.AIR.getDefaultState()))
					{
						air++;
					}else
						break;
				}
			}
		}else if(dir.equals(EnumDirection.SOUTH))
		{
			//SOUTH
			for(int i =0; i < 500 - location.getZ(); i ++)
			{
				if(location.south(i).getZ() < 499)
				{
					if(this.arena[location.getX()][location.getY()][location.south(i).getZ()] == null || this.arena[location.getX()][location.getY()][location.south(i).getZ()].equals(Blocks.AIR.getDefaultState()))
					{
						air++;
					}else
						break;
				}else
					break;
			}
		}else if(dir.equals(EnumDirection.WEST))
		{
			//WEST
			for(int i =0; i < location.getX(); i ++)
			{

				if(location.west(i).getX() < 499)
				{
					if(this.arena[location.west(i).getX()][location.getY()][location.getZ()] == null || this.arena[location.west(i).getX()][location.getY()][location.getZ()].equals(Blocks.AIR.getDefaultState()))
					{
						air++;
					}else
						break;
				}
			}
		}else if(dir.equals(EnumDirection.EAST))
		{
			//EAST
			for(int i =0; i < 500 - location.getX(); i ++)
			{

				if(location.east(i).getX() < 499)
				{
					if(this.arena[location.east(i).getX()][location.getY()][location.getZ()] == null || this.arena[location.east(i).getX()][location.getY()][location.getZ()].equals(Blocks.AIR.getDefaultState()))
					{
						air++;
					}else
						break;
				}else
					break;
			}
		}

		return air;
	}

	public boolean hasSpaceForRoom(BlockPos startingPosition, DungeonRoom_new room, EnumDirection buildingDirection)
	{
		BlockPos newPosition = startingPosition;
		for(int x = 0; x < room.getX(); x ++)
		{
			for(int y = 0; y < room.getY() - 1; y ++)
			{
				for(int z = 0; z < room.getZ(); z ++)
				{
					switch(buildingDirection)
					{
					case EAST: // + X, + Z
						newPosition = startingPosition.east(x).south(z).up(y);
						break;
					case WEST: // -X, - Z
						newPosition = startingPosition.west(x).north(z).up(y);
						break;
					case NORTH: // + Z, - X
						newPosition = startingPosition.north(z).east(x).up(y);
						break;
					case SOUTH://- Z, + X
						newPosition = startingPosition.south(z).west(x).up(y);
						break;
					}
					if(newPosition.getX() >= 0 && newPosition.getZ() >= 0 && newPosition.getY() >= 0)
					{
						if(newPosition.getX() < this.maxDungeonSize && newPosition.getZ() < this.maxDungeonSize && newPosition.getY() < this.maxYSize)
						{
							if(this.arena[newPosition.getX()][newPosition.getY()][newPosition.getZ()] != null)
							{
								if(this.arena[newPosition.getX()][newPosition.getY()][newPosition.getZ()].getBlock() != Blocks.AIR)
								{
									return false;
								}
							}	
						}
					}else
						return false;
				}
			}
		}
		return true;
	}

	public boolean tryToCloseExit(DungeonExit_new exit, boolean isBossRoom)
	{
		if(!isBossRoom)
		{
			return this.tryContructRoom(exit, getExit());
		}else
			return this.tryContructRoom(exit, getBossRoom());
	}

	public void buildDungeonInWorld(World world, BlockPos world_startingLocation)
	{
		Random rand = new Random();

		for(int x = 0; x < this.maxDungeonSize; x ++)
		{
			for(int y = 0 ; y < this.maxYSize - 1; y ++)
			{
				for(int z = 0; z < this.maxDungeonSize; z ++)
				{
					if(this.arena[x][y][z] != null)
					{
						BlockPos blockPos = new BlockPos(world_startingLocation.getX() + x, world_startingLocation.getY() + y, world_startingLocation.getZ() + z);
						world.setBlockState(blockPos, arena[x][y][z]);
						this.applyBuildingEffect(arena[x][y][z], blockPos, world, y);
					}
				}
			}
		}
	}

	public boolean isFinished()
	{
		for(DungeonExit_new exit : all_exits)
		{
			if(!exit.looked_at)
			{
				return false;
			}
		}
		return true;
	}

	public void closeAllExits()
	{
		for (int i = 0; i < getAll_exits().size(); i++) 
		{
			if(tryToCloseExit(getAll_exits().get(i), false))
			{
				continue;
			}
		}
	}

	public abstract DungeonRoom_new getBossRoom();
	public abstract DungeonRoom_new getExit();
	public abstract DungeonRoom_new getRoom();
	public abstract void applyBuildingEffect(IBlockState buildingBlock, BlockPos placePos, World world, int currentY);
	public abstract void constructDungeon(World world, BlockPos startingLocation, int DungeonSize);
}
