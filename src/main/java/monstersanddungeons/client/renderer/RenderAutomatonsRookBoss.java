package monstersanddungeons.client.renderer;

import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderAutomatonsRookBoss extends RenderLiving<EntityAutomatonsRookBoss>{

	
	ResourceLocation location = new ResourceLocation(Reference.MODID + ":textures/models/rookAutomaton.png");

	public RenderAutomatonsRookBoss(ModelBase base) {
		super(Minecraft.getMinecraft().getRenderManager(), base,1f);
	}
	
	@Override
	public void doRender(EntityAutomatonsRookBoss entity, double x, double y, double z, float u, float v){
		super.doRender(entity, x, y, z, u, v);
	}

	@Override
	public void bindTexture(ResourceLocation location) {
		  this.renderManager.renderEngine.bindTexture(location);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAutomatonsRookBoss entity) {
		return location;
	}
	

	
}
