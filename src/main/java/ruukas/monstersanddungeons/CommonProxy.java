package ruukas.monstersanddungeons;

import ruukas.monstersanddungeons.item.AllItems;

public class CommonProxy {
	public void preInit() {
		AllItems.initItems();
	}

	public void init() {
	}
}
