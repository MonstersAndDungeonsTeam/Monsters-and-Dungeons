package monstersanddungeons.util.entity;

import java.util.List;

public interface IMaDShopKeeper 
{
	public List<ShopItem> getSellList();
	
	public int getMaxRestockTime();
	public int getCurrentRestockTime();
	public int getShopID();
}
