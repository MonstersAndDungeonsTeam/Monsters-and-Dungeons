package ruukas.monstersanddungeons.client;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import ruukas.monstersanddungeons.CommonProxy;
import ruukas.monstersanddungeons.client.renderer.tileentity.TileHelmetRenderer;
import ruukas.monstersanddungeons.client.renderer.tileentity.TileStatueRenderer;
import ruukas.monstersanddungeons.tileentity.TileHelmet;
import ruukas.monstersanddungeons.tileentity.TileStatue;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		ClientRegistry.bindTileEntitySpecialRenderer(TileHelmet.class, new TileHelmetRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileStatue.class, new TileStatueRenderer());
	}

	@Override
	public void init() {
		super.init();
		
		/*Map<String, RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();
		RenderPlayer steve = skinMap.get("default");
		steve.addLayer(new LayerDungeonCape(steve));
		
		RenderPlayer slim = skinMap.get("slim");
		slim.addLayer(new LayerDungeonCape(slim));*/

	}
}
