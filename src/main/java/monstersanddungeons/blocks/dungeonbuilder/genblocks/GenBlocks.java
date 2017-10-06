package monstersanddungeons.blocks.dungeonbuilder.genblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenBlocks extends Block{

	public GenBlocks(String name) {
		super(Material.WOOD);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}
	
}
