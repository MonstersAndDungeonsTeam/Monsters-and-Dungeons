package monstersanddungeons.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * ModelWaspArmor - KaijuRizard Created using Tabula 7.0.0
 */
public class ModelWaspArmor extends ModelBiped
{
    public ModelRenderer Arm01;
    public ModelRenderer Leg01;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer Leg02;
    public ModelRenderer Arm02;
    public ModelRenderer Shoes01;
    public ModelRenderer Shoes02;
    public ModelRenderer Shouler01;
    public ModelRenderer Elbow01;
    public ModelRenderer ShoulderSpike01;
    public ModelRenderer ShoulderSpike01b;
    public ModelRenderer ElbowSpike01;
    public ModelRenderer ElbowSpike02;
    public ModelRenderer ElbowSpike03;
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
    public ModelRenderer Back01;
    public ModelRenderer Back02;
    
    public ModelRenderer Shouler02;
    public ModelRenderer Elbow02;
    public ModelRenderer ShoulderSpike02;
    public ModelRenderer ShoulderSpike02b;
    public ModelRenderer ElbowSpike04;
    public ModelRenderer ElbowSpike05;
    public ModelRenderer ElbowSpike06;
    
    public EntityEquipmentSlot slot;
    
    public ModelWaspWings wings;
    
    public ModelWaspArmor(EntityEquipmentSlot slot) {
        super( 1f );
        
        this.wings = new ModelWaspWings();
        
        this.slot = slot;
        
        this.textureWidth = 96;
        this.textureHeight = 96;
        this.ShoulderSpike01 = new ModelRenderer( this, 49, 38 );
        this.ShoulderSpike01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ShoulderSpike01.addBox( -2.3F, -4.5F, -1.0F, 2, 3, 2, 0.0F );
        this.setRotateAngle( ShoulderSpike01, 0.0F, 0.0F, -0.5098106745075436F );
        this.Elbow01 = new ModelRenderer( this, 41, 75 );
        this.Elbow01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Elbow01.addBox( -3.5F, 3.0F, -2.5F, 5, 5, 5, 0.0F );
        this.Eye02 = new ModelRenderer( this, 0, 49 );
        this.Eye02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Eye02.addBox( 4.5F, -6.0F, 0.0F, 1, 3, 4, 0.0F );
        this.Shouler02 = new ModelRenderer( this, 49, 53 );
        this.Shouler02.mirror = true;
        this.Shouler02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Shouler02.addBox( -1.5F, -2.5F, -2.5F, 5, 4, 5, 0.0F );
        this.Antenna02b = new ModelRenderer( this, 17, 36 );
        this.Antenna02b.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna02b.addBox( -3.5F, -5.59F, 9.34F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna02b, 0.7285004297824331F, 0.0F, 0.0F );
        this.Antenna02c = new ModelRenderer( this, 17, 36 );
        this.Antenna02c.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna02c.addBox( -3.5F, -5.07F, 10.57F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna02c, 0.31869712141416456F, 0.0F, 0.0F );
        this.Shoes02 = new ModelRenderer( this, 73, 76 );
        this.Shoes02.setRotationPoint( -1.9F, 12.0F, 0.1F );
        this.Shoes02.addBox( -2.5F, 6.0F, -2.5F, 5, 6, 5, 0.0F );
        this.Fang03 = new ModelRenderer( this, 22, 68 );
        this.Fang03.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Fang03.addBox( 1.5F, -4.0F, -3.0F, 2, 2, 3, 0.0F );
        this.setRotateAngle( Fang03, 1.0471975511965976F, 0.0F, 0.0F );
        this.Back02 = new ModelRenderer( this, 31, 49 );
        this.Back02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Back02.addBox( -2.0F, 1.0F, 2.5F, 4, 6, 1, 0.0F );
        this.Antenna01c = new ModelRenderer( this, 17, 36 );
        this.Antenna01c.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna01c.addBox( 2.5F, -5.07F, 10.57F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna01c, 0.31869712141416456F, 0.0F, 0.0F );
        this.Arm01 = new ModelRenderer( this, 40, 16 );
        this.Arm01.setRotationPoint( -5.0F, 2.0F, 0.0F );
        this.Arm01.addBox( -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F );
        this.setRotateAngle( Arm01, 0.0F, 0.0F, 0.10000736613927509F );
        this.Fang04 = new ModelRenderer( this, 22, 76 );
        this.Fang04.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Fang04.addBox( 3.4F, -3.5F, -3.0F, 1, 1, 3, 0.0F );
        this.setRotateAngle( Fang04, 0.0F, 0.6829473363053812F, 0.0F );
        this.Back01 = new ModelRenderer( this, 14, 49 );
        this.Back01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Back01.addBox( -3.0F, 0.0F, 2.0F, 6, 8, 1, 0.0F );
        this.Antenna01 = new ModelRenderer( this, 17, 36 );
        this.Antenna01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna01.addBox( 2.5F, -8.8F, 5.0F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna01, 0.4553564018453205F, -0.31869712141416456F, 0.0F );
        this.ElbowSpike06 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike06.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike06.addBox( 7.1F, -0.1F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike06, 0.0F, 0.0F, 0.9651670763528643F );
        this.ShoulderSpike02b = new ModelRenderer( this, 65, 38 );
        this.ShoulderSpike02b.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ShoulderSpike02b.addBox( 2.9F, -6.2F, -0.5F, 1, 4, 1, 0.0F );
        this.setRotateAngle( ShoulderSpike02b, 0.0F, 0.0F, -0.5464625887994246F );
        this.Arm02 = new ModelRenderer( this, 40, 16 );
        this.Arm02.setRotationPoint( 5.0F, 2.0F, 0.0F );
        this.Arm02.addBox( -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F );
        this.setRotateAngle( Arm02, 0.0F, 0.0F, -0.10000736613927509F );
        this.ElbowSpike01 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike01.addBox( -5.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike01, 0.0F, 0.0F, -0.9651670763528643F );
        this.Fang01 = new ModelRenderer( this, 22, 68 );
        this.Fang01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Fang01.addBox( -3.5F, -4.0F, -3.0F, 2, 2, 3, 0.0F );
        this.setRotateAngle( Fang01, 1.0471975511965976F, 0.0F, 0.0F );
        this.ElbowSpike04 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike04.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike04.addBox( 4.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike04, 0.0F, 0.0F, 0.9651670763528643F );
        this.Shouler01 = new ModelRenderer( this, 49, 53 );
        this.Shouler01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Shouler01.addBox( -3.5F, -2.5F, -2.5F, 5, 4, 5, 0.0F );
        this.Eyebase01 = new ModelRenderer( this, 0, 36 );
        this.Eyebase01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Eyebase01.addBox( -4.5F, -6.5F, -0.5F, 1, 4, 5, 0.0F );
        this.setRotateAngle( Eyebase01, 0.27314402793711257F, 0.0F, 0.0F );
        this.Eye01 = new ModelRenderer( this, 0, 49 );
        this.Eye01.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Eye01.addBox( -5.5F, -6.0F, 0.0F, 1, 3, 4, 0.0F );
        this.Elbow02 = new ModelRenderer( this, 41, 75 );
        this.Elbow02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Elbow02.addBox( -1.5F, 3.0F, -2.5F, 5, 5, 5, 0.0F );
        this.Shoes01 = new ModelRenderer( this, 73, 76 );
        this.Shoes01.setRotationPoint( 1.9F, 12.0F, 0.1F );
        this.Shoes01.addBox( -2.5F, 6.0F, -2.5F, 5, 6, 5, 0.0F );
        this.ElbowSpike03 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike03.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike03.addBox( -8.1F, -0.1F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike03, 0.0F, 0.0F, -0.9651670763528643F );
        this.ElbowSpike02 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike02.addBox( -6.8F, -1.1F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike02, 0.0F, 0.0F, -0.9651670763528643F );
        this.Eyebase02 = new ModelRenderer( this, 0, 36 );
        this.Eyebase02.mirror = true;
        this.Eyebase02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Eyebase02.addBox( 3.5F, -6.5F, -0.5F, 1, 4, 5, 0.0F );
        this.setRotateAngle( Eyebase02, 0.27314402793711257F, 0.0F, 0.0F );
        this.Fang02 = new ModelRenderer( this, 22, 76 );
        this.Fang02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Fang02.addBox( -4.4F, -3.5F, -3.0F, 1, 1, 3, 0.0F );
        this.setRotateAngle( Fang02, 0.0F, -0.6829473363053812F, 0.0F );
        this.Antenna02 = new ModelRenderer( this, 17, 36 );
        this.Antenna02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna02.addBox( -3.5F, -8.8F, 5.0F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna02, 0.4553564018453205F, 0.31869712141416456F, 0.0F );
        this.Body = new ModelRenderer( this, 16, 16 );
        this.Body.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Body.addBox( -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F );
        this.Head = new ModelRenderer( this, 0, 0 );
        this.Head.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Head.addBox( -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F );
        this.Antenna01b = new ModelRenderer( this, 17, 36 );
        this.Antenna01b.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.Antenna01b.addBox( 2.5F, -5.59F, 9.34F, 1, 3, 1, 0.0F );
        this.setRotateAngle( Antenna01b, 0.7285004297824331F, 0.0F, 0.0F );
        this.Leg02 = new ModelRenderer( this, 0, 16 );
        this.Leg02.mirror = true;
        this.Leg02.setRotationPoint( 1.9F, 12.0F, 0.1F );
        this.Leg02.addBox( -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F );
        this.ElbowSpike05 = new ModelRenderer( this, 41, 70 );
        this.ElbowSpike05.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ElbowSpike05.addBox( 5.8F, -1.1F, -0.5F, 1, 2, 1, 0.0F );
        this.setRotateAngle( ElbowSpike05, 0.0F, 0.0F, 0.9651670763528643F );
        this.Leg01 = new ModelRenderer( this, 0, 16 );
        this.Leg01.setRotationPoint( -1.9F, 12.0F, 0.1F );
        this.Leg01.addBox( -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F );
        this.ShoulderSpike01b = new ModelRenderer( this, 65, 38 );
        this.ShoulderSpike01b.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ShoulderSpike01b.addBox( -3.9F, -6.2F, -0.5F, 1, 4, 1, 0.0F );
        this.setRotateAngle( ShoulderSpike01b, 0.0F, 0.0F, 0.5464625887994246F );
        this.ShoulderSpike02 = new ModelRenderer( this, 49, 38 );
        this.ShoulderSpike02.setRotationPoint( 0.0F, 0.0F, 0.0F );
        this.ShoulderSpike02.addBox( 0.3F, -4.5F, -1.0F, 2, 3, 2, 0.0F );
        this.setRotateAngle( ShoulderSpike02, 0.0F, 0.0F, 0.5098106745075436F );
        
        this.Shouler01.addChild( this.ShoulderSpike01 );
        this.Arm01.addChild( this.Elbow01 );
        this.Eyebase02.addChild( this.Eye02 );
        this.Arm02.addChild( this.Shouler02 );
        this.Antenna02.addChild( this.Antenna02b );
        this.Antenna02b.addChild( this.Antenna02c );
        this.Head.addChild( this.Fang03 );
        this.Back01.addChild( this.Back02 );
        this.Antenna01b.addChild( this.Antenna01c );
        this.Back01.addChild( wings.wingRight );
        this.Fang03.addChild( this.Fang04 );
        this.Body.addChild( this.Back01 );
        this.Head.addChild( this.Antenna01 );
        this.Elbow02.addChild( this.ElbowSpike06 );
        this.ShoulderSpike02.addChild( this.ShoulderSpike02b );
        this.Elbow01.addChild( this.ElbowSpike01 );
        this.Head.addChild( this.Fang01 );
        this.Elbow02.addChild( this.ElbowSpike04 );
        this.Back01.addChild( wings.wingLeft );
        this.Arm01.addChild( this.Shouler01 );
        this.Head.addChild( this.Eyebase01 );
        this.Eyebase01.addChild( this.Eye01 );
        this.Arm02.addChild( this.Elbow02 );
        this.Elbow01.addChild( this.ElbowSpike03 );
        this.Elbow01.addChild( this.ElbowSpike02 );
        this.Head.addChild( this.Eyebase02 );
        this.Fang01.addChild( this.Fang02 );
        this.Head.addChild( this.Antenna02 );
        this.Antenna01.addChild( this.Antenna01b );
        this.Elbow02.addChild( this.ElbowSpike05 );
        this.ShoulderSpike01.addChild( this.ShoulderSpike01b );
        this.Shouler02.addChild( this.ShoulderSpike02 );
        
        /*
         * this.bipedHead.addChild(this.Head);
         * 
         * this.bipedRightArm.addChild(this.Arm01); this.bipedLeftArm.addChild(this.Arm02);
         * 
         * this.bipedBody.addChild(this.Body);
         * 
         * this.bipedRightLeg.addChild(this.Leg01); this.bipedLeftLeg.addChild(this.Leg02);
         * 
         */
        
    }
    
    @Override
    public void render( Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadyaw, float headPitch, float scaleFactor )
    {
        bipedHeadwear.showModel = false;
        Head.showModel = slot == EntityEquipmentSlot.HEAD;
        
        Arm01.showModel = slot == EntityEquipmentSlot.CHEST;
        Arm02.showModel = slot == EntityEquipmentSlot.CHEST;
        Body.showModel = slot == EntityEquipmentSlot.CHEST;
        
        Leg01.showModel = slot == EntityEquipmentSlot.LEGS;
        Leg02.showModel = slot == EntityEquipmentSlot.LEGS;
        
        Shoes01.showModel = slot == EntityEquipmentSlot.FEET;
        Shoes02.showModel = slot == EntityEquipmentSlot.FEET;
        
        bipedHead = Head;
        
        bipedRightArm = Arm01;
        bipedLeftArm = Arm02;
        bipedBody = Body;
        
        if ( slot == EntityEquipmentSlot.LEGS )
        {
            bipedRightLeg = Leg01;
            bipedLeftLeg = Leg02;
        }
        else
        {
            bipedRightLeg = Shoes02;
            bipedLeftLeg = Shoes01;
        }
        
        // TODO Antenna animation - only possible if the rotation points are changed.
        
        if ( slot == EntityEquipmentSlot.CHEST )
        {
            wings.setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, netHeadyaw, headPitch, scaleFactor, entity );
            // wings.render(entity, f, f1, f2, f3, f4, f5);
        }
        
        super.render( entity, limbSwing, limbSwingAmount, ageInTicks, netHeadyaw, headPitch, scaleFactor );
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle( ModelRenderer modelRenderer, float x, float y, float z )
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
