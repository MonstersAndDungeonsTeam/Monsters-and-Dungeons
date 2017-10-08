package monstersanddungeons.util.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ShopItem 
{
	int stock, mood;
	ItemStack trade_item, cost_item;

	public ShopItem(int stock, int mood, ItemStack trade_item, ItemStack cost_item) {

		this.stock = stock;
		this.mood = mood;
		this.trade_item = trade_item;
		this.cost_item = cost_item;
	}

	public ItemStack getTrade_item() {
		return trade_item;
	}

	public ItemStack getCost_item() {
		return cost_item;
	}

	public int getStock() {
		return stock;
	}

	public int getmood() {
		return mood;
	}

	public boolean canTradeItem(EntityPlayer player)
	{
		if(this.stock > 0 )
		{
			for(ItemStack stack : player.inventory.mainInventory)
			{
				if(stack != null)
				{
					if(stack.isItemEqual(cost_item))
					{
						if(stack.getCount() >= getCost_item().getCount())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void tradeItem(EntityPlayer player)
	{
		if(this.canTradeItem(player))
		{
			if(player.inventory.addItemStackToInventory(trade_item))
			{
				for(int i = 0; i < player.inventory.mainInventory.size(); i ++)
				{
					if(player.inventory.mainInventory.get(i) != null)
					{
						if(player.inventory.mainInventory.get(i).isItemEqual(getCost_item()))
						{
							player.inventory.decrStackSize(i, getCost_item().getCount());
							//this.stock--;
							return;
						}
					}
				}
			}
		}
	}
}
