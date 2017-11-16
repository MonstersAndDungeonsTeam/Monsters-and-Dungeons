package ruukas.monstersanddungeons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ruukas.monstersanddungeons.block.AllBlocks;
import ruukas.monstersanddungeons.item.AllItems;
import ruukas.monstersanddungeons.tileentity.AllTileEntities;

public class CommonProxy {
	public void preInit() {
		MonstersAndDungeons.CREATIVE_TAB = new CreativeTabs(MonstersAndDungeons.MODID) {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(AllBlocks.ENDERLIGHT);
			}
		};
		
		AllBlocks.initBlocks();
		AllItems.initItems();
		AllTileEntities.initTileEntities();
	}

	public void init() {
	}
}
