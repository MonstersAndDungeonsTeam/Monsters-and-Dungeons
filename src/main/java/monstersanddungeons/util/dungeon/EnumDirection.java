package monstersanddungeons.util.dungeon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EnumFaceDirection;

public enum EnumDirection {
	WEST(0, "WEST"),
	NORTH(1, "NORTH"),
	EAST(2, "EAST"),
	SOUTH(3, "SOUTH");
	
	int id;
	String direction;
	private EnumDirection(int i, String direction){
		this.id = i;
		this.direction = direction;
	}
	
	public static EnumDirection getDirectionFromID(int id){
		
		for(EnumDirection dir : values()){
			if(dir.getID() == id){
				return dir;
			}
		}
		return null;
	}
	
	public static EnumDirection getDirectionFromBlockState(IBlockState state){
		int i = state.getBlock().getMetaFromState(state);
		
		for(EnumDirection dir : values()){
			if(dir.getID()-1 == i){
				return dir;
			}else if(dir == WEST && i == 3){
				return dir;
			}
		}
		return null;
	}
	
	public static EnumDirection getDirectionFromString(String dir){
		
		if(dir.equals("WEST")){
			return WEST;
		}else if(dir.equals("EAST"))
		{
			return EAST;
		}else if(dir.equals("NORTH"))
		{
			return NORTH;
		}else if(dir.equals("SOUTH"))
		{
			return SOUTH;
		}
		
		return null;
	}
	
	public static EnumDirection rotateDirection(EnumDirection dir, int rotation)
	{
		EnumDirection newDir = dir;
		for(int i = 0; i < rotation; i ++)
		{
			switch(dir)
			{
			case EAST:
				newDir = EnumDirection.SOUTH;
				continue;
			case NORTH:
				newDir = EnumDirection.EAST;
				continue;
			case SOUTH:
				newDir = EnumDirection.WEST;
				continue;
			case WEST:
				newDir = EnumDirection.NORTH;
			}
		}
		return newDir;
	}
	
	public static EnumDirection getOppositeDirection(EnumDirection dir)
	{
		switch(dir)
		{
		case WEST:
			return EAST;
		case EAST:
			return WEST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		default:
			return EAST;
		}
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getDirection() {
		return direction;
	}
}
