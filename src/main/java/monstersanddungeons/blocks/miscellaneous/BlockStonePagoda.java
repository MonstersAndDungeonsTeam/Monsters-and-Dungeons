package monstersanddungeons.blocks.miscellaneous;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.common.registry.GameRegistry;





public class BlockStonePagoda extends Block{

	public BlockStonePagoda(String name) {
		super(Material.ROCK);
		
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}


	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

}
