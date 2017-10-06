package monstersanddungeons.util.dungeon;

import net.minecraft.util.math.BlockPos;

public class DungeonExit {


	EnumDirection direction;
	BlockPos pos; //Note this is only the distance from the starting point to the exit (0, 0, 0) Not actual position
	DungeonExit exit; //only used for the offset in build room
	boolean isCorrectPath;
	int rotation;

	public DungeonExit(BlockPos pos, EnumDirection direction)
	{
		this.direction = direction;
		this.pos = pos;

	}
	public DungeonExit(BlockPos pos, EnumDirection direction, DungeonExit exit)
	{
		this(pos, direction);
		this.exit = exit;
	}

	public void setCorrectPath(boolean isCorrectPath) {
		this.isCorrectPath = isCorrectPath;
	}

	public boolean getCorrectPath()
	{
		return this.isCorrectPath;
	}

	public DungeonExit getExit() {
		return exit;
	}
	
	public EnumDirection getDirection() {
		return this.direction;
	}
	
	public void setRotation(int rotation)
	{
		this.rotation = rotation;
		this.direction = this.getDirectionWithRotation(rotation);
		
	}
	
	/**
	 * Rotates in a clockwise direction
	 */
	public EnumDirection getDirectionWithRotation(int times)
	{
		EnumDirection rotatedDirection = this.getDirection(); //WEST
		
		for(int i = 0; i < times; i ++)
		{
			switch(rotatedDirection){
			case EAST:
				rotatedDirection = EnumDirection.SOUTH;
				continue;
			case NORTH:
				rotatedDirection = EnumDirection.EAST;
				continue;
			case SOUTH:
				rotatedDirection = EnumDirection.WEST;
				continue;
			case WEST:
				rotatedDirection = EnumDirection.NORTH;
				continue;
			default:
				continue;
			}		
		}
		return rotatedDirection;
	}
	
	
	public EnumDirection getOppositeDirection(){
		switch(this.getDirection()){
		case EAST:
			return EnumDirection.WEST;
		case NORTH:
			return EnumDirection.SOUTH;
		case SOUTH:
			return EnumDirection.NORTH;
		case WEST:
			return EnumDirection.EAST;
		default:
			return this.getDirection();
		}	
	}
	
	
	public void setDirection(EnumDirection direction) {
		this.direction = direction;
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

}
