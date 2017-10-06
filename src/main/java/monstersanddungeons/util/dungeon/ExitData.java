package monstersanddungeons.util.dungeon;

import net.minecraft.util.math.BlockPos;

public class ExitData
{
	boolean alreadyBuilt;
	BlockPos pos;
	EnumDirection direction;
	DungeonRoom previousRoom;
	DungeonExit RealExit;
	
	public ExitData(BlockPos pos, EnumDirection enumDirection, boolean alreadyBuilt) 
	{
		this.pos = pos;
		this.direction = enumDirection;
		this.alreadyBuilt = alreadyBuilt;
	}
	
	public ExitData(BlockPos pos, DungeonExit exit, DungeonRoom room, boolean alreadyBuilt)
	{
		this(pos, exit.getDirection(), alreadyBuilt);
		previousRoom = room;
		RealExit = exit;
	}
	
	public boolean getAlreadyBuilt()
	{
		return this.alreadyBuilt;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public EnumDirection getDirection() {
		return direction;
	}
	
	public DungeonRoom getPreviousRoom() {
		return previousRoom;
	}
	
	public DungeonExit getRealExit() {
		return RealExit;
	}
}