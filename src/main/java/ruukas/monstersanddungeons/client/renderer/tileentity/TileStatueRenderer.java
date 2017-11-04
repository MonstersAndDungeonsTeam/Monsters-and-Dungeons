package ruukas.monstersanddungeons.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ruukas.monstersanddungeons.tileentity.TileStatue;

@SideOnly(Side.CLIENT)
public class TileStatueRenderer extends TileEntitySpecialRenderer<TileStatue>
{
    public static TileStatueRenderer instance;

    public void render(TileStatue te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
        this.renderHelmet((float)x, (float)y, (float)z, enumfacing, (float)(te.getHelmetRotation() * 360) / 16.0F, te.getModel(), destroyStage);
    }

    public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super.setRendererDispatcher(rendererDispatcherIn);
        instance = this;
    }

    public void renderHelmet(float x, float y, float z, EnumFacing facing, float rotationIn, ModelBase model, int destroyStage)
    {  	
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	GlStateManager.scale(1.0F, 1.0F, -1.0F);
    	//model.render(Minecraft.getMinecraft().player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2F);
    	//GlStateManager.rotate(180, 0.0f, 1.0f, 0.0f);
    	Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(Minecraft.getMinecraft().player).doRender(Minecraft.getMinecraft().player, x, y, z, 0, 0.0F);
    	GlStateManager.popMatrix();
    }
}