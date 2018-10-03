package monstersanddungeons;

import org.apache.logging.log4j.Logger;

import monstersanddungeons.block.AllBlocks;
import monstersanddungeons.item.AllItems;
import monstersanddungeons.proxy.IProxy;
import monstersanddungeons.tileentity.AllTileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod( name = MonstersAndDungeons.NAME, modid = MonstersAndDungeons.MODID, version = MonstersAndDungeons.VERSION )
public class MonstersAndDungeons
{
    public static final String NAME = "Monsters and Dungeons";
    public static final String MODID = "monstersanddungeons";
    public static final String VERSION = "0.1";
    
    public static CreativeTabs CREATIVE_TAB;
    
    @Instance
    MonstersAndDungeons instance;
    
    @SidedProxy( clientSide = "monstersanddungeons.proxy.ClientProxy", serverSide = "monstersanddungeons.proxy.ServerProxy" )
    public static IProxy proxy;
    
    public static Logger logger;
    
    @EventHandler
    public void preInit( FMLPreInitializationEvent event )
    {
        logger = event.getModLog();
        
        MonstersAndDungeons.CREATIVE_TAB = new CreativeTabs( MonstersAndDungeons.MODID ) {
            @Override
            public ItemStack createIcon()
            {
                return new ItemStack( AllBlocks.ENDERLIGHT );
            }
        };
        
        AllBlocks.initBlocks();
        AllItems.initItems();
        AllTileEntities.initTileEntities();
        
        proxy.preInit();
    }
    
    @EventHandler
    public void init( FMLInitializationEvent event )
    {
        proxy.init();
    }
}