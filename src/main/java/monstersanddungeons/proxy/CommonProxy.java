package monstersanddungeons.proxy;

import java.io.File;
import java.io.IOException;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.client.gui.MaDGuiHandler;
import monstersanddungeons.entity.MaDEntityHandler;
import monstersanddungeons.events.CommonEventHandler;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.sound.MaDSoundsHandler;
import monstersanddungeons.tileentity.MaDTileEntityHandler;
import monstersanddungeons.world.MaDWorldGenerationHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class CommonProxy {

	public static File cfgDir;

	public void preInit(FMLPreInitializationEvent event) {
		CommonProxy.cfgDir = event.getModConfigurationDirectory();

		try {
			MaDBlocksHandler.genBlocks(cfgDir.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		MaDPacketHandler.init();
		MaDTileEntityHandler.register();
		MaDEntityHandler.registerEntities();
		MaDEntityHandler.addSpawns();
		MaDSoundsHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(MonstersAndDungeons.instance, new MaDGuiHandler());
	}

	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new MaDWorldGenerationHandler(), 9000);
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}

	public void RegisterRenders() {

	}

	public ModelBiped getArmorModel(int id) {
		return null;
	}

}
