package monstersanddungeons.util.dungeon_new;

import monstersanddungeons.util.dungeon.EnumDirection;
import net.minecraft.util.math.BlockPos;

public class DungeonExit_new 
{
	
	//facing_direction means original, roated_direction means its been rotated
	EnumDirection facing_direction;
	
	//fictitious means how far away from starting position, and real means inside array
	BlockPos fictitious_position, real_position;
	
	boolean looked_at = false;
	
	public DungeonExit_new(EnumDirection facing_direction, BlockPos fictitious_position) 
	{
		this.fictitious_position = fictitious_position;
		this.facing_direction = facing_direction;
	}
	
	public EnumDirection getFacing_direction() {
		return facing_direction;
	}
	
	public BlockPos getFictitious_position() {
		return fictitious_position;
	}
	
	public BlockPos getReal_position() {
		return real_position;
	}
	
	public void setFacing_direction(EnumDirection facing_direction) {
		this.facing_direction = facing_direction;
	}
	
	public void setFictitious_position(BlockPos fictitious_position) {
		this.fictitious_position = fictitious_position;
	}
	
	public void setReal_position(BlockPos real_position) {
		this.real_position = real_position;
	}
	
	public EnumDirection getOppositeDirection()
	{
		return EnumDirection.getOppositeDirection(getFacing_direction());
	}

	public EnumDirection getDirectionWithRotation(int times)
	{
		EnumDirection rotatedDirection = this.getFacing_direction();
		
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
	
	
	public void calculateRealPosition(BlockPos startingPosition, EnumDirection dir)
	{
		switch(dir)
		{
		case EAST: // + X, + Z
			real_position = new BlockPos(startingPosition.getX() + this.fictitious_position.getX(), startingPosition.getY() + fictitious_position.getY(), startingPosition.getZ() + this.fictitious_position.getZ());
			break;
		case WEST: // -X, - Z
			real_position = new BlockPos(startingPosition.getX() - this.fictitious_position.getX(), startingPosition.getY() + fictitious_position.getY(), startingPosition.getZ() - this.fictitious_position.getZ());
			break;
		case NORTH: // + Z, - X
			real_position= new BlockPos(startingPosition.getX() + this.fictitious_position.getZ(), startingPosition.getY() + fictitious_position.getY(), startingPosition.getZ() - this.fictitious_position.getX());
			break;
		case SOUTH://- Z, + X
			real_position= new BlockPos(startingPosition.getX() - this.fictitious_position.getZ(), startingPosition.getY() + fictitious_position.getY(), startingPosition.getZ() + this.fictitious_position.getX());
			break;
		}
	}
	
	public boolean getLookedAt()
	{
		return this.looked_at;
	}
	
	public void setLookedat(boolean looked_at) 
	{
		this.looked_at = looked_at;
	}
}
