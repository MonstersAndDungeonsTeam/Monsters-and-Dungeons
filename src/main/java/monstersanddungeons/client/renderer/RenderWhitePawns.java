package monstersanddungeons.client.renderer;

import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderWhitePawns extends RenderLiving<EntityLiving>{

	
	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/models/whitePawn.png");
	protected ModelBase modelEntity;
	

	public RenderWhitePawns(ModelBase base) {
		super(Minecraft.getMinecraft().getRenderManager(), base,1f);
		modelEntity = ((ModelBase) mainModel);
	}
		
    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks){
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	public void bindTexture(ResourceLocation location) {
		  this.renderManager.renderEngine.bindTexture(location);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) {
		return location;
	}
}
