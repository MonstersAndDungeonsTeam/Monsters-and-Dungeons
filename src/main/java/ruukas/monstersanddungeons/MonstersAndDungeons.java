package ruukas.monstersanddungeons;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = MonstersAndDungeons.NAME, modid = MonstersAndDungeons.MODID, version = MonstersAndDungeons.VERSION)
public class MonstersAndDungeons
{
	public static final String NAME = "Monsters and Dungeons";
    public static final String MODID = "monstersanddungeons";
    public static final String VERSION = "0.1";
    
    @Instance
    MonstersAndDungeons instance;
        
    @SidedProxy(clientSide = "ruukas.monstersanddungeons.client.ClientProxy")
    public static CommonProxy proxy;
    
    public static Logger logger;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	
    	proxy.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    }
}