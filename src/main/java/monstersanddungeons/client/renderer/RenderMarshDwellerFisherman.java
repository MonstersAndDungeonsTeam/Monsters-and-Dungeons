package monstersanddungeons.client.renderer;

import monstersanddungeons.client.models.ModelMarshDweller;
import monstersanddungeons.client.models.ModelMarshDwellerFisherman;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMarshDwellerFisherman extends RenderLiving<EntityLiving>{

	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/models/Fisherman Swamp Dweller.png");
	protected ModelMarshDwellerFisherman modelEntity;
	
	public RenderMarshDwellerFisherman(ModelMarshDwellerFisherman base) {
		super(Minecraft.getMinecraft().getRenderManager(), base,1f);
		modelEntity = ((ModelMarshDwellerFisherman) mainModel);
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
