package ruukas.monstersanddungeons;

import ruukas.monstersanddungeons.block.AllBlocks;
import ruukas.monstersanddungeons.item.AllItems;
import ruukas.monstersanddungeons.tileentity.AllTileEntities;

public class CommonProxy {
	public void preInit() {
		AllItems.initItems();
		AllBlocks.initBlocks();
		AllTileEntities.initTileEntities();
	}

	public void init() {
	}
}
