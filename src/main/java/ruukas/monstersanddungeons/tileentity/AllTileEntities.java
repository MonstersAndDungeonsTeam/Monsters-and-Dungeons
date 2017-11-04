package ruukas.monstersanddungeons.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ruukas.monstersanddungeons.MonstersAndDungeons;

public class AllTileEntities {               
    public static void initTileEntities(){
		GameRegistry.registerTileEntity(TileHelmet.class, MonstersAndDungeons.MODID + ":tile_helmet");
		GameRegistry.registerTileEntity(TileStatue.class, MonstersAndDungeons.MODID + ":tile_statue");
    }
}
