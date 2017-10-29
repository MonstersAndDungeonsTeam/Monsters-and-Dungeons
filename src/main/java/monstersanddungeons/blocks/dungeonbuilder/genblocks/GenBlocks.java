package monstersanddungeons.blocks.dungeonbuilder.genblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class GenBlocks extends Block{

	public GenBlocks(String name) {
		super(Material.WOOD);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
}
