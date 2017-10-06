package monstersanddungeons.blocks.miscellaneous;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMagicalSapling extends Block{

	public BlockMagicalSapling(String name) 
	{
		super(Material.WOOD);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		GameRegistry.register(this);
	}

}
