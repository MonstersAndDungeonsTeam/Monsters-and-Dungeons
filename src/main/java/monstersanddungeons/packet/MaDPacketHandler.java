package monstersanddungeons.packet;


import monstersanddungeons.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class MaDPacketHandler {

	 public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 
	    
	 public static void init() 
	 {
		  INSTANCE.registerMessage(UpdateClientEntityAnimation.class, UpdateClientEntityAnimation.class, 0, Side.CLIENT);
		  INSTANCE.registerMessage(GivePlayerItem.class, GivePlayerItem.class, 1, Side.SERVER);
		  INSTANCE.registerMessage(UpdateShopKeeperList.class, UpdateShopKeeperList.class, 2, Side.CLIENT);
	 }	
}
