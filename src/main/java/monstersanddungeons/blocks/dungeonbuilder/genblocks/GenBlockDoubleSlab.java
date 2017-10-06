package monstersanddungeons.blocks.dungeonbuilder.genblocks;

import net.minecraft.block.material.Material;

public class GenBlockDoubleSlab extends GenBlockSlab {

	public GenBlockDoubleSlab(Material materialIn, String Name) {
		super(Name, materialIn);
		
	}

	@Override
	public boolean isDouble() {
		// TODO Auto-generated method stub
		return true;
	}
}
