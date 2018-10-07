package monstersanddungeons.block;

import java.util.HashSet;

import org.apache.logging.log4j.Logger;

import monstersanddungeons.MonstersAndDungeons;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class AllBlocks
{
    public static final HashSet<Block> blockSet = new HashSet<Block>();
    public static final HashSet<Block> itemBlockSet = new HashSet<Block>();
    public static final HashSet<Item> itemBlockItemsSet = new HashSet<Item>();
    
    public static Block HELMET;
    
    public static Block ENDERLIGHT;
    
    public static void initBlocks()
    {
        HELMET = constructBlock( new BlockHelmet(), "helmet", false );
        ENDERLIGHT = constructBlock( new BlockEnderTorch(), "enderLight", true );
    }
    
    public static Block constructBlock( Block block, String name, boolean shouldAddItemBlock )
    {
        block.setTranslationKey( name );
        
        String registryName = MonstersAndDungeons.MODID + ":";
        
        for ( int i = 0 ; i < name.length() ; i++ )
        {
            char c = name.charAt( i );
            if ( Character.isUpperCase( c ) )
            {
                registryName += "_" + Character.toLowerCase( c );
            }
            else
            {
                registryName += c;
            }
        }
        
        block.setRegistryName( registryName );
        
        block.setCreativeTab( MonstersAndDungeons.CREATIVE_TAB );
        
        boolean addedWithoutErrors = addBlock( block );
        if ( addedWithoutErrors && shouldAddItemBlock )
        {
            itemBlockSet.add( block );
        }
        
        return block;
    }
    
    public static boolean addBlock( Block block )
    {
        if ( block.getRegistryName() == null )
        {
            Logger logger = MonstersAndDungeons.logger;
            logger.error( block + " could not be added due to missing registry name." );
            return false;
        }
        else
        {
            blockSet.add( block );
            return true;
        }
    }
    
    @EventBusSubscriber( modid = MonstersAndDungeons.MODID )
    private static class BlockRegistryHandler
    {
        
        @SubscribeEvent
        public static void registerBlocks( Register<Block> event )
        {
            IForgeRegistry<Block> registry = event.getRegistry();
            
            for ( Block block : blockSet )
            {
                registry.register( block );
            }
        }
        
        @SubscribeEvent
        public static void registerBlockItems( Register<Item> event )
        {
            IForgeRegistry<Item> registry = event.getRegistry();
            
            for ( Block block : itemBlockSet )
            {
                Item item = new ItemBlock( block ).setRegistryName( block.getRegistryName() ).setCreativeTab( MonstersAndDungeons.CREATIVE_TAB );
                registry.register( item );
                itemBlockItemsSet.add( item );
            }
        }
        
        @SideOnly( Side.CLIENT )
        @SubscribeEvent
        public static void registerItemModels( ModelRegistryEvent event )
        {
            for ( Item item : itemBlockItemsSet )
            {
                ModelLoader.setCustomModelResourceLocation( item, 0, new ModelResourceLocation( item.getRegistryName().toString(), "inventory" ) );
            }
        }
    }
}
