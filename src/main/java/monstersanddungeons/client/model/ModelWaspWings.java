package monstersanddungeons.client.model;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
public class ModelWaspWings extends ModelBase
{
    public ModelRenderer wingRight;
    public ModelRenderer wingLeft;
    
    public ModelWaspWings() {
        this.textureWidth = 96;
        this.textureHeight = 96;
        
        this.wingLeft = new ModelRenderer( this, 0, 64 );
        this.wingLeft.mirror = true;
        this.wingLeft.setRotationPoint( 3.0F, 0.5F, 2.8F );
        this.wingLeft.addBox( -5.0F, 0.0F, 0.0F, 7, 22, 0, 0.0F );
        
        this.wingRight = new ModelRenderer( this, 0, 64 );
        this.wingRight.setRotationPoint( -3.0F, 0.5F, 2.8F );
        this.wingRight.addBox( -2.0F, 0.0F, 0.0F, 7, 22, 0, 0.0F );
    }
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render( Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale )
    {
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableCull();
        
        if ( entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).isChild() )
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale( 0.5F, 0.5F, 0.5F );
            GlStateManager.translate( 0.0F, 1.5F, -0.1F );
            this.wingLeft.render( scale );
            this.wingRight.render( scale );
            GlStateManager.popMatrix();
        }
        else
        {
            this.wingLeft.render( scale );
            this.wingRight.render( scale );
        }
    }
    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far" arms and legs can swing at most.
     */
    public void setRotationAngles( float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn )
    {
        super.setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn );
        float f = 0.2617994F;
        float f1 = -0.2617994F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        
        if ( entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).isElytraFlying() )
        {
            float f4 = 1.0F;
            
            if ( entityIn.motionY < 0.0D )
            {
                Vec3d vec3d = (new Vec3d( entityIn.motionX, entityIn.motionY, entityIn.motionZ )).normalize();
                f4 = 1.0F - (float) Math.pow( -vec3d.y, 1.5D );
            }
            
            f = f4 * 0.34906584F + (1.0F - f4) * f;
            f1 = f4 * -((float) Math.PI / 2F) + (1.0F - f4) * f1;
        }
        else if ( entityIn.isSneaking() )
        {
            f = ((float) Math.PI * 2F / 9F);
            f1 = -((float) Math.PI / 4F);
            f2 = 3.0F;
            f3 = 0.08726646F;
        }
        
        this.wingLeft.rotationPointX = 5.0F;
        this.wingLeft.rotationPointY = f2;
        
        if ( entityIn instanceof AbstractClientPlayer )
        {
            AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer) entityIn;
            abstractclientplayer.rotateElytraX = (float) ((double) abstractclientplayer.rotateElytraX + (double) (f - abstractclientplayer.rotateElytraX) * 0.1D);
            abstractclientplayer.rotateElytraY = (float) ((double) abstractclientplayer.rotateElytraY + (double) (f3 - abstractclientplayer.rotateElytraY) * 0.1D);
            abstractclientplayer.rotateElytraZ = (float) ((double) abstractclientplayer.rotateElytraZ + (double) (f1 - abstractclientplayer.rotateElytraZ) * 0.1D);
            this.wingLeft.rotateAngleX = abstractclientplayer.rotateElytraX;
            this.wingLeft.rotateAngleY = abstractclientplayer.rotateElytraY;
            this.wingLeft.rotateAngleZ = abstractclientplayer.rotateElytraZ;
        }
        else
        {
            this.wingLeft.rotateAngleX = f;
            this.wingLeft.rotateAngleZ = f1;
            this.wingLeft.rotateAngleY = f3;
        }
        
        this.wingRight.rotationPointX = -this.wingLeft.rotationPointX;
        this.wingRight.rotateAngleY = -this.wingLeft.rotateAngleY;
        this.wingRight.rotationPointY = this.wingLeft.rotationPointY;
        this.wingRight.rotateAngleX = this.wingLeft.rotateAngleX;
        this.wingRight.rotateAngleZ = -this.wingLeft.rotateAngleZ;
    }
}