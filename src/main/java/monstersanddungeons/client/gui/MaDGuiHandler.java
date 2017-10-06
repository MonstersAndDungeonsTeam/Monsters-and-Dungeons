package monstersanddungeons.client.gui;


import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.packet.UpdateShopKeeperList;
import monstersanddungeons.util.entity.IMaDShopKeeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;




public class MaDGuiHandler implements IGuiHandler {


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
       
    	if(ID == 0)
    	{
    		MaDPacketHandler.INSTANCE.sendTo(new UpdateShopKeeperList((IMaDShopKeeper) world.getEntityByID(x)), (EntityPlayerMP) player);
    		return null;
    	}

    	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
    {
        
     
    	if(ID == 0)
    	{
    		return new GuiBasicShopKeeper((IMaDShopKeeper) world.getEntityByID(x));
    	}
    	
    	return null;
    }
    
    
    
}