package monstersanddungeons.tileentity;




import monstersanddungeons.tileentity.miscellaneous.TileEntityMonsterStatue;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class MaDTileEntityHandler {


	public static void register()
	{
		
		GameRegistry.registerTileEntity(TileEntityMonsterStatue.class, "TileEntityMonsterStatue");
	}

}
