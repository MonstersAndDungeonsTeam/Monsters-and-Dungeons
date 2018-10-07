package monstersanddungeons.proxy;

import monstersanddungeons.client.renderer.tileentity.TileHelmetRenderer;
import monstersanddungeons.tileentity.TileHelmet;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy
{
    
    @Override
    public void preInit()
    {
        ClientRegistry.bindTileEntitySpecialRenderer( TileHelmet.class, new TileHelmetRenderer() );
    }
    
    @Override
    public void init()
    {
    }
}
