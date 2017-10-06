package monstersanddungeons.packet;

import io.netty.buffer.ByteBuf;

import java.util.List;

import monstersanddungeons.util.entity.IMaDShopKeeper;
import monstersanddungeons.util.entity.ShopItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GivePlayerItem implements IMessage, IMessageHandler<GivePlayerItem, IMessage> {

	
	int shop_keeperID, trade_item;
	
	public GivePlayerItem(){ }

	public GivePlayerItem(int shop_keeperID, int trade_item)
	{
		this.shop_keeperID = shop_keeperID;
		this.trade_item = trade_item;
	}
	
	
	@Override
	public IMessage onMessage(final GivePlayerItem message, MessageContext ctx) 
	{
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		World world = player.worldObj;
		
		IMaDShopKeeper shopkeeper = (IMaDShopKeeper) world.getEntityByID(message.shop_keeperID);
		
		if(shopkeeper != null)
		{
			
			List<ShopItem> stacks = shopkeeper.getSellList();
			
			ShopItem desired = stacks.get(message.trade_item);
			desired.tradeItem(player);
		}
	
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.shop_keeperID = buf.readInt();
		this.trade_item = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(shop_keeperID);
		buf.writeInt(trade_item);
		
	}
}
