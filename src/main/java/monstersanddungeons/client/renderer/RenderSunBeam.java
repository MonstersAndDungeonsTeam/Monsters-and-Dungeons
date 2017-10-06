package monstersanddungeons.client.renderer;

import monstersanddungeons.entity.miscellaneous.EntitySunBeam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class RenderSunBeam extends Render<EntitySunBeam>{

	float xSize;
	boolean changeToRed = false;

	public RenderSunBeam() 
	{
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(EntitySunBeam entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		Vec3d vec = Minecraft.getMinecraft().thePlayer.getLookVec();
		BlockPos pos = Minecraft.getMinecraft().thePlayer.getPosition();
		
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();

		vertexbuffer.begin(1, DefaultVertexFormats.POSITION_NORMAL);
		
		vertexbuffer.pos(vec.xCoord, vec.yCoord, vec.zCoord ).normal(0, 1, 0).endVertex();;
		vertexbuffer.pos(vec.xCoord *3, vec.yCoord, vec.zCoord * 3).normal(0, 1, 0).endVertex();
		
		tessellator.draw();
		//drawRectangle(vec.xCoord, 0.5f, vec.zCoord, 0, 0, 0, 255f,  255f, 255f, 1f);	
		
		GlStateManager.popMatrix();
		
		/*
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);

		drawRectangle(xSize, 30f, xSize, 0, -15, 0, 255f,  255f, 255f, 1f);	

		if(changeToRed)
		{
			drawRectangle(xSize, 30f, xSize, 0.8, -15, 0,  255f, 90f, 0f, 1f);	
			drawRectangle(xSize, 30f, xSize, -0.8, -15, 0,  255f, 90f, 0f, 1f);	
			drawRectangle(xSize, 30f, xSize,   0, -15,  0.8,  255f, 90f, 0f, 1f);	
			drawRectangle(xSize, 30f, xSize,   0, -15, -0.8, 255f, 90f, 0f, 1f);	
		}

		if(xSize < 0.5)
		{
			xSize += 0.01;
		}else
		{
			changeToRed = true;
		}

		GlStateManager.popMatrix();
		*/

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void drawRectangle(double lengthX, double lengthY, double lengthZ, double posX, double posY, double posZ, float colourX, float colourY, float colourZ, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(posX, posY, posZ);

		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

		GlStateManager.color(colourX, colourY, colourZ, alpha);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();

		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_NORMAL);

		vertexbuffer.pos(0, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();

		//EAST
		vertexbuffer.pos(0, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, 0, 0).normal(0, 0, 1).endVertex();


		//South outside face
		vertexbuffer.pos(0, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, lengthY, 0).normal(0, 0, 1).endVertex();


		//East Outside face
		vertexbuffer.pos(0, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, 0, lengthZ).normal(0, 0, 1).endVertex();


		//up outside face
		vertexbuffer.pos(0, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, 0, 0).normal(0, 0, 1).endVertex();


		//up inside face
		vertexbuffer.pos(0, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0f, 0, lengthZ).normal(0, 0, 1).endVertex();



		//West Inside

		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();


		//West Outside
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, 0).normal(0, 0, 1).endVertex();


		// North outside
		vertexbuffer.pos(0, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();


		//North inside
		vertexbuffer.pos(lengthX, 0, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, 0, lengthZ).normal(0, 0, 1).endVertex();


		//bottom inside
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, 0).normal(0, 0, 1).endVertex();


		vertexbuffer.pos(0, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, 0).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(lengthX, lengthY, lengthZ).normal(0, 0, 1).endVertex();
		vertexbuffer.pos(0, lengthY, lengthZ).normal(0, 0, 1).endVertex();

		tessellator.draw();

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}


	@Override
	protected ResourceLocation getEntityTexture(EntitySunBeam entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
