package monstersanddungeons.util.dungeon_new;

import monstersanddungeons.util.dungeon.EnumDirection;



public class DungeonPath 
{
	int length;
	EnumDirection dir;
	
	public DungeonPath(int length, EnumDirection dir) 
	{	
		this.length = Math.abs(length);
		this.dir = dir;
	}
	
	public EnumDirection getDir() {
		return dir;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setDir(EnumDirection dir) {
		this.dir = dir;
	}
	
	
	public void setLength(int length) {
		this.length = length;
	}
}
