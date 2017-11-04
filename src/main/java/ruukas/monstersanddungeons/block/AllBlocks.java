package ruukas.monstersanddungeons.block;

import java.util.HashSet;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import ruukas.monstersanddungeons.MonstersAndDungeons;

public class AllBlocks {    
    public static HashSet<Block> blockSet = new HashSet<Block>();
    public static HashSet<Item> itemBlockSet = new HashSet<Item>();
    
    public static Block HELMET;
        
    public static void initBlocks(){
		HELMET = new BlockHelmet().setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("blockHelmet").setRegistryName("helmet");
    
		addBlocks(HELMET);
    }
    
    public static void addBlock(Block block){
    	if (block.getRegistryName() == null){
        	Logger logger = MonstersAndDungeons.logger;
    		logger.error(block + " could not be added due to missing registry name.");
    	}else{
    		blockSet.add(block);
    		//itemBlockSet.add(Item.getItemFromBlock(block));
    	}
    }
    
    public static void addBlocks(Block... blocks){
    	for(Block block : blocks){
    		addBlock(block);
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
    }
}
