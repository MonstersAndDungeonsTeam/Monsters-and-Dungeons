package monstersanddungeons;

import monstersanddungeons.events.CommonEventHandler;
import monstersanddungeons.proxy.CommonProxy;
import monstersanddungeons.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MonstersAndDungeons 
{
	@Instance(Reference.MODID)
	public static MonstersAndDungeons instance;
	
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit(event);
	}
}
