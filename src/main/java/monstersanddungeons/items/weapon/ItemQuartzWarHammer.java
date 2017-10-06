package monstersanddungeons.items.weapon;

import monstersanddungeons.util.dungeon.DungeonMaze;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemQuartzWarHammer extends ItemAxe{

	public ItemQuartzWarHammer(ToolMaterial material, String name) {
		super(material, 9, -3.2F);

		this.setCreativeTab(CreativeTabs.COMBAT);
				
		setUnlocalizedName(name);
		setRegistryName(name);
		
		GameRegistry.register(this);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/*
		if(!world.isRemote){
			DungeonMaze maze = new DungeonMaze(5, 5, false, true, true, world, pos);
			maze.generateMaze(world, pos);
		}
		*/
		return EnumActionResult.SUCCESS;
	}
}
