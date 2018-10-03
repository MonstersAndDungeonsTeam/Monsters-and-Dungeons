package monstersanddungeons.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWaspArmor - KaijuRizard
 * Created using Tabula 7.0.0
 */
public class ModelWaspHead extends ModelBase {
   
    public ModelRenderer Head;
    public ModelRenderer Eyebase01;
    public ModelRenderer Eyebase02;
    public ModelRenderer Antenna01;
    public ModelRenderer Antenna02;
    public ModelRenderer Fang01;
    public ModelRenderer Fang03;
    public ModelRenderer Eye01;
    public ModelRenderer Eye02;
    public ModelRenderer Antenna01b;
    public ModelRenderer Antenna01c;
    public ModelRenderer Antenna02b;
    public ModelRenderer Antenna02c;
    public ModelRenderer Fang02;
    public ModelRenderer Fang04;
            
    public ModelWaspHead() {   	
        this.textureWidth = 96;
        this.textureHeight = 96;
        this.Eye02 = new ModelRenderer(this, 0, 49);
        this.Eye02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eye02.addBox(4.5F, -6.0F, 0.0F, 1, 3, 4, 0.0F);
        this.Antenna02b = new ModelRenderer(this, 17, 36);
        this.Antenna02b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna02b.addBox(-3.5F, -5.59F, 9.34F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna02b, 0.7285004297824331F, 0.0F, 0.0F);
        this.Antenna02c = new ModelRenderer(this, 17, 36);
        this.Antenna02c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna02c.addBox(-3.5F, -5.07F, 10.57F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna02c, 0.31869712141416456F, 0.0F, 0.0F);
        this.Fang03 = new ModelRenderer(this, 22, 68);
        this.Fang03.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Fang03.addBox(1.5F, -4.0F, -3.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Fang03, 1.0471975511965976F, 0.0F, 0.0F);
        this.Antenna01c = new ModelRenderer(this, 17, 36);
        this.Antenna01c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna01c.addBox(2.5F, -5.07F, 10.57F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna01c, 0.31869712141416456F, 0.0F, 0.0F);
        this.Fang04 = new ModelRenderer(this, 22, 76);
        this.Fang04.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Fang04.addBox(3.4F, -3.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Fang04, 0.0F, 0.6829473363053812F, 0.0F);
        this.Antenna01 = new ModelRenderer(this, 17, 36);
        this.Antenna01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna01.addBox(2.5F, -8.8F, 5.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna01, 0.4553564018453205F, -0.31869712141416456F, 0.0F);
        this.Fang01 = new ModelRenderer(this, 22, 68);
        this.Fang01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Fang01.addBox(-3.5F, -4.0F, -3.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Fang01, 1.0471975511965976F, 0.0F, 0.0F);
        this.Eyebase01 = new ModelRenderer(this, 0, 36);
        this.Eyebase01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eyebase01.addBox(-4.5F, -6.5F, -0.5F, 1, 4, 5, 0.0F);
        this.setRotateAngle(Eyebase01, 0.27314402793711257F, 0.0F, 0.0F);
        this.Eye01 = new ModelRenderer(this, 0, 49);
        this.Eye01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eye01.addBox(-5.5F, -6.0F, 0.0F, 1, 3, 4, 0.0F);
        this.Eyebase02 = new ModelRenderer(this, 0, 36);
        this.Eyebase02.mirror = true;
        this.Eyebase02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Eyebase02.addBox(3.5F, -6.5F, -0.5F, 1, 4, 5, 0.0F);
        this.setRotateAngle(Eyebase02, 0.27314402793711257F, 0.0F, 0.0F);
        this.Fang02 = new ModelRenderer(this, 22, 76);
        this.Fang02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Fang02.addBox(-4.4F, -3.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Fang02, 0.0F, -0.6829473363053812F, 0.0F);
        this.Antenna02 = new ModelRenderer(this, 17, 36);
        this.Antenna02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna02.addBox(-3.5F, -8.8F, 5.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna02, 0.4553564018453205F, 0.31869712141416456F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Antenna01b = new ModelRenderer(this, 17, 36);
        this.Antenna01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Antenna01b.addBox(2.5F, -5.59F, 9.34F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Antenna01b, 0.7285004297824331F, 0.0F, 0.0F);
        
        this.Eyebase02.addChild(this.Eye02);
        this.Antenna02.addChild(this.Antenna02b);
        this.Antenna02b.addChild(this.Antenna02c);
        this.Head.addChild(this.Fang03);
        this.Antenna01b.addChild(this.Antenna01c);
        this.Fang03.addChild(this.Fang04);
        this.Head.addChild(this.Antenna01);
        this.Head.addChild(this.Fang01);
        this.Head.addChild(this.Eyebase01);
        this.Eyebase01.addChild(this.Eye01);
        this.Head.addChild(this.Eyebase02);
        this.Fang01.addChild(this.Fang02);
        this.Head.addChild(this.Antenna02);
        this.Antenna01.addChild(this.Antenna01b);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        this.Head.render(scaleFactor);
    }
    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.Head.rotateAngleX = headPitch * 0.017453292F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
