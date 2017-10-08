package monstersanddungeons.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import monstersanddungeons.util.entity.IMaDShopKeeper;
import monstersanddungeons.util.entity.ShopItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateShopKeeperList implements IMessage, IMessageHandler<UpdateShopKeeperList , IMessage> {

	int shopKeeper;
	List<ShopItem> shop_items;
	public UpdateShopKeeperList(){ }

	public UpdateShopKeeperList(IMaDShopKeeper keeper)
	{
		shopKeeper = keeper.getShopID();
		shop_items = keeper.getSellList();
	}
	
	@Override
	public IMessage onMessage(final UpdateShopKeeperList message, MessageContext ctx) {

		IThreadListener mainThread = Minecraft.getMinecraft();
		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				EntityPlayer p = Minecraft.getMinecraft().player;
				World world = p.world;
				
				IMaDShopKeeper keeper = (IMaDShopKeeper) world.getEntityByID(message.shopKeeper);
				if(keeper != null)
				{
					List<ShopItem> shop_items = keeper.getSellList();
					shop_items.clear();
					shop_items.addAll(message.shop_items);
				}
			}
		});

		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.shopKeeper = buf.readInt();
		int size = buf.readInt();
		this.shop_items = new ArrayList<ShopItem>();
		
		for(int i = 0; i < size; i ++)
		{
			int stock = buf.readInt();
			
			ItemStack trade = ByteBufUtils.readItemStack(buf), cost = ByteBufUtils.readItemStack(buf);
			shop_items.add(new ShopItem(stock, 0, trade, cost));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(shopKeeper);
		buf.writeInt(shop_items.size());
		
		int i = 0;
		for(ShopItem item : shop_items)
		{
			buf.writeInt(item.getStock());
			ByteBufUtils.writeItemStack(buf, item.getTrade_item());
			ByteBufUtils.writeItemStack(buf, item.getCost_item());
		}
	}
}
