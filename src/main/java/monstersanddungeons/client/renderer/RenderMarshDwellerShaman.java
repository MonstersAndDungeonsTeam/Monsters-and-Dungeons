package monstersanddungeons.client.renderer;

import monstersanddungeons.client.models.ModelMarshDwellerShaman;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMarshDwellerShaman extends RenderLiving<EntityLiving>{

	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/models/Shaman Swamp Dweller.png");
	protected ModelMarshDwellerShaman modelEntity;
	
	public RenderMarshDwellerShaman(ModelMarshDwellerShaman base) {
		super(Minecraft.getMinecraft().getRenderManager(), base,1f);
		modelEntity = ((ModelMarshDwellerShaman) mainModel);
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
