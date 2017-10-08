package monstersanddungeons.client.renderer;

import monstersanddungeons.entity.miscellaneous.EntitySafeZone;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderSafeZone extends Render<EntitySafeZone>{

	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/entities/magic_circle.png");

	public RenderSafeZone() {
		super(Minecraft.getMinecraft().getRenderManager());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doRender(EntitySafeZone entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate(x - 1.5, y + 0.2, z - 1.5);
		GlStateManager.scale(3f, 3f, 3f);
		
		this.bindEntityTexture(entity);
       
	
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		
		vertexbuffer.pos(0, 0, 1).tex(0, 1).color(1f, 1f, 1f, (entity.isPlayerNearby() ? 6f : 0.15f)).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(1, 0, 1).tex(1, 1).color(1f, 1f, 1f, (entity.isPlayerNearby() ? 6f : 0.15f)).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(1, 0, 0).tex(1, 0).color(1f, 1f, 1f, (entity.isPlayerNearby() ? 6f : 0.15f)).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, 0, 0).tex(0, 0).color(1f, 1f, 1f, (entity.isPlayerNearby() ? 6f : 0.15f)).normal(0, 0, 1).endVertex();
		
		tessellator.draw();
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySafeZone entity) {
		// TODO Auto-generated method stub
		return location;
	}

}
