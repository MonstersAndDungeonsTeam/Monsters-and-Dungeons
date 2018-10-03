package monstersanddungeons.proxy;

import monstersanddungeons.client.renderer.tileentity.TileHelmetRenderer;
import monstersanddungeons.client.renderer.tileentity.TileStatueRenderer;
import monstersanddungeons.tileentity.TileHelmet;
import monstersanddungeons.tileentity.TileStatue;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {

	@Override
	public void preInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileHelmet.class, new TileHelmetRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileStatue.class, new TileStatueRenderer());
	}

	@Override
	public void init() {		
	}
}
