package monstersanddungeons.item;

import java.util.HashSet;

import org.apache.logging.log4j.Logger;

import monstersanddungeons.MonstersAndDungeons;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class AllItems
{
    public static Item WASP_HEAD;
    public static Item WASP_CHEST;
    public static Item WASP_LEGS;
    public static Item WASP_BOOTS;
    
    public static HashSet<Item> itemSet = new HashSet<Item>();
    
    public static void initItems()
    {
        WASP_HEAD = constructItem( new ItemArmorWasp( ArmorMaterial.GOLD, 0, EntityEquipmentSlot.HEAD ), "waspHelmet" );
        WASP_CHEST = constructItem( new ItemArmorWasp( ArmorMaterial.GOLD, 0, EntityEquipmentSlot.CHEST ), "waspChestplate" );
        WASP_LEGS = constructItem( new ItemArmorWasp( ArmorMaterial.GOLD, 0, EntityEquipmentSlot.LEGS ), "waspLeggings" );
        WASP_BOOTS = constructItem( new ItemArmorWasp( ArmorMaterial.GOLD, 0, EntityEquipmentSlot.FEET ), "waspBoots" );
    }
    
    public static Item constructItem( Item item, String name )
    {
        item.setTranslationKey( name );
        
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
        item.setRegistryName( registryName );
        
        item.setCreativeTab( MonstersAndDungeons.CREATIVE_TAB );
        
        addItem( item );
        
        return item;
    }
    
    public static void addItem( Item item )
    {
        if ( item.getRegistryName() == null )
        {
            Logger logger = MonstersAndDungeons.logger;
            logger.error( item + " could not be added due to missing registry name." );
        }
        else
        {
            itemSet.add( item );
        }
    }
    
    @EventBusSubscriber( modid = MonstersAndDungeons.MODID )
    private static class ItemRegistryHandler
    {
        
        @SubscribeEvent
        public static void registerItems( Register<Item> event )
        {
            IForgeRegistry<Item> registry = event.getRegistry();
            
            for ( Item item : itemSet )
            {
                registry.register( item );
            }
        }
        
        @SideOnly( Side.CLIENT )
        @SubscribeEvent
        public static void registerItemModels( ModelRegistryEvent event )
        {
            for ( Item item : itemSet )
            {
                ModelLoader.setCustomModelResourceLocation( item, 0, new ModelResourceLocation( item.getRegistryName().toString(), "inventory" ) );
            }
        }
    }
}
