package ruukas.monstersanddungeons;

import ruukas.monstersanddungeons.block.AllBlocks;
import ruukas.monstersanddungeons.item.AllItems;

public class CommonProxy {
	public void preInit() {
		AllItems.initItems();
		AllBlocks.initBlocks();
	}

	public void init() {
	}
}
