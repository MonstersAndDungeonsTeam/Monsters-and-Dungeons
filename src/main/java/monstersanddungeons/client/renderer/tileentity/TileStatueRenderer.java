package monstersanddungeons.client.renderer.tileentity;

import monstersanddungeons.tileentity.TileStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
public class TileStatueRenderer extends TileEntitySpecialRenderer<TileStatue>
{
    public static TileStatueRenderer instance;
    
    public void render( TileStatue te, double x, double y, double z, float partialTicks, int destroyStage, float alpha )
    {
        EnumFacing enumfacing = EnumFacing.byIndex( te.getBlockMetadata() & 7 );
        this.renderHelmet( (float) x, (float) y, (float) z, enumfacing, (float) (te.getHelmetRotation() * 360) / 16.0F, te.getModel(), destroyStage );
    }
    
    public void setRendererDispatcher( TileEntityRendererDispatcher rendererDispatcherIn )
    {
        super.setRendererDispatcher( rendererDispatcherIn );
        instance = this;
    }
    
    public void renderHelmet( float x, float y, float z, EnumFacing facing, float rotationIn, ModelBase model, int destroyStage )
    {
        ModelBiped playerModel = (ModelBiped) model;
        GlStateManager.pushMatrix();
        GlStateManager.translate( x + 0.5f, y + 3.0f, z + 0.5f );
        // GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        this.bindTexture( DefaultPlayerSkin.getDefaultSkinLegacy() );
        playerModel.render( Minecraft.getMinecraft().player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f );
        // GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        GlStateManager.popMatrix();
    }
}