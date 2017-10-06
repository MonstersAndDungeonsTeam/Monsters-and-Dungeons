package monstersanddungeons.blocks.dungeonbuilder.genblocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenBlockStairs extends BlockStairs {

	public GenBlockStairs(IBlockState modelState, String name) {
		super(modelState);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}
}
