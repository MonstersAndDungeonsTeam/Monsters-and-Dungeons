package ruukas.monstersanddungeons.block;

import java.util.HashSet;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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
import ruukas.monstersanddungeons.MonstersAndDungeons;

public class AllBlocks {    
    public static final HashSet<Block> blockSet = new HashSet<Block>();
    public static final HashSet<Block> itemBlockSet = new HashSet<Block>();
    public static final HashSet<Item> itemBlockItemsSet = new HashSet<Item>(); 
    
    public static Block HELMET;
    public static Block STATUE;
    
    public static Block ENDERLIGHT;
        
    public static void initBlocks(){
    	System.out.println(itemBlockSet);
		HELMET = new BlockHelmet().setUnlocalizedName("blockHelmet").setRegistryName("helmet");
		STATUE = new BlockStatue().setUnlocalizedName("blockStatue").setRegistryName("statue");
		
		ENDERLIGHT = new BlockEnderTorch().setUnlocalizedName("blockEnderLight").setRegistryName("ender_light");
    
		addBlocks(HELMET);
		addBlocksWithItemBlock(STATUE, ENDERLIGHT);
    }
    
    public static boolean addBlock(Block block){
    	if (block.getRegistryName() == null){
        	Logger logger = MonstersAndDungeons.logger;
    		logger.error(block + " could not be added due to missing registry name.");
    		return false;
    	}else{
    		blockSet.add(block);
    		return true;
    	}
    }
    
    public static void addBlocks(Block... blocks){
    	for(Block block : blocks){
    		addBlock(block);
    	}
    }
    
    public static void addBlocksWithItemBlock(Block... blocks){
    	for(Block block : blocks){
    		if(addBlock(block)){//Adds the block without ItemBlock
    			itemBlockSet.add(block);//Then adds the ItemBlock if successfully added without
    		}
    	}
    }
    
    @EventBusSubscriber(modid = MonstersAndDungeons.MODID)
    private static class BlockRegistryHandler {
    	
    	@SubscribeEvent
    	public static void registerBlocks(Register<Block> event){
    		IForgeRegistry<Block> registry = event.getRegistry();
    		
    		for (Block block : blockSet){
    			registry.register(block);
    		}
    	}
    	
    	@SubscribeEvent
    	public static void registerBlockItems(Register<Item> event){
    		IForgeRegistry<Item> registry = event.getRegistry();
    		
    		for (Block block : itemBlockSet){
    			Item item = new ItemBlock(block).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setRegistryName(block.getRegistryName().toString());
    			registry.register(item);
    			itemBlockItemsSet.add(item);
    		}
    	}
    	
    	@SideOnly(Side.CLIENT)
    	@SubscribeEvent
    	public static void registerItemModels(ModelRegistryEvent event){
    		for (Item item : itemBlockItemsSet){
        		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    		}
    	}
    }
}
