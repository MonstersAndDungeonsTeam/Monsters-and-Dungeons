package monstersanddungeons.client.renderer.tileentity;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.client.model.ModelWaspHead;
import monstersanddungeons.tileentity.TileHelmet;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
public class TileHelmetRenderer extends TileEntitySpecialRenderer<TileHelmet>
{
    private final ModelWaspHead helmet = new ModelWaspHead();
    public static TileHelmetRenderer instance;
    
    public void render( TileHelmet te, double x, double y, double z, float partialTicks, int destroyStage, float alpha )
    {
        EnumFacing enumfacing = EnumFacing.byIndex( te.getBlockMetadata() & 7 );
        this.renderHelmet( (float) x, (float) y, (float) z, enumfacing, (float) (te.getHelmetRotation() * 360) / 16.0F, te.getItemStack(), destroyStage );
    }
    
    public void setRendererDispatcher( TileEntityRendererDispatcher rendererDispatcherIn )
    {
        super.setRendererDispatcher( rendererDispatcherIn );
        instance = this;
    }
    
    public void renderHelmet( float x, float y, float z, EnumFacing facing, float rotationIn, ItemStack helmet, int destroyStage )
    {
        ModelBase modelbase = this.helmet;
        
        if ( destroyStage >= 0 )
        {
            this.bindTexture( DESTROY_STAGES[destroyStage] );
            GlStateManager.matrixMode( 5890 );
            GlStateManager.pushMatrix();
            GlStateManager.scale( 4.0F, 2.0F, 1.0F );
            GlStateManager.translate( 0.0625F, 0.0625F, 0.0625F );
            GlStateManager.matrixMode( 5888 );
        }
        else
        {
            
            this.bindTexture( new ResourceLocation( MonstersAndDungeons.MODID + ":textures/models/armor/wasp.png" ) );
        }
        
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        
        if ( facing == EnumFacing.UP )
        {
            GlStateManager.translate( x + 0.5F, y, z + 0.5F );
        }
        else
        {
            switch ( facing )
            {
                case NORTH:
                    GlStateManager.translate( x + 0.5F, y + 0.25F, z + 0.74F );
                    break;
                case SOUTH:
                    GlStateManager.translate( x + 0.5F, y + 0.25F, z + 0.26F );
                    rotationIn = 180.0F;
                    break;
                case WEST:
                    GlStateManager.translate( x + 0.74F, y + 0.25F, z + 0.5F );
                    rotationIn = 270.0F;
                    break;
                case EAST:
                default:
                    GlStateManager.translate( x + 0.26F, y + 0.25F, z + 0.5F );
                    rotationIn = 90.0F;
            }
        }
        
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale( -1.0F, -1.0F, 1.0F );
        GlStateManager.enableAlpha();
        
        modelbase.render( (Entity) null, 0.0F, 0.0F, 0.0F, rotationIn, 0.0F, 0.0625F );
        GlStateManager.popMatrix();
        
        if ( destroyStage >= 0 )
        {
            GlStateManager.matrixMode( 5890 );
            GlStateManager.popMatrix();
            GlStateManager.matrixMode( 5888 );
        }
    }
}