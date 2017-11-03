package ruukas.monstersanddungeons.item;

import java.util.HashSet;

import org.apache.logging.log4j.Logger;

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
import ruukas.monstersanddungeons.MonstersAndDungeons;

public class AllItems {
    public static Item WASP_HEAD;
    public static Item WASP_CHEST;
    public static Item WASP_LEGS;
    public static Item WASP_BOOTS;
    
    public static HashSet<Item> itemSet;
        
    public static void initItems(){
		WASP_HEAD = new ItemArmorWasp(ArmorMaterial.GOLD, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("chestplateWasp").setRegistryName("wasp_helmet");
		WASP_CHEST = new ItemArmorWasp(ArmorMaterial.GOLD, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("chestplateWasp").setRegistryName("wasp_chest");
		WASP_LEGS = new ItemArmorWasp(ArmorMaterial.GOLD, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("leggingsWasp").setRegistryName("wasp_leggings");
		WASP_BOOTS = new ItemArmorWasp(ArmorMaterial.GOLD, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("bootsWasp").setRegistryName("wasp_boots");
    
		addItems(WASP_HEAD, WASP_CHEST, WASP_LEGS, WASP_BOOTS);
    }
    
    public static void addItem(Item item){
    	if (item.getRegistryName() == null){
        	Logger logger = MonstersAndDungeons.logger;
    		logger.error(item + " could not be added due to missing registry name.");
    	}else{
    		itemSet.add(item);
    	}
    }
    
    public static void addItems(Item... items){
    	for(Item item : items){
    		addItem(item);
    	}
    }
    
    @EventBusSubscriber(modid = MonstersAndDungeons.MODID)
    private static class ItemRegistryHandler {
    	
    	@SubscribeEvent
    	public static void registerItems(Register<Item> event){
    		IForgeRegistry<Item> registry = event.getRegistry();
    		
    		for (Item item : itemSet){
    			registry.register(item);
    		}
    	}
    	
    	@SideOnly(Side.CLIENT)
    	@SubscribeEvent
    	public static void registerItemModels(ModelRegistryEvent event){
    		for (Item item : itemSet){
        		setModelLocationForItem(item);
    		}
    	}
    	
    	@SideOnly(Side.CLIENT)
    	private static void setModelLocationForItem(Item item){
    		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    	}
    }
}
