package monstersanddungeons.client.models.items;

import monstersanddungeons.entity.miscellaneous.EntityFlyingSword;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQuartzBigSword extends ModelBase {

	//have to shift everything X 129, and  Y 186
	ModelRenderer Handle1;
	ModelRenderer Handle2;
	ModelRenderer Handle3;
	ModelRenderer Handle4;
	ModelRenderer Handle5;
	ModelRenderer Handle6;
	ModelRenderer Handle7;
	ModelRenderer Handle8;
	ModelRenderer Handle9;
	ModelRenderer SwordPart1;
	ModelRenderer SwordPart2;
	ModelRenderer SwordPart3;
	ModelRenderer SwordPart4;
	ModelRenderer SwordPart5;
	ModelRenderer SwordPart6;
	ModelRenderer SwordPart7;
	ModelRenderer SwordPart8;
	ModelRenderer SwordPart9;
	ModelRenderer SwordPart10;
	ModelRenderer SwordPart11;
	ModelRenderer SwordPart12;
	ModelRenderer SwordPart13;
	ModelRenderer SwordPart14;
	ModelRenderer SwordPart15;
	ModelRenderer SwordPart16;
	ModelRenderer SwordPart17;
	
	int increment = 360;

	public ModelQuartzBigSword()
	{
		this(0);
	}
	
	public ModelQuartzBigSword(int offX) //- moves towards left hand, + towards right hand
	{
		textureWidth = 256;
		textureHeight = 256;
	
		Handle1 = new ModelRenderer(this, 0 + 129, 4 + 186);
		Handle1.addBox(-0.5F + offX, -10.5F, -0.5F, 1, 11, 1);
		Handle1.setRotationPoint(0F, 10F, 0F);
		Handle1.setTextureSize(256, 256);
		Handle1.mirror = true;
		setRotation(Handle1, 0F, 0F, -0.0349066F);
		
		Handle2 = new ModelRenderer(this, 0+ 129, 4+ 186);
		Handle2.addBox(-0.5F + offX, -10.5F, -0.5F, 1, 11, 1);
		Handle2.setRotationPoint(0F, 10F, 0F);
		Handle2.setTextureSize(256, 256);
		Handle2.mirror = true;
		setRotation(Handle2, 0F, 0F, 0.0349066F);
		
		Handle3 = new ModelRenderer(this, 0+ 129, 0+ 186);
		Handle3.addBox(-1F+ offX, 0F, -0.7F, 2, 1, 1);
		Handle3.setRotationPoint(0F, 0F, 0F);
		Handle3.setTextureSize(256, 256);
		Handle3.mirror = true;
		setRotation(Handle3, 0F, 0F, 0F);
		
		Handle4 = new ModelRenderer(this, 0+ 129, 0+ 186);
		Handle4.addBox(-1F+ offX, 0F, -0.3F, 2, 1, 1);
		Handle4.setRotationPoint(0F, 0F, 0F);
		Handle4.setTextureSize(256, 256);
		Handle4.mirror = true;
		setRotation(Handle4, 0F, 0F, 0F);
		
		Handle5 = new ModelRenderer(this, 0+ 129, 2+ 186);
		Handle5.addBox(-0.3F+ offX, 9F, -0.3F, 1, 1, 1);
		Handle5.setRotationPoint(0F, 0F, 0F);
		Handle5.setTextureSize(256, 256);
		Handle5.mirror = true;
		setRotation(Handle5, 0F, 0F, 0F);
		
		Handle6 = new ModelRenderer(this, 0+ 129, 2+ 186);
		Handle6.addBox(-0.7F+ offX, 9F, -0.7F, 1, 1, 1);
		Handle6.setRotationPoint(0F, 0F, 0F);
		Handle6.setTextureSize(256, 256);
		Handle6.mirror = true;
		setRotation(Handle6, 0F, 0F, 0F);
		
		Handle7 = new ModelRenderer(this, 0+ 129, 2+ 186);
		Handle7.addBox(-0.7F+ offX, 9F, -0.3F, 1, 1, 1);
		Handle7.setRotationPoint(0F, 0F, 0F);
		Handle7.setTextureSize(256, 256);
		Handle7.mirror = true;
		setRotation(Handle7, 0F, 0F, 0F);
		
		Handle8 = new ModelRenderer(this, 0+ 129, 2+ 186);
		Handle8.addBox(-0.3F+ offX, 9F, -0.7F, 1, 1, 1);
		Handle8.setRotationPoint(0F, 0F, 0F);
		Handle8.setTextureSize(256, 256);
		Handle8.mirror = true;
		setRotation(Handle8, 0F, 0F, 0F);
		
		Handle9 = new ModelRenderer(this, 6+ 129, 0+ 186);
		Handle9.addBox(-2.5F+ offX, -0.5F, -1F, 5, 1, 2);
		Handle9.setRotationPoint(0F, 0F, 0F);
		Handle9.setTextureSize(256, 256);
		Handle9.mirror = true;
		setRotation(Handle9, 0F, 0F, 0F);
		
		SwordPart1 = new ModelRenderer(this, 6+ 129, 3+ 186);
		SwordPart1.addBox(-2F+ offX, -8.5F, -0.7F, 4, 8, 1);
		SwordPart1.setRotationPoint(0F, 0F, 0F);
		SwordPart1.setTextureSize(256, 256);
		SwordPart1.mirror = true;
		setRotation(SwordPart1, 0F, 0F, 0F);
		
		SwordPart2 = new ModelRenderer(this, 6+ 129, 3+ 186);
		SwordPart2.addBox(-2F+ offX, -8.5F, -0.3F, 4, 8, 1);
		SwordPart2.setRotationPoint(0F, 0F, 0F);
		SwordPart2.setTextureSize(256, 256);
		SwordPart2.mirror = true;
		setRotation(SwordPart2, 0F, 0F, 0F);
		
		SwordPart3 = new ModelRenderer(this, 6+ 129, 12+ 186);
		SwordPart3.addBox(-1.5F+ offX, -30.5F, -0.3F, 3, 22, 1);
		SwordPart3.setRotationPoint(0F, 0F, 0F);
		SwordPart3.setTextureSize(256, 256);
		SwordPart3.mirror = true;
		setRotation(SwordPart3, 0F, 0F, 0F);
		
		SwordPart4 = new ModelRenderer(this, 6+ 129, 12+ 186);
		SwordPart4.addBox(-1.5F+ offX, -30.5F, -0.7F, 3, 22, 1);
		SwordPart4.setRotationPoint(0F, 0F, 0F);
		SwordPart4.setTextureSize(256, 256);
		SwordPart4.mirror = true;
		setRotation(SwordPart4, 0F, 0F, 0F);
		
		SwordPart5 = new ModelRenderer(this, 14+ 129, 12+ 186);
		SwordPart5.addBox(-2F+ offX, -31F, -0.5F, 4, 22, 1);
		SwordPart5.setRotationPoint(0F, 0F, 0F);
		SwordPart5.setTextureSize(256, 256);
		SwordPart5.mirror = true;
		setRotation(SwordPart5, 0F, 0F, 0F);
		
		SwordPart6 = new ModelRenderer(this, 16+ 129, 9+ 186);
		SwordPart6.addBox(2F+ offX, -32F, -0.5F, 1, 2, 1);
		SwordPart6.setRotationPoint(0F, 0F, 0F);
		SwordPart6.setTextureSize(256, 256);
		SwordPart6.mirror = true;
		setRotation(SwordPart6, 0F, 0F, 0F);
		
		SwordPart7 = new ModelRenderer(this, 16+ 129, 3+ 186);
		SwordPart7.addBox(2F+ offX, -29F, -0.5F, 1, 2, 1);
		SwordPart7.setRotationPoint(0F, 0F, 0F);
		SwordPart7.setTextureSize(256, 256);
		SwordPart7.mirror = true;
		setRotation(SwordPart7, 0F, 0F, 0F);
		
		SwordPart8 = new ModelRenderer(this, 16+ 129, 6+ 186);
		SwordPart8.addBox(2F+ offX, -26F, -0.5F, 1, 2, 1);
		SwordPart8.setRotationPoint(0F, 0F, 0F);
		SwordPart8.setTextureSize(256, 256);
		SwordPart8.mirror = true;
		setRotation(SwordPart8, 0F, 0F, 0F);
		
		SwordPart9 = new ModelRenderer(this, 6+ 129, 10+ 186);
		SwordPart9.addBox(-1.5F+ offX, -31.5F, -0.3F, 4, 1, 1);
		SwordPart9.setRotationPoint(0F, 0F, 0F);
		SwordPart9.setTextureSize(256, 256);
		SwordPart9.mirror = true;
		setRotation(SwordPart9, 0F, 0F, 0F);
		
		SwordPart10 = new ModelRenderer(this, 6+ 129, 10+ 186);
		SwordPart10.addBox(-1.5F+ offX, -31.5F, -0.7F, 4, 1, 1);
		SwordPart10.setRotationPoint(0F, 0F, 0F);
		SwordPart10.setTextureSize(256, 256);
		SwordPart10.mirror = true;
		setRotation(SwordPart10, 0F, 0F, 0F);
		
		SwordPart11 = new ModelRenderer(this, 0+ 129, 16+ 186);
		SwordPart11.addBox(1.5F+ offX, -28.5F, -0.3F, 1, 1, 1);
		SwordPart11.setRotationPoint(0F, 0F, 0F);
		SwordPart11.setTextureSize(256, 256);
		SwordPart11.mirror = true;
		setRotation(SwordPart11, 0F, 0F, 0F);
		
		SwordPart12 = new ModelRenderer(this, 0+ 129, 16+ 186);
		SwordPart12.addBox(1.5F+ offX, -28.5F, -0.7F, 1, 1, 1);
		SwordPart12.setRotationPoint(0F, 0F, 0F);
		SwordPart12.setTextureSize(256, 256);
		SwordPart12.mirror = true;
		setRotation(SwordPart12, 0F, 0F, 0F);
		
		SwordPart13 = new ModelRenderer(this, 0+ 129, 18+ 186);
		SwordPart13.addBox(1.5F+ offX, -25.5F, -0.3F, 1, 1, 1);
		SwordPart13.setRotationPoint(0F, 0F, 0F);
		SwordPart13.setTextureSize(256, 256);
		SwordPart13.mirror = true;
		setRotation(SwordPart13, 0F, 0F, 0F);
		
		SwordPart14 = new ModelRenderer(this, 0+ 129, 18+ 186);
		SwordPart14.addBox(1.5F+ offX, -25.5F, -0.7F, 1, 1, 1);
		SwordPart14.setRotationPoint(0F, 0F, 0F);
		SwordPart14.setTextureSize(256, 256);
		SwordPart14.mirror = true;
		setRotation(SwordPart14, 0F, 0F, 0F);
		
		SwordPart15 = new ModelRenderer(this, 14+ 129, 12+ 186);
		SwordPart15.addBox(-2F+ offX, -31.5F, -0.5F, 4, 1, 1);
		SwordPart15.setRotationPoint(0F, 0F, 0F);
		SwordPart15.setTextureSize(256, 256);
		SwordPart15.mirror = true;
		setRotation(SwordPart15, 0F, 0F, 0F);
		
		SwordPart16 = new ModelRenderer(this, 14+ 129, 12+ 186);
		SwordPart16.addBox(-1.5F+ offX, -32F, -0.5F, 4, 1, 1);
		SwordPart16.setRotationPoint(0F, 0F, 0F);
		SwordPart16.setTextureSize(256, 256);
		SwordPart16.mirror = true;
		setRotation(SwordPart16, 0F, 0F, 0F);
		
		SwordPart17 = new ModelRenderer(this, 14+ 129, 35+ 186);
		SwordPart17.addBox(-2F+ offX, -9.5F, -0.5F, 4, 1, 1);
		SwordPart17.setRotationPoint(0F, 0F, 0F);
		SwordPart17.setTextureSize(256, 256);
		SwordPart17.mirror = true;
		setRotation(SwordPart17, 0F, 0F, 0F);
		
		
		convertToChild(SwordPart4, SwordPart12);
		convertToChild(SwordPart4, SwordPart10);
		convertToChild(SwordPart4, SwordPart14);
		
		convertToChild(SwordPart3, SwordPart9);
		convertToChild(SwordPart3, SwordPart11);
		convertToChild(SwordPart3, SwordPart13);
		
		convertToChild(SwordPart5, SwordPart16);
		convertToChild(SwordPart5, SwordPart15);
		convertToChild(SwordPart5, SwordPart6);
		convertToChild(SwordPart5, SwordPart7);
		convertToChild(SwordPart5, SwordPart8);
		
		convertToChild(SwordPart5, SwordPart4);
		convertToChild(SwordPart5, SwordPart3);
		
		convertToChild(SwordPart17, SwordPart5);
		
		convertToChild(SwordPart1, SwordPart2);
		convertToChild(SwordPart1, SwordPart17);
		
		convertToChild(Handle9, SwordPart1);
		
		convertToChild(Handle3,Handle4);
		convertToChild(Handle3,Handle9);
		
		convertToChild(Handle1,Handle6);
		convertToChild(Handle1,Handle5);
		convertToChild(Handle1,Handle7);
		convertToChild(Handle1,Handle8);
		
		convertToChild(Handle1,Handle2);
		convertToChild(Handle1,Handle3);
		
		
	}
	public ModelRenderer getHandle()
	{
		return this.Handle1;
	}
	
	public void attachSwordTo(ModelRenderer parent)
	{
		this.convertToChild(parent, this.Handle1);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		this.Handle1.render(f5);
		
		if(entity instanceof EntityFlyingSword)
		{
			if(((EntityFlyingSword) entity).getIsSevenSword() == null)
			{
				if(((EntityFlyingSword) entity).getAttackCD() > 0)
				{
					this.movePiece(this.getHandle(), 2f, 65f, increment, 0f);
				
					if(this.getHandle().rotateAngleY == degToRad(360))
					{
						this.getHandle().rotateAngleY = 0;
					}
				}
			}else
			{
				this.getHandle().rotateAngleY = 0f;
				this.movePiece(this.getHandle(), 2f, 0f, 0, 0f);
			}
		}
	}
	public boolean movePiece(ModelRenderer currentRender, float speed, float rotationX, float rotationY, float rotationZ)
	{
		if(currentRender.rotateAngleX < degToRad(rotationX))
		{
			if(currentRender.rotateAngleX + degToRad(speed) < degToRad(rotationX))
			{
				currentRender.rotateAngleX += degToRad(speed);
			}else
				currentRender.rotateAngleX = degToRad(rotationX);
		}else
		{
			if(currentRender.rotateAngleX - degToRad(speed) > degToRad(rotationX))
			{
				currentRender.rotateAngleX -= degToRad(speed);
			}else
			{	
				currentRender.rotateAngleX = degToRad(rotationX);
			}
		}

		//Y
		if(currentRender.rotateAngleY < degToRad(rotationY))
		{
			if(currentRender.rotateAngleY + degToRad(speed) < degToRad(rotationY))
			{
				currentRender.rotateAngleY += degToRad(speed);
			}else
				currentRender.rotateAngleY = degToRad(rotationY);
		}else
		{
			if(currentRender.rotateAngleY - degToRad(speed) > degToRad(rotationY))
			{
				currentRender.rotateAngleY -= degToRad(speed);
			}else
				currentRender.rotateAngleY = degToRad(rotationY);
		}
		//Z
		if(currentRender.rotateAngleZ < degToRad(rotationZ))
		{
			if(currentRender.rotateAngleZ + degToRad(speed) < degToRad(rotationZ))
			{
				currentRender.rotateAngleZ += degToRad(speed);
			}else
				currentRender.rotateAngleZ = degToRad(rotationZ);
		}else
		{
			if(currentRender.rotateAngleZ - degToRad(speed) > degToRad(rotationZ))
			{
				currentRender.rotateAngleZ -= degToRad(speed);
			}else
				currentRender.rotateAngleZ = degToRad(rotationZ);
		}

		if(currentRender.rotateAngleX != degToRad(rotationX) || currentRender.rotateAngleY != degToRad(rotationY) || currentRender.rotateAngleZ != degToRad(rotationZ))
		{
			return false;
		}

		return true;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}

	protected float degToRad(float degrees)
	{
		return degrees * (float)Math.PI / 180 ;
	}

}
