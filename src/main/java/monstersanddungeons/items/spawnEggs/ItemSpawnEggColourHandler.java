package monstersanddungeons.items.spawnEggs;

import java.util.List;

import monstersanddungeons.entity.MaDEntityList;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemSpawnEggColourHandler implements IItemColor {
	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		List<Integer> colors = MaDEntityList.entityEggs.get(MaDEntityList.getClassFromID(stack.getItemDamage()));
		return colors != null && colors.size() > 1 ? colors.get((tintIndex == 0 ? 0 : 1)) : 16777215;
	}
}
