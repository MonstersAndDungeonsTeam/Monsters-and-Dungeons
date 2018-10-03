package monstersanddungeons.tileentity;

import monstersanddungeons.MonstersAndDungeons;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AllTileEntities
{
    public static void initTileEntities()
    {
        GameRegistry.registerTileEntity( TileHelmet.class, MonstersAndDungeons.MODID + ":tile_helmet" );
        GameRegistry.registerTileEntity( TileStatue.class, MonstersAndDungeons.MODID + ":tile_statue" );
    }
}
