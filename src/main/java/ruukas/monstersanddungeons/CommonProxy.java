package ruukas.monstersanddungeons;

import ruukas.monstersanddungeons.block.AllBlocks;
import ruukas.monstersanddungeons.item.AllItems;
import ruukas.monstersanddungeons.tileentity.AllTileEntities;

public class CommonProxy {
	public void preInit() {
		AllBlocks.initBlocks();
		AllItems.initItems();
		AllTileEntities.initTileEntities();
	}

	public void init() {
	}
}
