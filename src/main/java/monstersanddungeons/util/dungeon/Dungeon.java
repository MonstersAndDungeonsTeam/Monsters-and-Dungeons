package monstersanddungeons.util.dungeon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Dungeon {

	
	//FOR NEW SYSTEM it will work with a 3D array, a changelog (stack, or list), and total exits list
	//needed functions hasSpaceFor(Room), deleted last change, deleteChange(), 
	//algorithim will start by marching through each exit in the list, the same as the traversal, each time it places an new room
	//the old exit gets replaced by the new ones

	int dungeonSize;
	List<ExitData> totalExits = new ArrayList<ExitData>(); 

	public Dungeon(int dungeonSize)
	{
		this.dungeonSize = dungeonSize;
	}

	public int getDungeonSize() {
		return dungeonSize;
	}

	public List<ExitData> getTotalExits() {
		return totalExits;
	}

	/**
	 * This method will create a random branch at a starting location heading in a specific direction
	 * @param world
	 * @param startingLocation - "WEST" , "EAST", "NORTH", "SOUTH";
	 * @param enumDirection
	 */
	public void createBranch(World world, BlockPos startingLocation, int DungeonSize, DungeonRoom PreviousRoom, EnumDirection enumDirection) {
		DungeonRoom NextRoom = this.selectRandomRoom();
		BlockPos UpdatedLocation = startingLocation;

		for(int i = 0; i < DungeonSize; i ++)
		{
			if(PreviousRoom != null)
			{
				DungeonExit ExitToRemove = PreviousRoom.setCorrectPath(enumDirection);
				DungeonExit TempExit = null;
				for(int j = 0; j < PreviousRoom.getExits().size(); j ++)
				{
					if(!PreviousRoom.getExits().get(j).getCorrectPath())
					{
						totalExits.add(new ExitData(getExitActualCoord(PreviousRoom.getExits().get(j), UpdatedLocation, PreviousRoom), PreviousRoom.getExits().get(j), PreviousRoom, false));	
					}else
					{
						if(!NextRoom.isLoaded())
						{
							NextRoom.loadRoom();
						}
						totalExits.add(new ExitData(getExitActualCoord(PreviousRoom.getExits().get(j), UpdatedLocation, PreviousRoom), PreviousRoom.getExits().get(j), PreviousRoom, true));
						TempExit = NextRoom.alignWithRoom(NextRoom, PreviousRoom, PreviousRoom.getExits().get(j), UpdatedLocation);
						NextRoom.setPrevBuiltDirection(TempExit.getDirection());
						NextRoom.buildRoom(TempExit.getPos(), world, TempExit.getDirection(), TempExit.getExit().getPos().getY() - 1);
						NextRoom.removeOppositeExit(ExitToRemove);	
					}
				}
				UpdatedLocation = TempExit.getPos();	
			}else
			{
				if(!NextRoom.isLoaded())
				{
					NextRoom.loadRoom();
				}
				NextRoom.buildRoom(startingLocation, world, EnumDirection.WEST, 0);
				NextRoom.setPrevBuiltDirection(EnumDirection.WEST);
			}
			PreviousRoom = NextRoom;
			NextRoom = this.selectRandomRoom();
		}
	}

	private BlockPos getExitActualCoord(DungeonExit exit, BlockPos tempExit, DungeonRoom previousRoom)
	{
		EnumDirection prevBuiltDirection = previousRoom.getPrevBuiltDIRECTION();
		BlockPos RealPrevEntrance = null;

		if(prevBuiltDirection == EnumDirection.WEST) // gets the entrance actual position
		{
			RealPrevEntrance = new BlockPos(tempExit.getX() - exit.getPos().getX(), tempExit.getY(), tempExit.getZ() - exit.getPos().getZ());
		}else if(prevBuiltDirection == EnumDirection.EAST)
		{
			RealPrevEntrance = new BlockPos(tempExit.getX() + exit.getPos().getX(), tempExit.getY(), tempExit.getZ() + exit.getPos().getZ());
		}else if(prevBuiltDirection == EnumDirection.NORTH)
		{
			RealPrevEntrance = new BlockPos(tempExit.getX() + exit.getPos().getZ(), tempExit.getY(), tempExit.getZ() - exit.getPos().getX());
		}else if(prevBuiltDirection == EnumDirection.SOUTH)
		{
			RealPrevEntrance = new BlockPos(tempExit.getX() - exit.getPos().getZ(), tempExit.getY(), tempExit.getZ() + exit.getPos().getX());
		}
		return RealPrevEntrance;
	}

	private ExitData FindCandidate(ExitData data)
	{
		//Need to pair exits together to process them efficiently...
		//Before anything check to see if we have space(dont know how to do this)
		/* Step One: Determine Likely candidates 
		 *  1. Select all the exits that can be seen from looking at the exits point of view...Only look backwards for maybe 5 blocks	
		 *  2. Select the closest distance (maybe might mean all paths are small and short)
		 *  3. If there are no exits that are good, and has space then chance to create branch from that point
		 *  4. This method will keep running until the size of the list is zero
		 */
		ExitData mostProbable = null;
		int closestValue = 0;
		int tempClosestValue;

		for(ExitData exit : totalExits)
		{	
			if(data.getDirection() == EnumDirection.WEST)
			{
				if(exit.getPos().getX() < data.getPos().getX())
				{
					tempClosestValue = Math.abs(exit.getPos().getX());

					if(tempClosestValue < closestValue || closestValue == 0)
					{
						closestValue = tempClosestValue;
						mostProbable = exit;
					}
				}

			}else if(data.getDirection() == EnumDirection.EAST)
			{
				if(exit.getPos().getX() > data.getPos().getX())
				{
					tempClosestValue = Math.abs(exit.getPos().getX());

					if(tempClosestValue < closestValue || closestValue == 0)
					{
						closestValue = tempClosestValue;
						mostProbable = exit;
					}
				}

			}else if(data.getDirection() == EnumDirection.NORTH)
			{
				if(exit.getPos().getZ() < data.getPos().getZ())
				{
					mostProbable = exit;
				}

			}else if(data.getDirection() == EnumDirection.SOUTH)
			{
				if(exit.getPos().getZ() > data.getPos().getZ())
				{
					mostProbable = exit;
				}
			}

		}

		return mostProbable;
	}



	/** Places 1 single room
	 * core meaning the direction stored inside the exit data
	 * @param firstExit
	 */
	public void createBranch(ExitData firstExit, World world, int DungeonSize)
	{
		DungeonRoom previousRoom = firstExit.getPreviousRoom();
		
		DungeonRoom nextRoom = this.selectRandomRoom();
		nextRoom.loadRoom();
		
		
		if(!hasEnoughRoom(firstExit, nextRoom.getRoomStructure().xSize, nextRoom.getRoomStructure().ySize))
		{
			closeBranch(firstExit, world, false);
			return;
		}
		
		BlockPos realPrevEntrance = firstExit.getPos();
		DungeonExit previousEntrance = firstExit.getRealExit(); // this is the exit we need to work with

		DungeonExit exit = getRoomRotationRoom(nextRoom, previousEntrance.getDirection(), previousEntrance.getOppositeDirection());
		EnumDirection direction = exit.getDirection();

		if(direction == null){
			closeBranch(firstExit, world, false);
			return;
		}
		
		if(previousEntrance.getDirection() == EnumDirection.EAST){
			if(direction == EnumDirection.NORTH){
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + nextRoom.getRoomStructure().zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + nextRoom.getRoomStructure().xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + nextRoom.getRoomStructure().xSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + nextRoom.getRoomStructure().zSize);
			}

		}else if(previousEntrance.getDirection() == EnumDirection.WEST)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - nextRoom.getRoomStructure().xSize , realPrevEntrance.getY(), realPrevEntrance.getZ() - nextRoom.getRoomStructure().zSize);
			}else if(direction == EnumDirection.SOUTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - nextRoom.getRoomStructure().zSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - nextRoom.getRoomStructure().zSize);
			}

		}else if(previousEntrance.getDirection() == EnumDirection.NORTH)
		{
			//RealPrevEntrance = new BlockPos(RealPrevEntrance.getX() - NextRoom.getRoomStructure().xSize, RealPrevEntrance.getY(), RealPrevEntrance.getZ() - NextRoom.getRoomStructure().zSize);
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() - nextRoom.getRoomStructure().zSize);
			}else if(direction == EnumDirection.SOUTH)
			{

				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + nextRoom.getRoomStructure().zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() - nextRoom.getRoomStructure().xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() + nextRoom.getRoomStructure().xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}

		}else if(previousEntrance.getDirection() == EnumDirection.SOUTH)
		{
			if(direction == EnumDirection.EAST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - nextRoom.getRoomStructure().xSize, realPrevEntrance.getY(), realPrevEntrance.getZ());
			}else if(direction == EnumDirection.NORTH)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX() - nextRoom.getRoomStructure().zSize, realPrevEntrance.getY(), realPrevEntrance.getZ() + nextRoom.getRoomStructure().xSize);
			}else if(direction == EnumDirection.WEST)
			{
				realPrevEntrance = new BlockPos(realPrevEntrance.getX(), realPrevEntrance.getY(), realPrevEntrance.getZ() + nextRoom.getRoomStructure().zSize);
			}

		}
		int j = direction.getID();

		if(firstExit.getRealExit().getDirection() != null)
		{
			switch (firstExit.getDirection()) {
			case WEST:
				realPrevEntrance = realPrevEntrance.south(j % 2 == 0 ? firstExit.getRealExit().getPos().getZ() : firstExit.getRealExit().getPos().getX());
				break;
			case NORTH:
				realPrevEntrance = realPrevEntrance.west(j % 2 == 0 ? firstExit.getRealExit().getPos().getX() : firstExit.getRealExit().getPos().getZ());
				break;
			case EAST:
				realPrevEntrance = realPrevEntrance.north(j % 2 == 0 ? firstExit.getRealExit().getPos().getZ() : firstExit.getRealExit().getPos().getX());
				break;
			case SOUTH:
				realPrevEntrance = realPrevEntrance.east(j % 2 == 0 ? firstExit.getRealExit().getPos().getX() : firstExit.getRealExit().getPos().getZ());
				break;
			}
		}
		nextRoom.removeOppositeExit(previousEntrance);
		nextRoom.buildRoom(realPrevEntrance, world, direction, exit.getPos().getY() - 1);
		nextRoom.setPrevBuiltDirection(direction);

		createBranch(world, realPrevEntrance, DungeonSize, nextRoom, firstExit.getDirection());
	//	createBranch(world, realPrevEntrance, DungeonSize, nextRoom, null);
	}

	/** DOES NOT WORK YET!
	 * @param startingPoint - will create branch path that has a desired location, startingPoint must always be behind or equal to finishingPoint
	 * @param finishingPoint
	 */
	public void createBranch(ExitData startingPoint, ExitData finishingPoint, World world) // exit data contains the room the exit belongs to
	{

		EnumDirection facingDirection = startingPoint.getDirection();
		EnumDirection goingDirection = finishingPoint.getDirection();

		BlockPos currentLocation = startingPoint.getPos();
		//how far on the x and z, choose random room see if possible then build it, re check how far on x and z, choose random room

		int disX = Math.abs(startingPoint.getPos().getX() - startingPoint.getPos().getZ());
		int disY = Math.abs(startingPoint.getPos().getZ() - startingPoint.getPos().getZ());

		if(facingDirection == EnumDirection.WEST || facingDirection == EnumDirection.EAST)
		{
			DungeonRoom PreviousRoom = startingPoint.getPreviousRoom();
			DungeonRoom NextRoom = this.selectRandomRoom();
			BlockPos UpdatedLocation = startingPoint.getPos();
		}
		/*
			while(disX >= 5)
			{
				if(PreviousRoom != null)
				{
					DungeonExit ExitToRemove = PreviousRoom.setCorrectPath(facingDirection);
					DungeonExit TempExit = null;
					for(int j = 0; j < PreviousRoom.getExits().size(); j ++)
					{
						if(!PreviousRoom.getExits().get(j).getCorrectPath())
						{
							//stores the actual coordinates in world inside the exit blockPos, Now can do real calculations to determine path connections
						//	totalExits.add(new ExitData(getExitActualCoord(PreviousRoom.getExits().get(j), UpdatedLocation, PreviousRoom), PreviousRoom.getExits().get(j), PreviousRoom));	
						}else
						{
							if(!NextRoom.isLoaded())
							{
								NextRoom.loadRoom();
							}
							TempExit = NextRoom.alignWithRoom(NextRoom, PreviousRoom, PreviousRoom.getExits().get(j), UpdatedLocation);
							String direction = getRoomRotationRoom(NextRoom, startingPoint.getRealExit().getOppositeDirection(), goingDirection);

							if(direction != null)
								TempExit.setDirection(direction);

							NextRoom.setPrevBuiltDIRECTION(TempExit.getDirection());
							NextRoom.buildRoom(TempExit.getPos(), world, TempExit.getDirection());
							NextRoom.removeOppositeExit(ExitToRemove);	
						}
					}
					UpdatedLocation = TempExit.getPos();	
					disX -= NextRoom.roomStructure.xSize;
				}
			}
		}
		 */
	}

	/*
	 * This method will the return the rotation that has both exits or null if not possible...Also this method prepares the room
	 * by rotating all of the exits when the value is found. Easier to build
	 */
	private DungeonExit getRoomRotationRoom(DungeonRoom Nextroom, EnumDirection firstExit, EnumDirection secondExit)
	{
		EnumDirection direction = null;
		BlockPos pos = null;

		int j =0;
		here:
			do{
				boolean hasFirstExit = false, hasSecondExit = false;

				for(int i = 0; i < Nextroom.getExits().size(); i ++)// 0 = west, 1 = north, 2 = east, 3 = south
				{
					if(Nextroom.getExits().get(i).getDirectionWithRotation(j) == firstExit)
					{
						hasFirstExit =  true;
					}else
						if(Nextroom.getExits().get(i).getDirectionWithRotation(j) == secondExit)
						{
							hasSecondExit = true;
							pos = Nextroom.getExits().get(i).getPos();
						}

					if(hasFirstExit && hasSecondExit)
					{
						direction = EnumDirection.getDirectionFromID(j);

						for(int l = 0; l < Nextroom.getExits().size(); l ++)
						{
							Nextroom.getExits().get(l).setRotation(j);
						}
						break here;
					}
				}
				j++;
			}while(j < 4);
		return new DungeonExit(pos, direction);
	}



	protected boolean hasEnoughRoom(ExitData exit, int xRange, int zRange){
		EnumDirection direction = exit.getDirection();

		int posX = exit.getPos().getX();
		int posZ = exit.getPos().getZ();

		if (direction == EnumDirection.WEST) {
			for(ExitData data: totalExits)
			{
				if(posX > data.getPos().getX())
				{
					if(Math.abs((posX - data.getPos().getX())) < xRange)
					{
						if(Math.abs((posZ - data.getPos().getZ())) < zRange)

						{
							return false;
						}
					}
				}
			}
		} else if (direction == EnumDirection.EAST) {
			for(ExitData data: totalExits)
			{
				if(posX < data.getPos().getX())
				{

					if(Math.abs((posX - data.getPos().getX())) < xRange)
					{
						if(Math.abs((posZ - data.getPos().getZ())) < zRange)

						{
							return false;
						}
					}
				}
			}
		} else if (direction == EnumDirection.NORTH) {
			for(ExitData data: totalExits)
			{
				if(posZ > data.getPos().getZ())
				{

					if(posZ - data.getPos().getZ() < zRange)
					{
						if(Math.abs((posX - data.getPos().getX())) < xRange)

						{
							return false;
						}
					}
				}
			}
		} else if (direction == EnumDirection.SOUTH) {
			for(ExitData data: totalExits)
			{
				if(posZ < data.getPos().getZ())
				{
					if(Math.abs((posZ - data.getPos().getZ())) < zRange)
					{
						if(Math.abs((posX - data.getPos().getX())) < xRange)

						{
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	//working

	public void closeBranch(ExitData startingPoint, World world, boolean spawnBoss)
	{
		DungeonRoom exit = spawnBoss ? getBossRoom() : selectRandomExit();
		exit.loadRoom();
		if(hasEnoughRoom(startingPoint, exit.getRoomStructure().getxSize(), exit.getRoomStructure().getzSize()))
		{
			DungeonExit tempExit = startingPoint.getPreviousRoom().TESTalignWithRoom(exit, startingPoint.getPreviousRoom(), startingPoint.getRealExit(), startingPoint.getPos());
			exit.buildRoom(tempExit.getPos(), world, tempExit.getDirection(), tempExit.getExit().getPos().getY() - 1);
		}	
	}

	public abstract DungeonRoom selectRandomRoom();
	public abstract DungeonRoom selectRandomExit();
	public abstract DungeonRoom getBossRoom();
	public abstract void ConstructDungeon(World world, BlockPos startingLocation, int DungeonSize);

}
