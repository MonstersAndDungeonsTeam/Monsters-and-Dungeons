package monstersanddungeons.util;


import net.minecraft.block.state.IBlockState;

public class StructureData {

	public int xSize, ySize, zSize;
	String BuildingDirection; 
	String fileName;

	public 	IBlockState[][][] blocks;

	boolean isLoaded = false;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public int getzSize() {
		return zSize;
	}
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public boolean getIsLoaded()
	{
		return this.isLoaded;
	}

}
