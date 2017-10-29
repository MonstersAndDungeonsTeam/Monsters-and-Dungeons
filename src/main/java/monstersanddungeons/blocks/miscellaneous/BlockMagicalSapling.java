package monstersanddungeons.blocks.miscellaneous;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMagicalSapling extends Block{

	public BlockMagicalSapling(String name) 
	{
		super(Material.WOOD);
		
		setUnlocalizedName(name);
		setRegistryName(name);
	}
}
