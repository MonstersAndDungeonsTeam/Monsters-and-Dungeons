package ruukas.monstersanddungeons.client;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import ruukas.monstersanddungeons.CommonProxy;
import ruukas.monstersanddungeons.client.renderer.tileentity.TileHelmetRenderer;
import ruukas.monstersanddungeons.tileentity.TileHelmet;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		ClientRegistry.bindTileEntitySpecialRenderer(TileHelmet.class, new TileHelmetRenderer());;
	}

	@Override
	public void init() {
		super.init();
	}

}
