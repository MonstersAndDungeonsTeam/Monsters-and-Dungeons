package monstersanddungeons.items.armor;

import net.minecraft.item.ItemShield;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemQuartzShield extends ItemShield {



	public ItemQuartzShield(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		
		GameRegistry.register(this);
	}
}
